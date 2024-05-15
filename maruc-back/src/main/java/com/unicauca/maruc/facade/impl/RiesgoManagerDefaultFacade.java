package com.unicauca.maruc.facade.impl;

import com.unicauca.maruc.comun.ConstantesGlogables;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.Causa;
import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.domain.model.Dependencia;
import com.unicauca.maruc.domain.model.DifusionControl;
import com.unicauca.maruc.domain.model.EntidadCatalogo;
import com.unicauca.maruc.domain.model.EstatusInformacionRiesgo;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.EvidenciaObservacion;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.RiesgoObservacion;
import com.unicauca.maruc.domain.model.OpcionConsecuencia;
import com.unicauca.maruc.domain.model.OpcionConsecuenciaRiesgo;
import com.unicauca.maruc.domain.model.Periodicidad;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.RiesgoActividad;
import com.unicauca.maruc.domain.model.RiesgoResidual;
import com.unicauca.maruc.domain.model.TipoAfectacion;
import com.unicauca.maruc.domain.model.TipoControl;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.domain.model.TipoRiesgo;
import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.domain.model.enums.EIndicador;
import com.unicauca.maruc.dto.mappers.CausaMapper;
import com.unicauca.maruc.dto.mappers.ControlMapper;
import com.unicauca.maruc.dto.mappers.ControlResidualMapper;
import com.unicauca.maruc.dto.mappers.RiesgoMapper;
import com.unicauca.maruc.dto.mappers.RiesgoResidualMapper;
import com.unicauca.maruc.dto.riesgos.CausaDTO;
import com.unicauca.maruc.dto.riesgos.ControlDTO;
import com.unicauca.maruc.dto.riesgos.ControlResidualDTO;
import com.unicauca.maruc.dto.riesgos.IndicadorDTO;
import com.unicauca.maruc.dto.riesgos.InformacionBasicaRiesgoDTO;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaRiesgoDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoInherenteDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoResidualDTO;
import com.unicauca.maruc.dto.riesgos.SearchRiesgosDTO.Search;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.exceptions.MarucError;
import com.unicauca.maruc.facade.RiesgoManagerFacade;
import com.unicauca.maruc.service.ActividadService;
import com.unicauca.maruc.service.CausaService;
import com.unicauca.maruc.service.ControlResidualService;
import com.unicauca.maruc.service.ControlService;
import com.unicauca.maruc.service.DependenciaService;
import com.unicauca.maruc.service.DifusionControlService;
import com.unicauca.maruc.service.EstadoInformaconRiegoService;
import com.unicauca.maruc.service.EvidenciaService;
import com.unicauca.maruc.service.OpcionConsecuenciaService;
import com.unicauca.maruc.service.PeriodicidadService;
import com.unicauca.maruc.service.RiesgoActividadService;
import com.unicauca.maruc.service.RiesgoInherenteService;
import com.unicauca.maruc.service.RiesgoResidualService;
import com.unicauca.maruc.service.RiesgoService;
import com.unicauca.maruc.service.TipoAfectacionService;
import com.unicauca.maruc.service.TipoControlService;
import com.unicauca.maruc.service.TipoProcesoService;
import com.unicauca.maruc.service.TipoRiesgoService;
import com.unicauca.maruc.service.ValidarControlService;
import com.unicauca.maruc.service.impl.CatalogoService;
import com.unicauca.maruc.service.impl.riesgoresidual.CalculadoraNivelRiesgoResidual;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Slf4j
public class RiesgoManagerDefaultFacade implements RiesgoManagerFacade {

  @Autowired
  private TipoRiesgoService tipoRiesgoService;

  @Autowired
  private RiesgoService riesgoService;

  @Autowired
  private CausaService causaService;

  @Autowired
  private ActividadService actividadService;

  @Autowired
  private TipoAfectacionService tipoAfectacionService;

  @Autowired
  private TipoControlService tipoControlService;

