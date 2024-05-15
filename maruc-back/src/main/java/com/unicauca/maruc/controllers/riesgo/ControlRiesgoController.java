package com.unicauca.maruc.controllers.riesgo;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.dto.riesgos.ControlDTO;
import com.unicauca.maruc.facade.ControlFacade;
import com.unicauca.maruc.facade.RiesgoManagerFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/riesgos/controles")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ControlRiesgoController {

  private final RiesgoManagerFacade riesgosFacade;
  private final ControlFacade controlFacade;

  @Operation(summary = "Registra los controles existentes y sus valores al riesgo identificado")
  @PostMapping
  public boolean agregarControles(@RequestBody final List<ControlDTO> controles) {
    return riesgosFacade.guardarControles(controles);
  }

  @GetMapping("/{idRiesgo}")
  public ResponseEntity<List<Control>> buscarControles(
      @PathVariable("idRiesgo") final Long idRiesgo) {
    return new ResponseEntity<>(controlFacade.buscarPorIdRiesgo(idRiesgo), HttpStatus.OK);
  }
}
