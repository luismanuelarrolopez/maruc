package com.unicauca.maruc.facade;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.unicauca.maruc.domain.model.Dependencia;
import com.unicauca.maruc.domain.model.Rol;
import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.dto.GuardarUsuarioDTO;
import com.unicauca.maruc.dto.usuarios.request.ActualizarPassword;
import com.unicauca.maruc.dto.usuarios.request.UsuarioRequest;
import com.unicauca.maruc.dto.usuarios.response.PerfilUsuarioResponse;
import com.unicauca.maruc.exceptions.EntityNotFountException;

public interface AdministrarUsuariosFacade {

  List<Rol> listarRoles();

  List<Usuario> listarUsuarios();

  List<Usuario> listarUsuariosByRolCodigo(String rol);

  Page<Usuario> listarUsuarioPaginados(int page, int size) throws EntityNotFountException;

  Usuario guardarUsuario(GuardarUsuarioDTO guardarUsuarioDTO) throws IOException;

  UsuarioRequest buscarPor(Long id);

  List<Dependencia> listarDependencias();

  boolean actualizarPassword(ActualizarPassword actualizarPasswordDto);

  void enviarCorreoRecuperarPassword(String email) throws IOException;

  PerfilUsuarioResponse buscarPerfilUsuario(String email);

  boolean actualizarPerfil(PerfilUsuarioResponse perfilUsuario);

  boolean actualizarUsuario(UsuarioRequest usuarioRequest);

  boolean deleteUsuario(Long id);
}
