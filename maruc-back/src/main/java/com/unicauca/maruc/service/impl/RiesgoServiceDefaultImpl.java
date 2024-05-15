package com.unicauca.maruc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unicauca.maruc.domain.model.Dependencia;
import com.unicauca.maruc.domain.model.OpcionConsecuenciaRiesgo;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.dto.riesgos.RiesgoDTO;
import com.unicauca.maruc.dto.riesgos.SearchRiesgosDTO.Search;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.repositories.OpcionConsecuenciaRiesgoDAO;
import com.unicauca.maruc.repositories.RiesgoDAO;
import com.unicauca.maruc.service.RiesgoService;

@Service
public class RiesgoServiceDefaultImpl implements RiesgoService {

  @Autowired
  private RiesgoDAO riesgoDAO;

  @Autowired
  private OpcionConsecuenciaRiesgoDAO opcionConsecuenciaRiesgoDAO;

  @Override
  public Optional<Riesgo> buscarPorId(final Long id) {
    return riesgoDAO.findById(id);
  }

  @Override
  public Page<Riesgo> filterByProceso(final long key, final int page, final int size) {
    return riesgoDAO.findByTipoProcesoId(PageRequest.of(page, size, Sort.by("fechaCreacion")), key);
  }

  @Override
  public Page<RiesgoDTO> filter(Search search, int page, int size) {    
    return riesgoDAO.filter(
      PageRequest.of(page, size, Sort.by("fechaCreacion")), 
      search.getKey(), 
      search.getTipo_riesgo(), 
      search.getProceso(),
      search.getAll())
        .map(RiesgoDTO::convert);
  }

  @Override
  public Page<Riesgo> filterByTipoRiesgo(final long key, final int page, final int size) {
    return riesgoDAO.findByTipoRiesgoId(PageRequest.of(page, size, Sort.by("fechaCreacion")), key);
  }

  @Override
  public void guardarConseucenciasDelRiesgo(
      final OpcionConsecuenciaRiesgo opcionConsecuenciaRiesgo) {
    opcionConsecuenciaRiesgoDAO.save(opcionConsecuenciaRiesgo);
  }

  @Override
  public Riesgo actualizarRiesgo(final Riesgo riesgo) {
    return this.riesgoDAO.save(riesgo);
  }

  @Override
  @Transactional
  public Riesgo guardarInformacionInicial(final Riesgo riesgo) {
    return riesgoDAO.save(riesgo);
  }

  @Override
  public Page<Riesgo> listarByNombre(final String nombre, final int page, final int size) {
    return riesgoDAO.findByNombreContainingIgnoreCase(
        PageRequest.of(page, size, Sort.by("fechaCreacion")), nombre);
  }

  @Override
  public Page<RiesgoDTO> listarRiesgosDTO(final int page, final int size, final Boolean all) {
    
    Page<RiesgoDTO> res = all? 
      riesgoDAO.findAll(PageRequest.of(page, size, Sort.by("fechaCreacion")))
        .map(RiesgoDTO::convert) :
      riesgoDAO.findByestatusInformacionRiesgo_valorGreaterThanEqual(PageRequest.of(page, size, Sort.by("fechaCreacion")), 8)
        .map(RiesgoDTO::convert);
    return res;
  }
  
  @Override
  public Page<RiesgoDTO> listarRiesgosLiderDTO(final int page, final int size, final Boolean all, final Long id) {
    
    Page<RiesgoDTO> res = all? 
      riesgoDAO.findByUsuarioId(PageRequest.of(page, size, Sort.by("fechaCreacion")), id)
        .map(RiesgoDTO::convert) :
      riesgoDAO.findByestatusInformacionRiesgo_valorGreaterThanEqualAndUsuarioId(PageRequest.of(page, size, Sort.by("fechaCreacion")), 8, id)
        .map(RiesgoDTO::convert);
    return res;
  }

  @Override
  public List<RiesgoDTO> listarRiesgos() {
    List<Riesgo> res = riesgoDAO.findByestatusInformacionRiesgo_valorGreaterThanEqual(7);
    List<RiesgoDTO> ret = new ArrayList<>();
    for (Riesgo riesgo : res) {
        RiesgoDTO toAdd = new RiesgoDTO();
        riesgo.getControles();
        riesgo.getTipoProceso();
        riesgo.getTipoRiesgo();
        riesgo.getTipoTratamiento();
        toAdd.setRiesgo(riesgo);
        ret.add(toAdd);
    }
    return ret;
  }

  @Override
  public Page<Riesgo> listarRiesgos(final int page, final int size) {
    return riesgoDAO.findAll(PageRequest.of(page, size, Sort.by("fechaCreacion")));
  }

  @Override
  public Riesgo SeleccionarRiesgo(final long id) {
    final Optional<Riesgo> riesgo = riesgoDAO.findById(id);
    if (riesgo.isPresent()) {
      return riesgo.get();
    }
    throw new EntidadNoExisteException(
        String.format("El riesgo con id %s no se encuentra registrado.", id));
  }

  @Override
  public List<Riesgo> listarRiesgosByLiderProceso(Long id_lider_proceso) {
    return riesgoDAO.findByUsuarioId(id_lider_proceso);
  }

  @Override
  public Integer countControlesInCumplimientoByProceso(TipoProceso tipo_proceso)
  {
    return riesgoDAO.countControlesInCumplimientoByProceso(tipo_proceso.getId());
  }

  @Override
  public Integer countControlesInCumplimientoByDependencia(Dependencia dependencia)
  {
    return riesgoDAO.countControlesInCumplimientoByDependencia(dependencia.getId());
  }

  @Override
  public List<Riesgo> findByestatusInformacionRiesgo_valorGreaterThanEqual(Integer valor)
  {
    return riesgoDAO.findByestatusInformacionRiesgo_valorGreaterThanEqual(valor);
  }
}
