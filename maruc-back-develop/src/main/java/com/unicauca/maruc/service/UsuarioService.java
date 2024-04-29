package com.unicauca.maruc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.exceptions.EntidadYaExisteException;
import com.unicauca.maruc.exceptions.EntityNotFountException;

public interface UsuarioService extends BaseService<Usuario> {

  Page<Usuario> fetchUsuarios(int offset, int limit) throws EntityNotFountException;

  void verificarExisteUsuarioConEmail(String email) throws EntidadYaExisteException;

  List<Usuario> buscarUsuariosByRolCodigo(String rol);

  Optional<Usuario> findByUuid(String uuid);

  Optional<Usuario> findByEmail(String email);
}
