package com.unicauca.maruc.controllers.catalogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.comun.ConstantesGlogables;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.facade.AdministrarCatalogosFacade;
import com.unicauca.maruc.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/actividades")
@CrossOrigin(origins = "*")
public class ActividadController {

  @Autowired
  private AdministrarCatalogosFacade administrarCatalogosFacade;

  @PostMapping(produces = ConstantesGlogables.APPLICATION_JSON)
  @Operation(summary = "Registra una nueva actividad en el catálogo de actividades",
      responses = {@ApiResponse(responseCode = "201",
          description = "La actividad se ha registrado correctamente")})
  public ResponseEntity<Actividad> guardarActividad(@RequestBody final Actividad actividad) {
    return ResponseUtils.ok(administrarCatalogosFacade.registrarActividad(actividad));
  }

  @GetMapping(produces = ConstantesGlogables.APPLICATION_JSON, params = {"page", "size"})
  @Operation(summary = "Lista todas las actividades registradas en el catálogo de actividades")
  public Page<Actividad> listarActividades(@RequestParam int page, @RequestParam int size) {
    return administrarCatalogosFacade.listarActividades(page, size);
  }

  @GetMapping(produces = ConstantesGlogables.APPLICATION_JSON, path = "/{id}")
  @Operation(summary = "Obtiene una actividad por el identificador proporcionado",
      parameters = @Parameter(name = "id", description = "Identificador de la actividad a buscar"))
  public ResponseEntity<Actividad> verActividad(@PathVariable(name = "id") final Long id)
      throws EntityNotFountException {
    return ResponseUtils.ok(administrarCatalogosFacade.buscarActividad(id));
  }

  @PutMapping("/{id}")
  @Operation(
      summary = "Actualiza la información de una actividad que se encuentra registrada en el catálogo de actividades",
      responses = {
          @ApiResponse(responseCode = "201",
              description = "La actividad se ha actualizado correctamente"),
          @ApiResponse(responseCode = "404",
              description = "No existe una actividad registrada con el identificador proporcionado")})
  public ResponseEntity<Actividad> editarActividad(@PathVariable(name = "id") final Long id,
      @RequestBody final Actividad actividad) throws EntityNotFountException {
    return ResponseUtils.ok(administrarCatalogosFacade.editarActividad(id, actividad));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Elimina un actividad del catálogo de actividades",
      responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404",
          description = "No se encuentra una actividad con el identificador proporcionado")})
  public ResponseEntity<Long> eliminarActividad(@PathVariable(name = "id") final Long id)
      throws EntityNotFountException {
    administrarCatalogosFacade.eliminarActividad(id);
    return ResponseUtils.ok(id);
  }
}
