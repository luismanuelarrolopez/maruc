package com.unicauca.maruc.controllers.evidencias;

import javax.validation.Valid;

import com.unicauca.maruc.comun.ConstantesGlogables;
import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.domain.model.Notificacion;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.TipoActor;
import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;
import com.unicauca.maruc.dto.monitoreo_evaluacion.ActualizarEvaluacionDTO;
import com.unicauca.maruc.dto.monitoreo_evaluacion.EvidenciaDTO;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.facade.AdministrarCatalogosFacade;
import com.unicauca.maruc.facade.EvidenciaFacade;
import com.unicauca.maruc.facade.NotificacionFacade;
import com.unicauca.maruc.service.EmailService;
import com.unicauca.maruc.util.ResponseUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/evidencias")
@Validated
@CrossOrigin(origins = "*")
public class EvidenciaController {
    
    @Autowired
    private EvidenciaFacade evidenciaFacade;
    @Autowired
    private AdministrarCatalogosFacade catalogosFacade;
    @Autowired
    private NotificacionFacade notificacionFacade;
    @Autowired
    private EmailService emailService;

    @Operation(summary = "Agregar una evidencia", description = "Agrega un elemento a la tabla evidencia")
    @PostMapping
    public ResponseEntity<Evidencia> guardarEvidencia(@Valid @RequestBody Evidencia evidencia) {
        return ResponseUtils.ok(evidenciaFacade.guardarEvidencia(evidencia));
    }

    @GetMapping(produces = ConstantesGlogables.APPLICATION_JSON, path = "/{id}")
    @Operation(summary = "Obtiene una evidencia por el identificador proporcionado",
      parameters = @Parameter(name = "id", description = "Identificador de la evidencia a buscar"))
  public ResponseEntity<EvidenciaDTO> SeleccionarEvidencia(@PathVariable(name = "id") final Long id) throws EntityNotFountException{
    EvidenciaDTO response = new EvidenciaDTO();
    Evidencia evidencia = evidenciaFacade.encontrarEvidencia(id);
    response.setEvidencia(evidencia);
    response.setControl(evidencia.getControl().getNombre());
    response.setRiesgo(evidencia.getControl().getRiesgoResidual().getRiesgo().getNombre());
    return ResponseUtils.ok(response);
  }

  @Operation(summary = "Actualizar una evidencia", description = "Actualiza un elemento de la tabla evidencia")
    @PutMapping
    public ResponseEntity<Evidencia> actualizarEvidencia(@RequestBody Evidencia evidencia) {
        return ResponseUtils.ok(evidenciaFacade.ActualizarEvidencia(evidencia));
    }

    @Operation(summary = "Actualizar la evaluación de una evidencia", description = "Actualiza un elemento de la tabla evidencia")
    @PutMapping(produces = ConstantesGlogables.APPLICATION_JSON, path = "/actualizarEvaluacion")
    public ResponseEntity<Evidencia> actualizarEvaluacionEvidencia(@RequestBody ActualizarEvaluacionDTO evidenciaDTO) {
        Evidencia resultado = evidenciaFacade.ActualizarEvidencia(evidenciaDTO.getEvidencia());
        notificarObservacion(evidenciaDTO);
        return ResponseUtils.ok(resultado);
    }

    private void notificarObservacion(ActualizarEvaluacionDTO evidenciaDTO) {
        // TODO Auto-generated method stub
      try{
          TipoActor actor = catalogosFacade.buscarTipoActorByCodigo(evidenciaDTO.getCodigo_actor());
          Evidencia evidencia = evidenciaFacade.encontrarEvidencia(evidenciaDTO.getEvidencia().getId());
          ControlResidual control = evidencia.getControl();
          Riesgo riesgo = control.getRiesgoResidual().getRiesgo();
          Usuario lider = riesgo.getUsuario();
          //Agregamos la notificación al lider
          Notificacion notificacion = new Notificacion();
          notificacion.setNotificacion("Un funcionario de la "+actor.getNombre()+" ha actualizado la evaluación de la evidencia '"+evidenciaDTO.getEvidencia().getEvidencia()+"'");
          notificacion.setUsuario(lider);
          notificacion.setEnlace("/lider_proceso/riesgos/controles/"+riesgo.getId()+"/evidencias/"+evidencia.getId());
          notificacionFacade.AgregarNotificacion(notificacion);
          //Enviamos el correo al lider del riesgo
          emailService.sendEmail(
          lider.getEmail(), 
          "Evaluación de evidencia actualizada", 
          "Un funcionario de la "+actor.getNombre()+" ha actualizado la evaluación de la evidencia '"+evidenciaDTO.getEvidencia().getEvidencia()+"' del control '"+control.getNombre()+"' del riesgo '"+riesgo.getNombre()+"'.");
      }catch(Exception e){
          System.out.println("Error al notificar observación");
      }
  }
}
