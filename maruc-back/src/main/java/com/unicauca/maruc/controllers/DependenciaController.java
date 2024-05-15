package com.unicauca.maruc.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.domain.model.Dependencia;
import com.unicauca.maruc.facade.AdministrarUsuariosFacade;
import com.unicauca.maruc.util.ResponseUtils;

@RestController
@RequestMapping("dependencias")
@CrossOrigin("*")
public class DependenciaController {

  @Autowired
  private AdministrarUsuariosFacade administrarUsuariosFacade;

  @GetMapping
  public ResponseEntity<List<Dependencia>> listarConsecuencias() {
    return ResponseUtils.ok(administrarUsuariosFacade.listarDependencias());
  }
}
