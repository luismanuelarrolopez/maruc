package com.unicauca.maruc.controllers.riesgo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.maruc.dto.riesgos.ControlResidualDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoResidualDTO;
import com.unicauca.maruc.facade.RiesgoManagerFacade;
import com.unicauca.maruc.util.ResponseUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/riesgos/residual")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RiesgoResidualController {

  private final RiesgoManagerFacade riesgoManagerFacade;

  @GetMapping("/{idRiesgo}")
  public ResponseEntity<Object> obtenerNivelRiesgoResidual(@PathVariable("idRiesgo") final Long idRiesgo) {
    return ResponseUtils.ok(riesgoManagerFacade.calcularNivelRiesgoResidual(idRiesgo));
  }

  @PostMapping
  public ResponseEntity<Object> guardarRiesgoResidual(@Valid @RequestBody final RiesgoResidualDTO riesgoResidualDTO) {
    return ResponseUtils.ok(riesgoManagerFacade.guardarRiesgoResidual(riesgoResidualDTO));
  }

  @PutMapping("/{idRiesgoResidual}")
  public ResponseEntity<Object> actualizarRiesgoResidual(
      @Valid @NotNull(message = "Debe pasar el id del riesgo residual") @PathVariable("idRiesgoResidual") final Long idRiesgoResidual,
      @Valid @RequestBody final RiesgoResidualDTO riesgoResidualDTO) {
    return ResponseUtils.ok(riesgoManagerFacade.actualizarRiesgoResidual(idRiesgoResidual, riesgoResidualDTO));
  }

  @GetMapping("/controles/{idRiesgoResidual}")
  public ResponseEntity<Object> obtenerControles(@PathVariable("idRiesgoResidual") final Long idRiesgo) {
    return ResponseUtils.ok(riesgoManagerFacade.buscarPorRiesgoResidual(idRiesgo));
  }

  //@PostMapping("/controles/{idRiesgoResidual}")
  public ResponseEntity<Object> agregarControl(@PathVariable("idRiesgoResidual") final Long idRiesgoResidual,
      @Valid @RequestBody final ControlResidualDTO controlResidualDTO) {
    return ResponseUtils.ok(riesgoManagerFacade.guardarControlResidual(idRiesgoResidual, controlResidualDTO));
  }

  @DeleteMapping("/controles/{idControlResidual}")
  public ResponseEntity<Object> eliminarControl(
      @Valid @NotNull(message = "Debe ingresar el id del control residual") @PathVariable("idControlResidual") final Long idControlResidual) {
    return ResponseUtils.ok(riesgoManagerFacade.eliminarControlResidual(idControlResidual));
  }

  @PutMapping("/controles/{idControlResidual}")
  public ResponseEntity<Object> editarControlResidual(@PathVariable("idControlResidual") final Long idControlResidual,
      @Valid @RequestBody final ControlResidualDTO controlResidualDTO) {
    return ResponseUtils.ok(riesgoManagerFacade.editarControlResidual(idControlResidual, controlResidualDTO));
  }
}
