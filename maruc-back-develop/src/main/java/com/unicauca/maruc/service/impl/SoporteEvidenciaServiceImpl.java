package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.SoporteEvidencia;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.exceptions.EntidadYaExisteException;
import com.unicauca.maruc.repositories.SoporteEvidenciaDAO;
import com.unicauca.maruc.service.SoporteEvidenciaService;

@Service
public class SoporteEvidenciaServiceImpl implements SoporteEvidenciaService {

  @Autowired
  private SoporteEvidenciaDAO SoporteEvidenciaDAO;

  @Override
  public SoporteEvidencia guardarSoporteEvidencia(SoporteEvidencia soporte_evidencia) {
    if (Objects.isNull(soporte_evidencia.getId())) {
      return SoporteEvidenciaDAO.save(soporte_evidencia);
    }
    throw new EntidadYaExisteException(
        String.format("El soporte de evidencia con id %s ya se encuentra registrado.",
            soporte_evidencia.getId()));
  }

  @Override
  public List<SoporteEvidencia> listarSoporteEvidencias() {
    return SoporteEvidenciaDAO.findAll();
  }

  @Override
  public SoporteEvidencia SeleccionarSoporteEvidencia(Long id) {
    Optional<SoporteEvidencia> soporte_evidencia = SoporteEvidenciaDAO.findById(id);
    if (soporte_evidencia.isPresent()) {
      return soporte_evidencia.get();
    }
    throw new EntidadNoExisteException(
        String.format("El soporte de evidencia con id %s no se encuentra registrado.", id));
  }

  @Override
  public Boolean EliminarSoporteEvidencia(Long id) {
    Optional<SoporteEvidencia> soporte_evidencia = SoporteEvidenciaDAO.findById(id);
    if (soporte_evidencia.isPresent()) {
      SoporteEvidenciaDAO.deleteById(id);
      return true;
    } else {
      throw new EntidadNoExisteException(
          String.format("El soporte de evidencia con id %s no se encuentra registrado.", id));
    }
  }

  @Override
  public SoporteEvidencia ActualizarSoporteEvidencia(SoporteEvidencia soporteEvidencia) {
    Optional<SoporteEvidencia> soporte_evidencia = SoporteEvidenciaDAO.findById(soporteEvidencia.getId());
    if(soporte_evidencia.isPresent()) {
      SoporteEvidencia data = soporte_evidencia.get();
      data.setNombre(soporteEvidencia.getNombre());
      return SoporteEvidenciaDAO.save(data);
    } else {
      throw new EntidadNoExisteException(
          String.format("El soporte de evidencia con id %s no se encuentra registrado.", soporteEvidencia.getId()));
    }
  }

  @Override
  public List<SoporteEvidencia> SeleccionarSoportesEvidenciaByIdEvidencia(Long id) {
    return SoporteEvidenciaDAO.findByEvidencia(id);
  }

}
