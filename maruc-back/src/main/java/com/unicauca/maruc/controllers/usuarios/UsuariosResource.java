package com.unicauca.maruc.controllers.usuarios;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.dto.GuardarUsuarioDTO;
import com.unicauca.maruc.dto.usuarios.request.ActualizarPassword;
import com.unicauca.maruc.dto.usuarios.request.UsuarioRequest;
import com.unicauca.maruc.dto.usuarios.response.PerfilUsuarioResponse;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.facade.AdministrarUsuariosFacade;
import com.unicauca.maruc.util.ResponseUtils;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("usuarios")
@CrossOrigin("*")
@Slf4j
public class UsuariosResource {

  @Autowired
  private AdministrarUsuariosFacade administrarUsuariosFacade;

  @PostMapping
  @Operation(summary = "Registra un nuevo usuario en el sitema")
  public ResponseEntity<Usuario> usuario(@RequestBody final GuardarUsuarioDTO guardarUsuarioDTO) throws IOException {
    return ResponseUtils.ok(administrarUsuariosFacade.guardarUsuario(guardarUsuarioDTO));
  }

  @GetMapping(params = { "page", "size" })
  @Operation(summary = "Lista los usuarios registrados en el sitema de forma paginada")
  public Page<Usuario> listarUsuariosPaginados(@RequestParam(name = "page") final int page,
      @RequestParam(name = "size") final int size) throws EntityNotFountException {
    return this.administrarUsuariosFacade.listarUsuarioPaginados(page, size);
  }

  @PutMapping("verificacion")
  public ResponseEntity<Object> actualizarPassword(@Valid @RequestBody final ActualizarPassword actualizarPassword) {
    return ResponseUtils.ok(administrarUsuariosFacade.actualizarPassword(actualizarPassword));
  }

  @PutMapping("recovery")
  public ResponseEntity<Object> recuperarPassword(@Valid @RequestBody final ActualizarPassword actualizarPassword) {
    return ResponseUtils.ok(administrarUsuariosFacade.actualizarPassword(actualizarPassword));
  }

  @PostMapping("forgot")
  public ResponseEntity<Object> forgot(@Valid @NotNull @RequestBody final String email) throws IOException {
    administrarUsuariosFacade.enviarCorreoRecuperarPassword(email);
    return ResponseUtils.ok("ok");
  }

  @GetMapping("profile/{email}")
  public ResponseEntity<PerfilUsuarioResponse> obtenerPerfil(@PathVariable("email") final String email) {
    return ResponseUtils.ok(administrarUsuariosFacade.buscarPerfilUsuario(email));
  }

  @PutMapping("profile")
  public ResponseEntity<Boolean> actualizarPerfil(@Valid @RequestBody final PerfilUsuarioResponse body) {
    return ResponseUtils.ok(administrarUsuariosFacade.actualizarPerfil(body));
  }

  @GetMapping("/{idUsuario}")
  public ResponseEntity<UsuarioRequest> buscarUsuario(@PathVariable("idUsuario") final Long id) {
    return ResponseUtils.ok(administrarUsuariosFacade.buscarPor(id));
  }

  @PutMapping
  public ResponseEntity<Boolean> actualizarUsuario(@RequestBody @Valid final UsuarioRequest usuarioRequest) {
    return ResponseUtils.ok(administrarUsuariosFacade.actualizarUsuario(usuarioRequest));
  }

  @DeleteMapping("/{idUsuario}")
  public ResponseEntity<Boolean> deleteUsuario(@PathVariable("idUsuario") final Long id) {
    return ResponseUtils.ok(administrarUsuariosFacade.deleteUsuario(id));
  }
}
