package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicauca.maruc.domain.model.OpcionConsecuencia;
import com.unicauca.maruc.domain.model.OpcionConsecuenciaRiesgo;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaRiesgoDTO;
import com.unicauca.maruc.repositories.OpcionConsecuenciaDAO;
import com.unicauca.maruc.repositories.OpcionConsecuenciaRiesgoDAO;
import com.unicauca.maruc.service.OpcionConsecuenciaService;

@Service
public class OpcionConsecuenciaServiceDefaultImpl implements OpcionConsecuenciaService {

  @Autowired
  private OpcionConsecuenciaDAO consecuenciaDAO;
  @Autowired
  private OpcionConsecuenciaRiesgoDAO opcionConsecuenciaRiesgoDAO;

  @Override
  public void guardarTodo(final Set<OpcionConsecuencia> opciones) {
    consecuenciaDAO.saveAll(opciones);
  }

  @Override
  public List<OpcionConsecuenciaRiesgoDTO> buscarConsecuenciasRiesgo(final Long idRiesgo) {
    return opcionConsecuenciaRiesgoDAO.buscarConsecuenciasRiesgo(idRiesgo);
  }

  @Override
  public Optional<OpcionConsecuenciaRiesgo> buscarPorId(final Long id) {
    return opcionConsecuenciaRiesgoDAO.findById(id);
  }

  @Override
  public OpcionConsecuencia buscarOpcionPorId(Long id) {
    return consecuenciaDAO.findById(id).orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public void eliminar(final Long id) {
    final OpcionConsecuenciaRiesgo ocr =
        opcionConsecuenciaRiesgoDAO.findById(id).orElseThrow(IllegalArgumentException::new);
    opcionConsecuenciaRiesgoDAO.delete(ocr);
  }

  @Override
  public void eliminarOpcionConsecuencia(Long id) {
    consecuenciaDAO.deleteById(id);
  }

  @Override
  public OpcionConsecuencia guardar(OpcionConsecuencia oc) {
    return consecuenciaDAO.save(oc);
  }

  @Override
  public OpcionConsecuencia buscarPorIdConsecuencia(Long id) {
    return consecuenciaDAO.findByConsecuenciaId(id);
  }

  @Override
  public void eliminarOpcionConsecuenciaPorIdConsecuencia(final Long id) {
    consecuenciaDAO.deleteByConsecuenciaId(id);
  }
}
