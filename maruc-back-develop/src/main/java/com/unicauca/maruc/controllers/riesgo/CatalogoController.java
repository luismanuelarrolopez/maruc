package com.unicauca.maruc.controllers.riesgo;

import com.unicauca.maruc.domain.model.EntidadCatalogo;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.domain.model.TipoRiesgo;
import com.unicauca.maruc.util.CacheCatalogo;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.maruc.domain.model.DifusionControl;
import com.unicauca.maruc.domain.model.EjecucionSistemasDigitales;
import com.unicauca.maruc.domain.model.EmpleaOEjecuta;
import com.unicauca.maruc.domain.model.Periodicidad;
import com.unicauca.maruc.domain.model.TipoControl;
import com.unicauca.maruc.facade.RiesgoManagerFacade;
import com.unicauca.maruc.util.ResponseUtils;

@RestController
@RequestMapping("/catalogo")
@CrossOrigin("*")
@Slf4j
public class CatalogoController {

  @Autowired
  private CacheCatalogo cacheCatalogo;
  @Autowired
  private RiesgoManagerFacade riesgoManagerFacade;

  @GetMapping("/{catalogo}")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<EntidadCatalogo>> buscarCatalogo(@PathVariable("catalogo") final String catalogo) {
    log.info("Buscando catalago: {}", catalogo);
    return ResponseUtils.ok(riesgoManagerFacade.cargarCatalogo(cacheCatalogo.buscarCatalogoClass(catalogo)));
  }

  /*
   * Catálogos
   */
  @GetMapping("/difusion")
  public ResponseEntity<List<DifusionControl>> cargarDifusiones() {
    return ResponseUtils.ok(riesgoManagerFacade.cargarCatalogo(DifusionControl.class));
  }

  @GetMapping("/ejecucion-sistemas-digitales")
  public ResponseEntity<List<EjecucionSistemasDigitales>> cargarEjecucionSistemasDigitals() {
    return ResponseUtils.ok(riesgoManagerFacade.cargarCatalogo(EjecucionSistemasDigitales.class));
  }

  @GetMapping("/emplea-o-ejecuta")
  public ResponseEntity<List<EmpleaOEjecuta>> cargarEmpleaOEjecuta() {
    return ResponseUtils.ok(riesgoManagerFacade.cargarCatalogo(EmpleaOEjecuta.class));
  }

  @GetMapping("/periodicidad")
  public ResponseEntity<List<Periodicidad>> cargarPeriodicidad() {
    return ResponseUtils.ok(riesgoManagerFacade.cargarCatalogo(Periodicidad.class));
  }

  @GetMapping("/tipo-control")
  public ResponseEntity<List<TipoControl>> cargarTiposControl() {
    return ResponseUtils.ok(riesgoManagerFacade.cargarCatalogo(TipoControl.class));
  }

}
