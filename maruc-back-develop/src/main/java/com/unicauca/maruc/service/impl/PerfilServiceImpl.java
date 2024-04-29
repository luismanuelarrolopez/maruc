package com.unicauca.maruc.service.impl;

import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.dto.usuarios.request.ChangePasswordReuest;
import com.unicauca.maruc.exceptions.MarucError;
import com.unicauca.maruc.repositories.UsuarioDAO;
import com.unicauca.maruc.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PerfilServiceImpl implements PerfilService {

  @Autowired
  private UsuarioDAO usuarioDAO;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public boolean actualizarPassword(final ChangePasswordReuest request) {
    if(!request.getNewPassword().equals((request.getConfirmPassword()))) {
      throw new MarucError(HttpStatus.BAD_REQUEST, "", "La contraseña nueva debe coincidir con la contraseña de confirmación");
    }
    final Usuario usuario = usuarioDAO.findById(request.getIdUsuario()).orElseThrow(IllegalArgumentException::new);
    if (usuario.getPassword().equals(passwordEncoder.encode(request.getNewPassword()))) {
      throw new MarucError(HttpStatus.BAD_REQUEST, "", "Debe ingresar su contraseña actual");
    }
    usuario.setPassword(passwordEncoder.encode(request.getNewPassword()));
    try {
      usuarioDAO.save(usuario);
      return true;
    } catch(Exception e) {
      return false;
    }
  }

  private boolean isEqualToActualPassword(final String password) {
    return usuarioDAO.findByPassword(passwordEncoder.encode(password)).isPresent();
  }
}
