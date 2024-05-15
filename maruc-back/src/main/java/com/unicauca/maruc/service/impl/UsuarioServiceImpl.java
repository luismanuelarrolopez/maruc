package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.exceptions.CodigoError;
import com.unicauca.maruc.exceptions.EntidadYaExisteException;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.repositories.UsuarioDAO;
import com.unicauca.maruc.service.UsuarioService;

@Service
public class UsuarioServiceImpl extends DefaultBaseService<Usuario> implements UsuarioService {

  @Autowired
  private UsuarioDAO usuarioDAO;

  @Override
  public Page<Usuario> fetchUsuarios(final int page, final int limit)
      throws EntityNotFountException {
    final Page<Usuario> result = this.usuarioDAO
        .findAll(PageRequest.of(page, limit, Sort.by("fechaCreacion").descending()));
    return result;
  }

  @Override
  public void verificarExisteUsuarioConEmail(final String email) throws EntidadYaExisteException {
    final Optional<Usuario> usuario = usuarioDAO.findByEmail(email);
    if (usuario.isPresent())
      throw new EntidadYaExisteException(CodigoError.ENTIDAD_YA_EXISTE);
  }

  @Override
  public List<Usuario> buscarUsuariosByRolCodigo(String rol) {
    return usuarioDAO.findByRolCodigo(rol);
  }

  @Override
  public Optional<Usuario> findByUuid(String uuid) {
    return usuarioDAO.findByUuid(uuid);
  }

  @Override
  public Optional<Usuario> findByEmail(String email) {
    return usuarioDAO.findByEmail(email);
  }
}
