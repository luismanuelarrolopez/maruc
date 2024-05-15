package com.unicauca.maruc.controllers.usuarios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.domain.model.Rol;
import com.unicauca.maruc.facade.AdministrarUsuariosFacade;
import com.unicauca.maruc.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("roles")
@RestController
@CrossOrigin("*")
public class RolResource {

  @Autowired
  private AdministrarUsuariosFacade administrarUsuariosFacade;

  @GetMapping
  @Operation(summary = "Devuelve la lista de roles registrados en la aplicación")
  public ResponseEntity<List<Rol>> listarRoles() {
    return ResponseUtils.ok(administrarUsuariosFacade.listarRoles());
  }
}
