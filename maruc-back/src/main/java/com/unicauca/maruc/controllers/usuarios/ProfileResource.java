package com.unicauca.maruc.controllers.usuarios;

import com.unicauca.maruc.dto.usuarios.request.ChangePasswordReuest;
import com.unicauca.maruc.facade.PerfilFacade;
import com.unicauca.maruc.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileResource {

  @Autowired
  private PerfilFacade perfilFacade;

  @PutMapping
  public ResponseEntity<Boolean> cambiarPassword(@RequestBody final ChangePasswordReuest request) {
    return ResponseUtils.ok(perfilFacade.cambiarPassword(request));
  }
}