  @Autowired
  private TipoProcesoService tipoProcesoService;

  @Autowired
  private DifusionControlService difusionControlService;

  @Autowired
  private PeriodicidadService periodicidadService;

  @Autowired
  private RiesgoActividadService riesgoActividadService;

  @Autowired
  private ControlService controlService;

  @Autowired
  private EstadoInformaconRiegoService estadoInformaconRiegoService;

  @Autowired
  private OpcionConsecuenciaService opcionConsecuenciaService;

  @Autowired
  private RiesgoInherenteService riesgoInherenteService;

  @Autowired
  private ValidarControlService validarControlService;

  @Autowired
  private CatalogoService catalogoService;

  @Autowired
  private CalculadoraNivelRiesgoResidual calculadoraNivelRiesgoResidual;

  @Autowired
  private RiesgoResidualService riesgoResidualService;

  @Autowired
  private ControlResidualService controlResidualService;

  @Autowired
  private DependenciaService dependenciaService;

  @Autowired
  private EvidenciaService evidenciaService;

  @Override
  @Transactional(readOnly = true)
  public List<TipoAfectacion> buscarConConsecuencias() {
    return tipoAfectacionService.buscarConConsecuencias();
  }

  @Override
  @Transactional(readOnly = true)
  public List<DifusionControl> buscarDifusionesControl() {
    return difusionControlService.buscarTodos();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Periodicidad> buscarPeriodicidades() {
    return periodicidadService.buscarTodos();
  }

  @Override
  @Transactional(readOnly = true)
  public List<TipoRiesgo> buscarTiposDeRiesgo() {
    return tipoRiesgoService.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public RiesgoDTO cargarRiesgo(final Long idRiesgo) {
    log.info("Cargando información del riesgo: {}", idRiesgo);
    final Riesgo riesgo = riesgoService.buscarPorId(idRiesgo)
        .orElseThrow(() -> new EntidadNoExisteException("No existe un riesgo con id " + idRiesgo));
    List<ControlResidual> controles = controlResidualService.buscarPorRiesgo(riesgo.getId());
    for (ControlResidual control : controles) {
      control.setCausas(causaService.buscarCausasDeControlResidual(riesgo.getId(), control.getId()).stream().map(CausaMapper::mapFromDTO).toList());
    }
    RiesgoDTO riesgoDTO = new RiesgoDTO();
    if(riesgo.getEstatusInformacionRiesgo().getValor() >= 8){
      riesgoDTO = new RiesgoDTO(riesgo,
      controles,
      riesgoInherenteService.calcularRiesgoInherente(riesgo.getId()), new RiesgoResidualDTO(),
      getCumplimientoRiesgo(riesgo), getCorregidoObservacionesEvidencia(riesgo),
      getCorregidoObservacionesRiesgo(riesgo));
    }else{
      riesgoDTO.setRiesgo(riesgo);
      riesgoDTO.setControles(controles);
    }
    return riesgoDTO;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Riesgo> filterByProceso(final long key, final int page, final int size) {
    return riesgoService.filterByProceso(key, page, size);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Riesgo> filterByTipoRiesgo(final long key, final int page, final int size) {
    return riesgoService.filterByTipoRiesgo(key, page, size);
  }

  @Override
  public boolean guardarActividadesRiesgo(final List<RiesgoActividad> actividadesRiesgo) {
    actividadesRiesgo.forEach(ra -> riesgoActividadService.guardar(ra));
    return true;
  }

  @Override
  public void guardarCausas(final List<CausaDTO> causas) {
    causas.stream().forEach(causaService::guardarCausa);
  }

  @Override
  public boolean guardarControles(final List<ControlDTO> controles) {
    controles.forEach(ctrl -> controlService.guardarControl(ControlMapper.mapFromDTO(ctrl)));
    return true;
  }

  @Override
  public Riesgo guardarInformacionInicial(
      final InformacionBasicaRiesgoDTO informacionBasicaRiesgoDTO) {
    final TipoRiesgo tipoRiesgo = tipoRiesgoService.buscarPorId(
        informacionBasicaRiesgoDTO.getIdTipoRiesgo()).orElseThrow(
        () -> new EntidadNoExisteException(
            "No existe un tipo de riesgo con id: " + informacionBasicaRiesgoDTO.getIdTipoRiesgo()));
    final TipoProceso tipoProceso = tipoProcesoService.buscarPorId(informacionBasicaRiesgoDTO.getIdProceso())
        .orElse(null);
    final EstatusInformacionRiesgo estatus = estadoInformaconRiegoService.findByCodigo(
        ConstantesGlogables.ESTADO_RIESGO_INICIAL);
    final Riesgo riesgo = new Riesgo(informacionBasicaRiesgoDTO.getNombre(),
        informacionBasicaRiesgoDTO.isRelacionConObjetivo(),
        informacionBasicaRiesgoDTO.getObjetivo(), tipoRiesgo,
        informacionBasicaRiesgoDTO.getMotivacion(), informacionBasicaRiesgoDTO.getResponsabilidad(),
        informacionBasicaRiesgoDTO.getOportunidad());
    log.info("Estatus información riesgo: {}", estatus.getCodigo());
    riesgo.setEstatusInformacionRiesgo(estatus);
    riesgo.setId(informacionBasicaRiesgoDTO.getId());
    riesgo.setUsuario(new Usuario(informacionBasicaRiesgoDTO.getIdUsuario()));
    riesgo.setTipoProceso(tipoProceso);
    final Riesgo riesgoGuardado = riesgoService.guardarInformacionInicial(riesgo);
    validarControlService.generarControlesPorDefecto(riesgoGuardado);
    return riesgoGuardado;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Actividad> listarActividades() {
    return actividadService.fetchActividades();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Riesgo> listarByNombre(final String key, final int page, final int size) {
    return riesgoService.listarByNombre(key, page, size);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<RiesgoDTO> listarRiesgosDTO(final int page, final int size, final Boolean all) {
    Page<RiesgoDTO> res = riesgoService.listarRiesgosDTO(page, size, all);
    for (RiesgoDTO riesgo : res.getContent()) {
      Riesgo ries = riesgo.getRiesgo();
      riesgo.setCumplimiento_observaciones_evidencia(getCorregidoObservacionesEvidencia(ries));
      riesgo.setCumplimiento_observaciones_riesgo(getCorregidoObservacionesRiesgo(ries));
      riesgo.setCumplimiento(getCumplimientoRiesgo(ries));
      if(!all){
      riesgo.setRiesgo_inherente(riesgoInherenteService.calcularRiesgoInherente(ries.getId()));
      riesgo.setRiesgo_residual(calcularNivelRiesgoResidual(ries.getId()));
     
      }
    }
    return res;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<RiesgoDTO> listarRiesgosLiderDTO(final int page, final int size, final Boolean all, final long idLider) {
    Page<RiesgoDTO> res = riesgoService.listarRiesgosLiderDTO(page, size, all, idLider);
    for (RiesgoDTO riesgo : res.getContent()) {
      Riesgo ries = riesgo.getRiesgo();
      riesgo.setCumplimiento(getCumplimientoRiesgo(ries));
      riesgo.setCumplimiento_observaciones_evidencia(getCorregidoObservacionesEvidencia(ries));
      riesgo.setCumplimiento_observaciones_riesgo(getCorregidoObservacionesRiesgo(ries));
      if(!all){
      riesgo.setRiesgo_inherente(riesgoInherenteService.calcularRiesgoInherente(ries.getId()));
      riesgo.setRiesgo_residual(calcularNivelRiesgoResidual(ries.getId()));
      }
    }
    return res;
  }

  @Override
  @Transactional(readOnly = true)
  public List<RiesgoDTO> listarRiesgos() {
    List<RiesgoDTO> res = riesgoService.listarRiesgos();
    for (RiesgoDTO riesgo : res) {
      Riesgo ries = riesgo.getRiesgo();
      riesgo.setCumplimiento(getCumplimientoRiesgo(ries));
      riesgo.setRiesgo_inherente(riesgoInherenteService.calcularRiesgoInherente(ries.getId()));
      List<ControlResidual> controles = controlResidualService.buscarPorRiesgo(ries.getId());
    for (ControlResidual control : controles) {
      control.setCausas(causaService.buscarCausasDeControlResidual(ries.getId(), control.getId()).stream().map(CausaMapper::mapFromDTO).toList());
    }
      riesgo.setControles(controles);
      riesgo.setRiesgo_residual(calcularNivelRiesgoResidual(ries.getId()));
    }
    return res;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Riesgo> listarRiesgos(final int page, final int size) {
    return riesgoService.listarRiesgos(page, size);
  }

  @Override
  public Page<RiesgoDTO> filter(Search search, int page, int size) {
    Page<RiesgoDTO> res = riesgoService.filter(search, page, size);
    for (RiesgoDTO riesgo : res.getContent()) {
      Riesgo ries = riesgo.getRiesgo();
      riesgo.setCumplimiento(getCumplimientoRiesgo(ries));
      riesgo.setRiesgo_inherente(riesgoInherenteService.calcularRiesgoInherente(ries.getId()));
      riesgo.setRiesgo_residual(calcularNivelRiesgoResidual(ries.getId()));
    }
    return res;
  }

  @Override
  @Transactional(readOnly = true)
  public List<TipoControl> listarTiposDeControl() {
    return tipoControlService.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Riesgo SeleccionarRiesgoPorId(final Long id) {
    return riesgoService.SeleccionarRiesgo(id);
  }

  @Override
  public boolean actualizarEstadoRiesgo(final Long idRiesgo, final String codigoEstado) {
    final EstatusInformacionRiesgo estatus = this.estadoInformaconRiegoService.findByCodigo(
        codigoEstado);
    log.info("Estado: {}, valor {}", estatus.getCodigo(), estatus.getValor());
    final Riesgo riesgo = this.riesgoService.buscarPorId(idRiesgo)
        .orElseThrow(IllegalArgumentException::new);
    if (riesgo.getEstatusInformacionRiesgo().getValor() < estatus.getValor()) {
      riesgo.setEstatusInformacionRiesgo(estatus);
      this.riesgoService.guardarInformacionInicial(riesgo);
    }
    return true;
  }

  @Override
  public Riesgo actualizarInformacionBasica(
      final InformacionBasicaRiesgoDTO informacionBasicaRiesgoDTO) throws EntityNotFountException {
    // load riesgo
    log.info("Tipo proceso id: {}", informacionBasicaRiesgoDTO.getIdProceso());
    final Riesgo riesgo = riesgoService.buscarPorId(informacionBasicaRiesgoDTO.getId()).orElseThrow(
        () -> new EntityNotFountException(
            "No existe el riesgo con id: " + informacionBasicaRiesgoDTO.getId()));
    RiesgoMapper.setValuesFromInformacionInicialDTO(informacionBasicaRiesgoDTO, riesgo);
    return riesgoService.actualizarRiesgo(riesgo);
  }

  @Override
  public Causa actualizarCausa(final CausaDTO causaDTO) throws EntityNotFountException {
    final Causa causa = causaService.buscarPorId(causaDTO.getId()).orElseThrow(
        () -> new EntityNotFountException("No existe la causa con id: " + causaDTO.getId()));
    CausaMapper.mapFromDTO(causaDTO, causa);
    return causaService.actualizar(causa);
  }

  @Override
  public Causa guardarCausa(final CausaDTO causaDto) {
    final Riesgo riesgo = riesgoService.buscarPorId(causaDto.getIdRiesgo())
        .orElseThrow(IllegalArgumentException::new);
    final Causa causa = CausaMapper.mapFromDTO(causaDto);
    causa.setRiesgo(riesgo);
    return causaService.guardar(causa);
  }

  @Override
  @Transactional(readOnly = true)
  public List<CausaDTO> listarCausas(final Long idRiesgo) {
    return causaService.buscarPorRiesgo(new Riesgo(idRiesgo));
  }

  @Override
  public boolean eliminarCausa(final Long idCausa) {
    final Causa causa = causaService.buscarPorId(idCausa)
        .orElseThrow(IllegalArgumentException::new);
    causaService.eliminar(causa);
    return true;
  }

  @Override
  public List<OpcionConsecuenciaRiesgoDTO> listarConsecuencias(final Long idRiesgo) {
    return opcionConsecuenciaService.buscarConsecuenciasRiesgo(idRiesgo);
  }

  @Override
  public OpcionConsecuenciaRiesgoDTO guardarConsecuencia(
      final OpcionConsecuenciaRiesgoDTO opcionConsecuenciaRiesgoDTO) {
    final OpcionConsecuenciaRiesgo pc = new OpcionConsecuenciaRiesgo();
    final OpcionConsecuencia opcionConsecuencia = opcionConsecuenciaService.buscarOpcionPorId(opcionConsecuenciaRiesgoDTO.getIdOpcionConsecuencia());
    pc.setOpcionConsecuencia(opcionConsecuencia);
    final Riesgo riesgo = new Riesgo();
    riesgo.setId(opcionConsecuenciaRiesgoDTO.getIdRiesgo());
    pc.setRiesgo(riesgo);
    riesgoService.guardarConseucenciasDelRiesgo(pc);
    return opcionConsecuenciaRiesgoDTO;
  }

  @Override
  public boolean eliminarConsecuencia(final Long idConsecuencia) {
    this.opcionConsecuenciaService.eliminar(idConsecuencia);
    return true;
  }

  @Override
  public List<Riesgo> listarRiesgosByLiderProceso(final Long id_lider_proceso) {
    return riesgoService.listarRiesgosByLiderProceso(id_lider_proceso);
  }

  @Override
  @Transactional(readOnly = true)
  public RiesgoInherenteDTO calcularRiesgoInherente(final Long idRiesgo) {
    return riesgoInherenteService.calcularRiesgoInherente(idRiesgo);
  }

  @Override
  public <T extends EntidadCatalogo> List<T> cargarCatalogo(final Class<T> Clasecatalogo) {
    return catalogoService.cargarCatalogo(Clasecatalogo);
  }

  @Override
  public boolean editarControl(final Long idControl, final Control control) {
    return validarControlService.editarControl(idControl, control);
  }

  @Override
  public List<CausaDTO> buscarCriticas(final Long idRiesgo) {
    return causaService.buscarCriticas(idRiesgo);
  }

  @Override
  public RiesgoResidualDTO calcularNivelRiesgoResidual(final Long idRiesgo) {
    final String nivelRiesgo = calculadoraNivelRiesgoResidual.ejecutar(idRiesgo);
    final RiesgoResidual riesgoResidual = riesgoResidualService.buscarPorIdRiesgo(idRiesgo);
    return RiesgoResidualDTO.builder().id(riesgoResidual.getId()).idRiesgo(idRiesgo)
        .nivel(nivelRiesgo).tratamiento(riesgoResidual.getTratamiento()).build();
  }

  @Override
  public List<ControlResidual> buscarPorRiesgoResidual(final Long idRiesgoResidual) {
    return controlResidualService.buscarPorRiesgoResidual(idRiesgoResidual);
  }

  @Override
  public boolean guardarRiesgoResidual(final RiesgoResidualDTO riesgoResidualDTO) {
    try {
      RiesgoResidual riesgoResidual = RiesgoResidualMapper.buildFromDTO(riesgoResidualDTO);
      riesgoResidualService.guardar(riesgoResidual);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean actualizarRiesgoResidual(final Long idRiesgoResidual,
      final RiesgoResidualDTO riesgoResidualDTO) {
    RiesgoResidual riesgoResidual = riesgoResidualService.buscarPorId(idRiesgoResidual)
        .orElseThrow(IllegalArgumentException::new);
    riesgoResidual.setTratamiento(riesgoResidualDTO.getTratamiento());
    riesgoResidualService.actualizar(riesgoResidual);
    return true;
  }

  @Override
  public boolean eliminarControlResidual(final Long idControlResidual) {
    ControlResidual cr = controlResidualService.buscarPorId(idControlResidual).orElseThrow(
        () -> new EntidadNoExisteException(
            "No existe un contrl residual con id: " + idControlResidual));
    controlResidualService.eliminar(cr);
    return true;
  }

  @Override
  public boolean guardarControlResidual(final Long idRiesgoResidual,
      final ControlResidualDTO controlResidualDTO) {
    ControlResidual controlResidual = ControlResidualMapper.mapFromDTO(controlResidualDTO);
    controlResidual.setRiesgoResidual(new RiesgoResidual(idRiesgoResidual));
    controlResidualService.guardar(controlResidual);
    Evidencia evidencia = new Evidencia();
    evidencia.setControl(controlResidual);
    evidencia.setCumplimiento_oci(false);
    evidencia.setCumplimiento_opdi(false);
    evidencia.setPorcentajeAvanceOci((float) 0);
    evidencia.setPorcentajeAvanceOpdi((float) 0);
    evidenciaService.guardarEvidencia(evidencia);
    return true;
  }

  @Override
  public boolean editarControlResidual(Long idControlResidual,
      ControlResidualDTO controlResidualDTO) {
    final ControlResidual controlResidual = controlResidualService.buscarPorId(idControlResidual)
        .orElseThrow(() -> new EntityNotFountException(
            "No existe el control residual con el id: " + idControlResidual));
    controlResidualService.guardar(
        ControlResidualMapper.setEntityValuesFromDTO(controlResidual, controlResidualDTO));
    final Evidencia evidencia = evidenciaService.encontrarEvidencia(
        controlResidualDTO.getEvidencia().getId());
    evidencia.setEvidencia(controlResidualDTO.getEvidencia().getEvidencia());
    evidenciaService.actualizarEvidencia(evidencia);
    return true;
  }

  public List<IndicadorDTO> generarIndicadores() {
    List<IndicadorDTO> ret = new ArrayList<IndicadorDTO>();
    List<Riesgo> riesgos = riesgoService.findByestatusInformacionRiesgo_valorGreaterThanEqual(7);
    if (riesgos.size() > 0) {
      IndicadorDTO general = generateIndicadorGeneral(riesgos);
      ret.add(general);
      generateIndicadoresProceso(ret, riesgos);
      generateIndicadoresDependencia(ret, riesgos);
    }
    return ret;
  }

  private void generateIndicadoresProceso(List<IndicadorDTO> ret, List<Riesgo> riesgos) {
    //controles programados / controles ejecutados
    List<IndicadorDTO> indicadores = new ArrayList<IndicadorDTO>();
    List<TipoProceso> tipos_proceso = tipoProcesoService.findAll();
    for (TipoProceso tipo_proceso : tipos_proceso) {
      if (tipo_proceso.getId() != null && tipo_proceso.getId() > 0) {
        List<Riesgo> riesgos_proceso = riesgos.stream()
            .filter(r -> r.getTipoProceso().getId() == tipo_proceso.getId())
            .collect(Collectors.toList());
        if (riesgos_proceso.size() > 0) {
          IndicadorDTO indicador = generateIndicadorProceso(riesgos_proceso, tipo_proceso);
          indicadores.add(indicador);
        }
      }
    }
    ret.addAll(indicadores);
  }

  private void generateIndicadoresDependencia(List<IndicadorDTO> ret, List<Riesgo> riesgos) {
    //controles programados / controles ejecutados
    List<IndicadorDTO> indicadores = new ArrayList<IndicadorDTO>();
    List<Dependencia> dependencias = dependenciaService.buscarTodos();
    for (Dependencia dependencia : dependencias) {
      if (dependencia.getId() != null && dependencia.getId() > 0) {
        List<Riesgo> riesgos_proceso = riesgos.stream()
            .filter(r -> r.getUsuario().getDependencia().getId() == dependencia.getId())
            .collect(Collectors.toList());
        if (riesgos_proceso.size() > 0) {
          IndicadorDTO indicador = generateIndicadorDependencia(riesgos_proceso, dependencia);
          indicadores.add(indicador);
        }
      }
    }
    ret.addAll(indicadores);
  }

  private IndicadorDTO generateIndicadorDependencia(List<Riesgo> riesgos_proceso,
      Dependencia dependencia) {
    IndicadorDTO indicador = new IndicadorDTO();
    indicador.setName(dependencia.getNombre());
    indicador.setFirst("Controles programados");
    indicador.setSecond("Controles ejecutados");
    indicador.setTipo_indicador(EIndicador.DEPENDENCIA);
    //count controles if control.aplica = true
    int controles_programados = 0;
    for (Riesgo riesgo : riesgos_proceso) {
      List<ControlResidual> controles = controlResidualService.buscarPorRiesgo(riesgo.getId());
      if (controles != null && controles.size() > 0) {
        controles_programados = controles_programados + controles.size();
      }
    }
    indicador.setFirst_value(Double.parseDouble(controles_programados + ""));
    Integer count_cumplimiento = riesgoService.countControlesInCumplimientoByDependencia(
        dependencia);
    indicador.setSecond_value(Double.parseDouble(count_cumplimiento + ""));
    if (count_cumplimiento > 0 && controles_programados > 0) {
      Double value = (Double.parseDouble(count_cumplimiento + "") / controles_programados) * 100;
      indicador.setValue(value);
      indicador.setMessage("El " + new DecimalFormat("#.##").format(indicador.getValue())
          + "% de los controles programados han sido ejecutados");
    } else {
      indicador.setMessage("El 0% de los controles programados han sido ejecutados");
    }
    return indicador;
  }

  private IndicadorDTO generateIndicadorProceso(List<Riesgo> riesgos_proceso,
      TipoProceso tipo_proceso) {
    IndicadorDTO indicador = new IndicadorDTO();
    indicador.setName(tipo_proceso.getNombre());
    indicador.setFirst("Controles programados");
    indicador.setSecond("Controles ejecutados");
    indicador.setTipo_indicador(EIndicador.PROCESO);
    //count controles if control.aplica = true
    int controles_programados = 0;
    for (Riesgo riesgo : riesgos_proceso) {
      List<ControlResidual> controles = controlResidualService.buscarPorRiesgo(riesgo.getId());
      if (controles != null && controles.size() > 0) {
        controles_programados = controles_programados + controles.size();
      }
    }
    indicador.setFirst_value(Double.parseDouble(controles_programados + ""));
    Integer count_cumplimiento = riesgoService.countControlesInCumplimientoByProceso(tipo_proceso);
    indicador.setSecond_value(Double.parseDouble(count_cumplimiento + ""));
    if (count_cumplimiento > 0 && controles_programados > 0) {
      Double value = (Double.parseDouble(count_cumplimiento + "") / controles_programados) * 100;
      indicador.setValue(value);
      indicador.setMessage("El " + new DecimalFormat("#.##").format(indicador.getValue())
          + "% de los controles programados han sido ejecutados");
    } else {
      indicador.setMessage("El 0% de los controles programados han sido ejecutados");
    }
    return indicador;
  }

  private IndicadorDTO generateIndicadorGeneral(List<Riesgo> riesgos) {
    Integer count_cumplimiento = 0;
    for (Riesgo riesgo : riesgos) {
      Boolean resCumplimiento = getCumplimientoRiesgo(riesgo);
      if (resCumplimiento) {
        count_cumplimiento += 1;
      }
    }
    IndicadorDTO general = new IndicadorDTO();
    general.setName("Riesgos identificados / Riesgos gestionados");
    general.setFirst("Riesgos identificados");
    general.setSecond("Riesgos gestionados");
    general.setFirst_value(Double.parseDouble(riesgos.size() + ""));
    general.setSecond_value(Double.parseDouble(count_cumplimiento + ""));
    general.setTipo_indicador(EIndicador.GENERAL);
    if (count_cumplimiento > 0) {
      Double value = (Double.parseDouble(count_cumplimiento + "") / riesgos.size()) * 100;
      general.setValue(value);
      general.setMessage("El " + new DecimalFormat("#.##").format(general.getValue())
          + "% de los riesgos identificados han sido gestionados");
    } else {
      general.setMessage("El 0% de los riesgos identificados han sido gestionados");
    }
    return general;
  }

  public Boolean getCumplimientoRiesgo(Riesgo riesgo) {
    List<ControlResidual> controles = controlResidualService.buscarPorRiesgo(riesgo.getId());
    Boolean resCumplimiento = true;
    for (ControlResidual control : controles) {
      // TODO: Cargar evidencia del control ya que la propiedad se puso transient ya
      //  que genera error al momento de guardar el control residual.
      Evidencia evidencia = control.getEvidencia();
      if (evidencia != null) {
        Boolean cumplimiento_oci = evidencia.getCumplimiento_oci();
        Boolean cumplimiento_opdi = evidencia.getCumplimiento_opdi();
        if (!(cumplimiento_oci && cumplimiento_opdi)) {
          resCumplimiento = false;
          break;
        }
      }
    }
    return resCumplimiento;
  }

  public Boolean getCorregidoObservacionesEvidencia(Riesgo riesgo) {
    List<ControlResidual> controles = controlResidualService.buscarPorRiesgo(riesgo.getId());
    Boolean resCumplimiento = true;
    for (ControlResidual control : controles) {
      Evidencia evidencia = control.getEvidencia();
      if (evidencia != null) {
        for (EvidenciaObservacion eo : evidencia.getEvidenciaObservacion()) {
          if (!eo.getObservacion().getCorregida()) {
            resCumplimiento = false;
            break;
          }
        }
      }
    }
    return resCumplimiento;
  }

  public Boolean getCorregidoObservacionesRiesgo(Riesgo riesgo) {
    Boolean resCumplimiento = true;
    for (RiesgoObservacion eo : riesgo.getRiesgoObservacion()) {
      if (!eo.getObservacion().getCorregida()) {
        resCumplimiento = false;
        break;
      }
    }
    return resCumplimiento;
  }

  public Boolean getCumplimientoControl(ControlResidual control) {
    Boolean resCumplimiento = true;
    Evidencia evidencia = control.getEvidencia();
    if (evidencia != null) {
      Boolean cumplimiento_oci = evidencia.getCumplimiento_oci();
      Boolean cumplimiento_opdi = evidencia.getCumplimiento_opdi();
      if (!(cumplimiento_oci && cumplimiento_opdi)) {
        resCumplimiento = false;
      }
    } else {
      resCumplimiento = false;
    }
    return resCumplimiento;
  }

  @Override
  public void actualizarMayorConsecuencia(final Long idRiesgo, final String consecuencia) {
    final Riesgo riesgo = riesgoService.buscarPorId(idRiesgo).orElseThrow(() ->new MarucError(
        HttpStatus.NOT_FOUND, "RiesgoManagerDefaultFacade::actualizarConsecuenciaMayor", "No existe el riesgo con id: " + idRiesgo));
    riesgo.setMayorConsecuencia(consecuencia);
    riesgoService.actualizarRiesgo(riesgo);
  }
}
