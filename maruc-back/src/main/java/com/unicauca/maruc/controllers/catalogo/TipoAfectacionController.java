package com.unicauca.maruc.controllers.catalogo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.domain.model.TipoAfectacion;
import com.unicauca.maruc.facade.AdministrarCatalogosFacade;
import com.unicauca.maruc.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tipos-afectacion")
@CrossOrigin("*")
public class TipoAfectacionController {

  @Autowired
  private AdministrarCatalogosFacade facade;

  @GetMapping
  @Operation(summary = "Obtiene los tipos de afectación configurados")
  public ResponseEntity<List<TipoAfectacion>> buscarTiposAfectacion() {
    return ResponseUtils.ok(facade.buscarTiposAfectacion());
  }
}
