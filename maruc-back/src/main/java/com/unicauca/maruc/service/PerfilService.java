package com.unicauca.maruc.service;

import com.unicauca.maruc.dto.usuarios.request.ChangePasswordReuest;

public interface PerfilService {
  boolean actualizarPassword(ChangePasswordReuest reuest);
}
