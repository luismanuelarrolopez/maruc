package com.unicauca.maruc.controllers.riesgo;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.dto.riesgos.RiesgoActividadDTO;
import com.unicauca.maruc.facade.ActividadesRiesgoFacade;
import com.unicauca.maruc.util.ResponseUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/riesgos/actividades")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ActividadRiesgoController {

  private final ActividadesRiesgoFacade actividadesRiesgoFacade;

  @GetMapping("/notIn/{idRiesgo}")
  public ResponseEntity<List<Actividad>> cargarActividadesSinRiesgo(
      @PathVariable("idRiesgo") final Long idRiesgo) {
    return ResponseUtils.ok(actividadesRiesgoFacade.obtenerActividadesNoEnRiesgo(idRiesgo));
  }

  @GetMapping("/{id}")
  public ResponseEntity<List<RiesgoActividadDTO>> buscarActividades(
      @PathVariable("id") final Long idRiesgo) {
    return new ResponseEntity<>(actividadesRiesgoFacade.buscarActividadesPor(idRiesgo),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Boolean> guardarActividad(
      @Valid @RequestBody final RiesgoActividadDTO actividad) {
    return ResponseUtils.ok(actividadesRiesgoFacade.guardarRiesgo(actividad));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> eliminarActividad(
      @PathVariable("id") final Long idActividadRiesgo) {
    return ResponseUtils.ok(actividadesRiesgoFacade.eliminarActividadPor(idActividadRiesgo));
  }
}
