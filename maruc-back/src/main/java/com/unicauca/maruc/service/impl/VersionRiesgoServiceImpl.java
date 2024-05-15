package com.unicauca.maruc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.VersionRiesgo;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.exceptions.EntidadYaExisteException;
import com.unicauca.maruc.repositories.VersionRiesgoDAO;
import com.unicauca.maruc.service.VersionRiesgoService;

@Service
public class VersionRiesgoServiceImpl implements VersionRiesgoService {

  @Autowired
  private VersionRiesgoDAO VersionRiesgoDAO;

  @Override
  public VersionRiesgo guardarVersionRiesgo(VersionRiesgo soporte_evidencia) {
    if (Objects.isNull(soporte_evidencia.getId())) {
      return VersionRiesgoDAO.save(soporte_evidencia);
    }
    throw new EntidadYaExisteException(
        String.format("El soporte de evidencia con id %s ya se encuentra registrado.",
            soporte_evidencia.getId()));
  }

  @Override
  public List<VersionRiesgo> listarVersionRiesgos() {
    return VersionRiesgoDAO.findAll();
  }

  @Override
  public VersionRiesgo SeleccionarVersionRiesgo(Long id) {
    Optional<VersionRiesgo> soporte_evidencia = VersionRiesgoDAO.findById(id);
    if (soporte_evidencia.isPresent()) {
      return soporte_evidencia.get();
    }
    throw new EntidadNoExisteException(
        String.format("El soporte de evidencia con id %s no se encuentra registrado.", id));
  }

  @Override
  public Boolean EliminarVersionRiesgo(Long id) {
    Optional<VersionRiesgo> soporte_evidencia = VersionRiesgoDAO.findById(id);
    if (soporte_evidencia.isPresent()) {
      VersionRiesgoDAO.deleteById(id);
      return true;
    } else {
      throw new EntidadNoExisteException(
          String.format("El soporte de evidencia con id %s no se encuentra registrado.", id));
    }
  }

  @Override
  public VersionRiesgo ActualizarVersionRiesgo(VersionRiesgo versionRiesgo) {
    VersionRiesgo version = VersionRiesgoDAO.getById(versionRiesgo.getId());
    version.setFechaActualizacion(new Date());
    version.setNombre(versionRiesgo.getNombre());
    if (Objects.isNull(versionRiesgo.getId())) {
      throw new EntidadNoExisteException(
          String.format("El soporte de evidencia con id %s no se encuentra registrado.",
              versionRiesgo.getId()));
    }
    return VersionRiesgoDAO.save(version);
  }
}
