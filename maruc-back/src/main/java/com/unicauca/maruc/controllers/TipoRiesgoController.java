package com.unicauca.maruc.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.domain.model.TipoRiesgo;
import com.unicauca.maruc.facade.RiesgoManagerFacade;
import com.unicauca.maruc.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tipo-riesgo")
@CrossOrigin("*")
public class TipoRiesgoController {

  @Autowired
  private RiesgoManagerFacade riesgoManagerFacade;

  @GetMapping
  @Operation(summary = "Retorna el catálogo de los tipos de riesgos registrados")
  public ResponseEntity<List<TipoRiesgo>> listarTiposDeRiesgo() {
    return ResponseUtils.ok(riesgoManagerFacade.buscarTiposDeRiesgo());
  }
}
