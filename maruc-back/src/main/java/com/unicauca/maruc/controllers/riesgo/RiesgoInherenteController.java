package com.unicauca.maruc.controllers.riesgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.dto.riesgos.RiesgoInherenteDTO;
import com.unicauca.maruc.facade.RiesgoManagerFacade;
import com.unicauca.maruc.util.ResponseUtils;

@RestController
@RequestMapping("/riesgos/riesgo-inherente")
@CrossOrigin("*")
public class RiesgoInherenteController {

  @Autowired
  private RiesgoManagerFacade riesgoManagerFacade;

  @GetMapping("/{idRiesgo}")
  public ResponseEntity<RiesgoInherenteDTO> calcularRiesgoInherente(
      @PathVariable("idRiesgo") final Long idRiesgo) {
    return ResponseUtils.ok(riesgoManagerFacade.calcularRiesgoInherente(idRiesgo));
  }
}
