package com.unicauca.maruc.facade.impl;

import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.Consecuencia;
import com.unicauca.maruc.domain.model.OpcionConsecuencia;
import com.unicauca.maruc.domain.model.Rol;
import com.unicauca.maruc.domain.model.TipoActor;
import com.unicauca.maruc.domain.model.TipoAfectacion;
import com.unicauca.maruc.domain.model.TipoObservacion;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.domain.model.enums.ERol;
import com.unicauca.maruc.domain.model.enums.TipoCampo;
import com.unicauca.maruc.dto.catalogos.CatalogoConsecuenciaDTO;
import com.unicauca.maruc.dto.catalogos.OpcionConsecuenciaMapper;
import com.unicauca.maruc.dto.mappers.CatalogoConsecuenciaMapper;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaDTO;
import com.unicauca.maruc.exceptions.CodigoError;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.exceptions.ReglaNegocioExcepcion;
import com.unicauca.maruc.facade.AdministrarCatalogosFacade;
import com.unicauca.maruc.service.ActividadService;
import com.unicauca.maruc.service.ConsecuenciaService;
import com.unicauca.maruc.service.OpcionConsecuenciaService;
import com.unicauca.maruc.service.RolService;
import com.unicauca.maruc.service.TipoActorService;
import com.unicauca.maruc.service.TipoAfectacionService;
import com.unicauca.maruc.service.TipoObservacionService;
import com.unicauca.maruc.service.TipoProcesoService;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.LockModeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AdministrarCatalogosDefaultFacade implements AdministrarCatalogosFacade {

  @Autowired
  private ActividadService actividadService;
  @Autowired
  private ConsecuenciaService consecuenciaService;
  @Autowired
  private OpcionConsecuenciaService opcionConsecuenciaService;
  @Autowired
  private TipoAfectacionService tipoAfectacionService;
  @Autowired
  private TipoProcesoService tipoProcesoService;
  @Autowired
  private TipoActorService tipoActorService;
  @Autowired
  private TipoObservacionService tipoObservacionService;
  @Autowired
  private RolService rolService;

  @Override
  public Actividad registrarActividad(final Actividad actividad) {
    return actividadService.guardar(actividad);
  }

  @Override
  public Consecuencia registrarConsecuencia(final Consecuencia consecuencia)
      throws ReglaNegocioExcepcion {
    final Consecuencia respuesta = consecuenciaService.registrarConsecuencia(consecuencia);
    final Set<OpcionConsecuencia> opcionesAGuardar = consecuencia.getOpciones().stream()
        .map(opcion -> {
          opcion.setConsecuencia(respuesta);
          return opcion;
        }).collect(Collectors.toSet());
    opcionConsecuenciaService.guardarTodo(opcionesAGuardar);
    return respuesta;
  }

  @Override
  public Actividad editarActividad(final Long id, final Actividad actividad)
      throws EntityNotFountException {
    final Actividad actividadEncontrada = buscarActividad(id);
    actividadEncontrada.setFrecuenciaInvertida(actividad.isFrecuenciaInvertida());
    actividadEncontrada.setNombre(actividad.getNombre());
    return actividadService.actualizar(actividadEncontrada);
  }

  @Override
  public void eliminarActividad(final Long idActividad) throws EntityNotFountException {
    final Actividad actividadAEliminar = buscarActividad(idActividad);
    actividadService.eliminar(actividadAEliminar);
  }

  @Override
  public Actividad buscarActividad(final Long id) throws EntityNotFountException {
    return actividadService.buscarPorId(id).orElseThrow(
        () -> new EntityNotFountException(CodigoError.ENTIDAD_NO_ENCONTRADA, "Actividad", id));
  }

  @Override
  @Lock(LockModeType.WRITE)
  @Transactional(readOnly = false)
  public Consecuencia editarConsecuencia(final CatalogoConsecuenciaDTO consecuencia)
      throws EntityNotFountException {
    final Consecuencia actual = consecuenciaService.buscarConsecuencia(consecuencia.getId())
        .orElseThrow(
            () -> new EntityNotFountException(CodigoError.ENTIDAD_NO_ENCONTRADA, "Consecuencia",
                consecuencia.getId()));
    final Consecuencia actualizada = CatalogoConsecuenciaMapper.fromDto(actual, consecuencia);
    if (actualizada.getTipoCampo().equals(TipoCampo.texto)) {
      opcionConsecuenciaService.eliminarOpcionConsecuenciaPorIdConsecuencia(actualizada.getId());
      final OpcionConsecuenciaDTO request = consecuencia.getListaOpciones().get(0);
      OpcionConsecuencia opcionConsecuencia = new OpcionConsecuencia();
      opcionConsecuencia.setPuntaje(request.getPuntaje());
      opcionConsecuencia.setConsecuencia(actualizada);
      opcionConsecuenciaService.guardar(opcionConsecuencia);
    } else {
      final Set<OpcionConsecuencia> existentes = consecuencia.getListaOpciones()
          .stream().filter(op -> Objects.nonNull(op.getId()))
          .map(dto -> OpcionConsecuenciaMapper.setFromDto(dto,
              opcionConsecuenciaService.buscarOpcionPorId(dto.getId())))
          .collect(Collectors.toSet());
      final List<OpcionConsecuencia> nuevas = consecuencia.getListaOpciones().stream()
          .filter(op -> Objects.isNull(op.getId())).map(
              OpcionConsecuenciaMapper::fromDto
          ).toList();
      guardarNuevasOpciones(nuevas, actualizada);
      actualizada.setOpciones(existentes);
    }
    return consecuenciaService.actualizarConsecuencia(actualizada);
  }

  private void guardarNuevasOpciones(List<OpcionConsecuencia> opciones, Consecuencia consecuencia) {
    opciones.stream().forEach(oc -> {
      oc.setConsecuencia(consecuencia);
      opcionConsecuenciaService.guardar(oc);
    });
  }

  @Override
  public CatalogoConsecuenciaDTO buscarConsecuencia(final Long id) throws EntityNotFountException {
    return consecuenciaService.buscarConsecuencia(id)
        .map(CatalogoConsecuenciaMapper::toDto)
        .orElseThrow(
            () -> new EntityNotFountException(CodigoError.ENTIDAD_NO_ENCONTRADA, "Consecuencia",
                id));
  }

  @Override
  public Page<CatalogoConsecuenciaDTO> listarConsecuencias(final int page, final int size) {
    final Page<Consecuencia> consecuencias = consecuenciaService.listarConsecuencias(page, size);
    return new PageImpl<CatalogoConsecuenciaDTO>(
        CatalogoConsecuenciaMapper.mapPageToDto(consecuencias), consecuencias.getPageable(),
        consecuencias.getTotalElements());
  }

  @Override
  public Long eliminarConsecuencia(Long id) throws EntityNotFountException {
    final Consecuencia consecuencia = consecuenciaService.buscarConsecuencia(id).orElseThrow(
        () -> new EntityNotFountException(CodigoError.ENTIDAD_NO_ENCONTRADA, "Consecuencia", id));
    return consecuenciaService.eliminar(consecuencia);
  }

  @Override
  public List<TipoAfectacion> buscarTiposAfectacion() {
    return tipoAfectacionService.buscarTodos();
  }

  @Override
  public Page<Actividad> listarActividades(int page, int size) {
    return actividadService.fetchActividades(page, size);
  }

  @Override
  public List<TipoProceso> listarTipoProceso() {
    return tipoProcesoService.findAll();
  }

  @Override
  public TipoActor buscarTipoActorByCodigo(String codigo) {
    return tipoActorService.findByCodigo(codigo);
  }

  @Override
  public TipoObservacion buscarTipoObservacionByCodigo(String codigo) {
    return tipoObservacionService.findByCodigo(codigo);
  }

  @Override
  public Rol SeleccionarRolByCodigo(ERol oci) {
    return rolService.findByCodigo(oci);
  }

  @Override
  public boolean eliminarOpcionConsecuencia(Long id) {
    opcionConsecuenciaService.eliminarOpcionConsecuencia(id);
    return true;
  }
}
