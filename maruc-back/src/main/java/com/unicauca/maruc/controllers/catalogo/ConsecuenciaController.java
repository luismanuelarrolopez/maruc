package com.unicauca.maruc.controllers.catalogo;

import com.unicauca.maruc.domain.model.Consecuencia;
import com.unicauca.maruc.dto.catalogos.CatalogoConsecuenciaDTO;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.exceptions.ReglaNegocioExcepcion;
import com.unicauca.maruc.facade.AdministrarCatalogosFacade;
import com.unicauca.maruc.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
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

@RestController
@RequestMapping("/consecuencias")
@CrossOrigin("*")
public class ConsecuenciaController {

  @Autowired
  private AdministrarCatalogosFacade administrarCatalogosFacade;

  @PostMapping
  @Operation(summary = "Registra una nueva consecuencia en el catálogo de consecuencias", responses = {
      @ApiResponse(responseCode = "201", description = "La consecuencia se ha registrado correctamente"),
      @ApiResponse(responseCode = "500", description = "Error interno", content = {
          @Content(schema = @Schema(oneOf = com.unicauca.maruc.exceptions.Error.class))})})
  public ResponseEntity<Consecuencia> registrarConsecuencia(
      @RequestBody final Consecuencia consecuencia) throws ReglaNegocioExcepcion {
    return ResponseUtils.ok(administrarCatalogosFacade.registrarConsecuencia(consecuencia));
  }

  @PutMapping
  public ResponseEntity<Boolean> actualizarConsecuencia(
      @Valid @RequestBody final CatalogoConsecuenciaDTO consecuencia) {
    return ResponseUtils.ok(administrarCatalogosFacade.editarConsecuencia(consecuencia) != null);
  }

  @GetMapping(params = {"page", "size"})
  public Page<CatalogoConsecuenciaDTO> listarConsecuencias(@RequestParam final int page,
      @RequestParam final int size) {
    return administrarCatalogosFacade.listarConsecuencias(page, size);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<CatalogoConsecuenciaDTO> buscarConsecuencia(
      @PathVariable(name = "id") final Long id) throws EntityNotFountException {
    return ResponseUtils.ok(administrarCatalogosFacade.buscarConsecuencia(id));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Long> eliminarConsecuencia(@PathVariable(name = "id") final Long id)
      throws EntityNotFountException {
    return ResponseUtils.ok(administrarCatalogosFacade.eliminarConsecuencia(id));
  }

  @DeleteMapping(path = "/opcion/{id}")
  public ResponseEntity<Boolean> eliminarOpcionConsecuencia(@PathVariable(name = "id") final Long id) {
    return ResponseEntity.ok().body(this.administrarCatalogosFacade.eliminarOpcionConsecuencia(id));
  }
}
