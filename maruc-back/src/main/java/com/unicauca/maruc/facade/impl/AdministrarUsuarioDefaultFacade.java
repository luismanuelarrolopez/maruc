package com.unicauca.maruc.facade.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicauca.maruc.domain.model.Dependencia;
import com.unicauca.maruc.domain.model.Rol;
import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.dto.GuardarUsuarioDTO;
import com.unicauca.maruc.dto.mappers.UsuarioMapper;
import com.unicauca.maruc.dto.usuarios.request.ActualizarPassword;
import com.unicauca.maruc.dto.usuarios.request.UsuarioRequest;
import com.unicauca.maruc.dto.usuarios.response.PerfilUsuarioResponse;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.facade.AdministrarUsuariosFacade;
import com.unicauca.maruc.service.DependenciaService;
import com.unicauca.maruc.service.RolService;
import com.unicauca.maruc.service.UsuarioService;
import com.unicauca.maruc.service.impl.usuarios.NuevoUsuarioEmailService;
import com.unicauca.maruc.service.impl.usuarios.RecuperarPasswordEmailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Transactional
@Slf4j
public class AdministrarUsuarioDefaultFacade implements AdministrarUsuariosFacade {

  @Autowired
  private RolService rolService;
  @Autowired
  private UsuarioService usuarioService;
  @Autowired
  private DependenciaService dependenciaService;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private NuevoUsuarioEmailService nuevoUsuarioEmailService;
  @Autowired
  private RecuperarPasswordEmailService recuperarPasswordEmailService;

  @Override
  @Transactional(readOnly = true)
  public List<Rol> listarRoles() {
    return rolService.buscarTodos();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Usuario> listarUsuarios() {
    return usuarioService.buscarTodos();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Usuario> listarUsuarioPaginados(int page, int size) throws EntityNotFountException {
    return usuarioService.fetchUsuarios(page, size);
  }

  @Override
  public Usuario guardarUsuario(final GuardarUsuarioDTO guardarUsuarioDTO) throws IOException {
    log.info("?=========== Usuario a guardar: {}", guardarUsuarioDTO.toString());
    usuarioService.verificarExisteUsuarioConEmail(guardarUsuarioDTO.getEmail());
    final Usuario usuario = new Usuario();
    usuario.setNombres(guardarUsuarioDTO.getNombres());
    usuario.setApellidos(guardarUsuarioDTO.getApellidos());
    usuario.setEmail(guardarUsuarioDTO.getEmail());
    usuario.setPassword(passwordEncoder.encode(RandomStringUtils.random(8)));
    //usuario.setPassword(passwordEncoder.encode("1234"));
    usuario.setRol(new Rol.Builder().id(guardarUsuarioDTO.getIdRol()).build());
    usuario
        .setDependencia(new Dependencia.Builder().id(guardarUsuarioDTO.getIdDependencia()).build());
    usuario.setUuid(UUID.randomUUID().toString());
    nuevoUsuarioEmailService.sendEmail(usuario);
    return usuarioService.guardar(usuario);
  }

  @Override
  public List<Dependencia> listarDependencias() {
    return dependenciaService.buscarTodos();
  }

  @Override
  public List<Usuario> listarUsuariosByRolCodigo(String rol) {
    return usuarioService.buscarUsuariosByRolCodigo(rol);
  }

  @Override
  public boolean actualizarPassword(final ActualizarPassword actualizarPasswordDto) {
    final Usuario usuario = usuarioService.findByUuid(actualizarPasswordDto.getUuid())
        .orElseThrow(
            () -> new EntityNotFountException("No existe el usuario con uuid: " + actualizarPasswordDto.getUuid()));
    usuario.setPassword(passwordEncoder.encode(actualizarPasswordDto.getPassword()));
    usuario.setVerificado(Boolean.TRUE);
    usuarioService.actualizar(usuario);
    return true;
  }

  @Override
  public void enviarCorreoRecuperarPassword(final String email) throws IOException {
    final Usuario usuario = usuarioService.findByEmail(email)
        .orElseThrow(() -> new EntidadNoExisteException("No existe el usuario con email: " + email));
    recuperarPasswordEmailService.sendEmail(usuario);
  }

  @Override
  public PerfilUsuarioResponse buscarPerfilUsuario(final String email) {
    final Usuario usuario = usuarioService.findByEmail(email)
        .orElseThrow(() -> new EntidadNoExisteException("No existe el usuario con email: " + email));
    return UsuarioMapper.mapToPerfilUsuario(usuario);
  }

  @Override
  public boolean actualizarPerfil(final PerfilUsuarioResponse perfilUsuario) {
    final Usuario usuario = usuarioService.findByEmail(perfilUsuario.getEmail())
        .orElseThrow(() -> new EntidadNoExisteException("No existe el usuario con email: " + perfilUsuario.getEmail()));
    usuario.setNombres(perfilUsuario.getPrimerNombre() + " " + perfilUsuario.getSegundoNombre());
    usuario.setApellidos(perfilUsuario.getPrimerApellido() + " " + perfilUsuario.getSegundoApellido());
    usuarioService.actualizar(usuario);
    return false;
  }

  @Override
  public UsuarioRequest buscarPor(final Long id) {
    return UsuarioMapper.mapFrom(usuarioService.buscarPorId(id)
        .orElseThrow(() -> new EntidadNoExisteException("No existe el usuario con id: " + id)));
  }

  @Override
  public boolean actualizarUsuario(final UsuarioRequest usuarioRequest) {
    final Usuario usuario = usuarioService.buscarPorId(usuarioRequest.getId())
        .orElseThrow(() -> new EntidadNoExisteException("No existe el usuario con id: " + usuarioRequest.getId()));
    usuario.setNombres(usuarioRequest.getNombres());
    usuario.setApellidos(usuarioRequest.getApellidos());
    if (!usuario.getEmail().equals(usuarioRequest.getEmail())) {
      usuarioService.verificarExisteUsuarioConEmail(usuarioRequest.getEmail());
      usuario.setVerificado(false);
      usuario.setEmail(usuarioRequest.getEmail());
    }
    usuario.setDependencia(new Dependencia(usuarioRequest.getIdDependencia()));
    usuario.setRol(new Rol(usuarioRequest.getIdRol()));
    usuarioService.actualizar(usuario);
    return true;
  }

  @Override
  public boolean deleteUsuario(Long id) {
    final Usuario usuario = usuarioService.buscarPorId(id)
        .orElseThrow(() -> new EntidadNoExisteException("No existe el usuario con id: " + id));
    usuarioService.eliminar(usuario);
    return true;
  }

}
