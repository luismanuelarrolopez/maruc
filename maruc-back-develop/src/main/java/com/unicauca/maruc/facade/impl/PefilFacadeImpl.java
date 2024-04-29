package com.unicauca.maruc.facade.impl;

import com.unicauca.maruc.dto.usuarios.request.ChangePasswordReuest;
import com.unicauca.maruc.exceptions.MarucError;
import com.unicauca.maruc.facade.PerfilFacade;
import com.unicauca.maruc.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PefilFacadeImpl implements PerfilFacade {

  @Autowired
  private PerfilService perfilService;

  @Override
  public boolean cambiarPassword(ChangePasswordReuest request) throws MarucError {
    return perfilService.actualizarPassword(request);
  }
}
