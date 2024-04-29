package com.unicauca.maruc.controllers.asesorias;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.domain.model.Asesoria;
import com.unicauca.maruc.dto.AgendarAsesoriaDTO;
import com.unicauca.maruc.dto.GuardarAsesoriaDTO;
import com.unicauca.maruc.facade.AsesoriasFacade;
import com.unicauca.maruc.util.ResponseUtils;

@RestController
@RequestMapping("asesorias")
@CrossOrigin("*")
public class AsesoriasController {

  @Autowired
  private AsesoriasFacade asesoriasFacade;

  @GetMapping(params = {"page", "size"}, value = "/{oficinaAsesora}")
  public Page<Asesoria> listarAsesoriasPorOficina(
      @Positive @RequestParam(name = "page") final int page,
      @Positive @RequestParam(name = "size") final int size,
      @PathVariable(name = "oficinaAsesora") final String oficinaAsesora) {
    return asesoriasFacade.listarAsesoria(page, size, oficinaAsesora);
  }

  @PostMapping
  public ResponseEntity<Asesoria> guardarAsesoria(
      @Valid @RequestBody final GuardarAsesoriaDTO asesoriaDto) {
    return ResponseUtils.ok(asesoriasFacade.guardarAsesoria(asesoriaDto));
  }

  @PutMapping("/{idAsesoria}")
  public ResponseEntity<?> agendarAsesoria(
      @Valid @RequestBody final AgendarAsesoriaDTO agendarAsesoriaDTO,
      @PathVariable final Long idAsesoria) {
    asesoriasFacade.agendarAsesoria(agendarAsesoriaDTO.getFechaAgenda(), idAsesoria);
    return ResponseUtils.ok(true);
  }
}
