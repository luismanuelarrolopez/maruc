package com.unicauca.maruc.facade;

import com.unicauca.maruc.dto.usuarios.request.ChangePasswordReuest;
import com.unicauca.maruc.exceptions.MarucError;

public interface PerfilFacade {

  boolean cambiarPassword(ChangePasswordReuest request) throws MarucError;
}
