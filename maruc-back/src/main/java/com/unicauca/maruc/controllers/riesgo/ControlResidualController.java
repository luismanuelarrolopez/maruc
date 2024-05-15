package com.unicauca.maruc.controllers.riesgo;

import com.unicauca.maruc.dto.riesgos.GuardarControlResidualRequest;
import com.unicauca.maruc.facade.ControlResidualFacade;
import com.unicauca.maruc.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/riesgos/controles/residuales")
@CrossOrigin("*")
@Slf4j
public class ControlResidualController {

  @Autowired
  private ControlResidualFacade controlResidualFacade;

  @GetMapping("/{riesgoId}")
  public ResponseEntity<Object> buscarCausasSinControl(
      @PathVariable("riesgoId") final Long riesgoId) {
    return ResponseUtils.ok(controlResidualFacade.obtenerCausasSinControl(riesgoId));
  }

  @GetMapping("/{riesgoId}/{controlId}")
  public ResponseEntity<Object> buscarCausasDelControl(
      @PathVariable("riesgoId") final Long riesgoId,
      @PathVariable("controlId") final Long controlId) {
    return ResponseUtils.ok(controlResidualFacade.obtenerCausasDelControl(riesgoId, controlId));
  }

  @PostMapping
  public ResponseEntity<Boolean> guardarControlResidual(
      @RequestBody final GuardarControlResidualRequest request) {
    log.info("Llenando información de control residual");
    return ResponseUtils.ok(controlResidualFacade.guardarControlResidual(request));
  }

  @PutMapping("/{idControlResidual}")
  public ResponseEntity<Object> actualizarControlResidual(
      @PathVariable("idControlResidual") final Long id,
      @RequestBody final GuardarControlResidualRequest request) {
    log.info("?????? Actualizando control residual, cantidad de causa: {}", request.getListaIdCausas().size());
    return ResponseUtils.ok(controlResidualFacade.actualizarControlResidual(id, request));
  }
}
