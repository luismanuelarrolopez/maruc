package com.unicauca.maruc.facade.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.facade.ControlFacade;
import com.unicauca.maruc.service.ControlService;

@Component
public class ControlImpl implements ControlFacade {

  @Autowired
  private ControlService controlService;

  @Override
  public List<Control> buscarPorIdRiesgo(final Long idRiesgo) {
    return controlService.buscarPorIdRiesgo(idRiesgo);
  }

  @Override
  public Control encontrarControl(final Long id) {
    // Buscamos un control por su identificador
    return controlService.seleccionarControl(id);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Control guardarControl(final Control control) {
    return controlService.guardarControl(control);
  }

  @Override
  public List<Control> listarControles() {
    // Listamos todos los controles
    return controlService.listarControles();
  }
}

