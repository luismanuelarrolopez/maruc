package com.unicauca.maruc.controllers.catalogo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.facade.AdministrarCatalogosFacade;
import com.unicauca.maruc.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tipos-proceso")
@CrossOrigin("*")
public class TipoProcesoController {

  @Autowired
  private AdministrarCatalogosFacade facade;

  @GetMapping
  @Operation(summary = "Obtiene los tipos de proceso configurados")
  public ResponseEntity<List<TipoProceso>> listarTiposProceso() {
    return ResponseUtils.ok(facade.listarTipoProceso());
  }
}
