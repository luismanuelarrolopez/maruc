package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.exceptions.EntidadYaExisteException;
import com.unicauca.maruc.repositories.ControlDAO;
import com.unicauca.maruc.service.ControlService;

@Service
public class ControlServiceImpl implements ControlService {

  @Autowired
  private ControlDAO controlDAO;

  @Override
  public List<Control> buscarPorIdRiesgo(final Long idRiesgo) {
    return controlDAO.findByRiesgoId(idRiesgo);
  }

  @Override
  public Control guardarControl(final Control control) {
    if (Objects.isNull(control.getId())) {
      return controlDAO.save(control);
    }
    throw new EntidadYaExisteException(
        String.format("El control con id %s ya se encuentra registrado.", control.getId()));
  }

  @Override
  public List<Control> listarControles() {
    return controlDAO.findAll();
  }

  @Override
  public Control seleccionarControl(final Long id) {
    final Optional<Control> control = controlDAO.findById(id);
    if (control.isPresent()) {
      return control.get();
    }
    throw new EntidadNoExisteException(
        String.format("El control con id %s no se encuentra registrado.", id));
  }

}
