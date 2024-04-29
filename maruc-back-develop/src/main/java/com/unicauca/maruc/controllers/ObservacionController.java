package com.unicauca.maruc.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import com.unicauca.maruc.comun.ConstantesGlogables;
import com.unicauca.maruc.domain.model.Notificacion;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.TipoActor;
import com.unicauca.maruc.domain.model.TipoObservacion;
import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.EvidenciaObservacion;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Observacion;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.RiesgoObservacion;
import com.unicauca.maruc.dto.monitoreo_evaluacion.ActualizarObservacionDTO;
import com.unicauca.maruc.dto.monitoreo_evaluacion.AgregarObservacionDTO;
import com.unicauca.maruc.facade.AdministrarCatalogosFacade;
import com.unicauca.maruc.facade.EvidenciaFacade;
import com.unicauca.maruc.facade.EvidenciaObservacionFacade;
import com.unicauca.maruc.facade.RiesgoObservacionFacade;
import com.unicauca.maruc.facade.NotificacionFacade;
import com.unicauca.maruc.facade.ObservacionFacade;
import com.unicauca.maruc.facade.RiesgoManagerFacade;
import com.unicauca.maruc.service.EmailService;

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

@RestController
@RequestMapping("/observacion")
@Validated
@CrossOrigin(origins = "*")
public class ObservacionController {
    
    @Autowired
    private EvidenciaObservacionFacade EvidenciaObservacionFacade;
    @Autowired
    private RiesgoObservacionFacade RiesgoObservacionFacade;
    @Autowired
    private ObservacionFacade observacionFacade;
    @Autowired
    private AdministrarCatalogosFacade catalogosFacade;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EvidenciaFacade evidenciaFacade;
    @Autowired
    private NotificacionFacade notificacionFacade;
    @Autowired
    private RiesgoManagerFacade riesgoFacade;

    @Operation(summary = "Agregar una observación a una evidencia", description = "Agrega un elemento a la tabla Observacion_Evidencia y Observacion relacionando una evidencia")
    @PostMapping("/merge_observacion_evidencia")
    public ResponseEntity<?> MergeObservacionEvidencia(@RequestBody AgregarObservacionDTO observacion_dto ) {
        Map<String, Object> response = new HashMap<>();
        try {
            TipoActor actor = catalogosFacade.buscarTipoActorByCodigo(observacion_dto.getObservacion().getTipoActor().getCodigo());
            EvidenciaObservacion evidencia_observacion = new EvidenciaObservacion();
            TipoObservacion tipo_observacion = catalogosFacade.buscarTipoObservacionByCodigo(observacion_dto.getObservacion().getTipoObservacion().getCodigo());
            observacion_dto.getObservacion().setTipoActor(actor);
            observacion_dto.getObservacion().setTipoObservacion(tipo_observacion);
            Observacion observacion_new = observacionFacade.guardarObservacion(observacion_dto.getObservacion()); 
            Evidencia evidencia = evidenciaFacade.encontrarEvidencia(observacion_dto.getId_entity());
            evidencia_observacion.setEvidencia(evidencia);
            evidencia_observacion.setObservacion(observacion_new);
            EvidenciaObservacionFacade.guardarEvidenciaObservacion(evidencia_observacion);
            notificarObservacion(evidencia, actor, observacion_dto);
            return ResponseEntity.ok(evidencia_observacion);
        } catch (Exception e) {
            response.put("message", "Error al agregar observacion a evidencia");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
            
    }

    @Operation(summary = "Actualizar una observación a una evidencia", description = "Actualiza un elemento a la tabla Observacion_Evidencia y Observacion relacionando una evidencia")
    @PutMapping("/actualizar_observacion_evidencia")
    public ResponseEntity<?> ActualizarObservacionEvidencia(@RequestBody ActualizarObservacionDTO observacion_dto ) {
        Map<String, Object> response = new HashMap<>();
        try {
            EvidenciaObservacion evidencia_observacion = EvidenciaObservacionFacade.encontrarEvidenciaObservacion(observacion_dto.getId_entity_observacion());
            evidencia_observacion.getObservacion().setObservacion(observacion_dto.getObservacion().getObservacion());
            evidencia_observacion.getObservacion().setFechaActualizacion(new Date());
            evidencia_observacion.getObservacion().setCorregida(observacion_dto.getObservacion().getCorregida());
            observacionFacade.actualizarObservacion(evidencia_observacion.getObservacion());
            return ResponseEntity.ok(evidencia_observacion);
        } catch (Exception e) {
            response.put("message", "Error al agregar observacion a evidencia");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @Operation(summary = "Obtiene una observación a una evidencia", description = "Obtiene un elemento a la tabla Observacion_Evidencia")
    @GetMapping(produces = ConstantesGlogables.APPLICATION_JSON, path = "seleccionar_observacion_evidencia_by_evidencia_and_codigo_actor/{id_evidencia}/{codigo_actor}")
    public ResponseEntity<?> SeleccionarObservacionEvidenciaByEvidencia(@Valid @Positive @PathVariable Long id_evidencia, @Valid @Positive @PathVariable Long codigo_actor) {
        Map<String, Object> response = new HashMap<>();
        try {
            TipoActor actor = catalogosFacade.buscarTipoActorByCodigo(codigo_actor.toString());
            EvidenciaObservacion evidencia_observacion = EvidenciaObservacionFacade.encontrarEvidenciaObservacionByEvidenciaAndTipoActor(id_evidencia, actor.getId());
            return ResponseEntity.ok(evidencia_observacion);
        } catch (Exception e) {
            response.put("message", "Error al obtener observacion a evidencia");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }            
    }

    private void notificarObservacion(Evidencia evidencia, TipoActor actor, AgregarObservacionDTO observacion_dto){
        try{
            //Enviamos el correo al lider del riesgo
            Riesgo riesgo = evidencia.getControl().getRiesgoResidual().getRiesgo();
            Usuario lider = evidencia.getControl().getRiesgoResidual().getRiesgo().getUsuario();
            new Thread(() -> {
                emailService.sendEmail(
            lider.getEmail(), 
            "Nueva observación de evidencia registrada", 
            "Un funcionario de la "+actor.getNombre()+" ha agregado una observación a la evidencia '"+evidencia.getEvidencia()+" del riesgo '"+riesgo.getNombre()+"', observación: "+observacion_dto.getObservacion().getObservacion()+".");
            //Agregamos la notificación al lider
            Notificacion notificacion = new Notificacion();
            notificacion.setNotificacion("Un funcionario de la "+actor.getNombre()+" ha agregado una observación a la evidencia '"+evidencia.getEvidencia()+"'.");
            notificacion.setUsuario(lider);
            notificacion.setEnlace("/lider_proceso/riesgos/controles/"+riesgo.getId()+"/evidencias/"+evidencia.getId());
            notificacionFacade.AgregarNotificacion(notificacion);
            }).start();
        }catch(Exception e){
            System.out.println("Error al notificar observación");
        }
    }
    
    @Operation(summary = "lista las observaciónesde una evidencia", description = "Obtiene una lista de elementos de la tabla Observacion_Evidencia")
    @GetMapping(produces = ConstantesGlogables.APPLICATION_JSON, path = "listar_observacion_evidencia_by_evidencia_and_codigo_actor/{id_evidencia}/{id_actor}")
    public ResponseEntity<?> ListarObservacionEvidenciaByEvidencia(@Valid @Positive @PathVariable Long id_evidencia, @Valid @Positive @PathVariable Long id_actor) {
        Map<String, Object> response = new HashMap<>();
        try {
            TipoActor actor = catalogosFacade.buscarTipoActorByCodigo(id_actor.toString());
            List<EvidenciaObservacion> evidencia_observacion = EvidenciaObservacionFacade.ListarEvidenciaObservacionByEvidencia(id_evidencia, actor.getId());
            return ResponseEntity.ok(evidencia_observacion);
        } catch (Exception e) {
            response.put("message", "Error al obtener observacion a evidencia");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }            
    }

    @Operation(summary = "lista las observaciónesde una evidencia", description = "Obtiene una lista de elementos de la tabla Observacion_Evidencia")
    @GetMapping(produces = ConstantesGlogables.APPLICATION_JSON, path = "listar_observacion_entity_by_entity_and_codigo_actor/{id_entity}/{id_actor}/{codigo_tipo_observacion}")
    public ResponseEntity<?> ListarObservacionEntityByEntity(@Valid @Positive @PathVariable Long id_entity, @Valid @Positive @PathVariable Long id_actor, @Valid @Positive @PathVariable Long codigo_tipo_observacion) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<?> listaObservaciones;
            TipoActor actor = catalogosFacade.buscarTipoActorByCodigo(id_actor.toString());
            if(codigo_tipo_observacion == 1) listaObservaciones = EvidenciaObservacionFacade.ListarEvidenciaObservacionByEvidencia(id_entity, actor.getId());
            else listaObservaciones = RiesgoObservacionFacade.ListarRiesgoObservacionByRiesgo(id_entity, actor.getId());
            return ResponseEntity.ok(listaObservaciones);
        } catch (Exception e) {
            response.put("message", "Error al obtener observacion a evidencia");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }            
    }

    //Observación riesgo
    @Operation(summary = "Agregar una observación a un riesgo", description = "Agrega un elemento a la tabla Observacion_riesgo y Observacion relacionando un riesgo")
    @PostMapping("/merge_observacion_riesgo")
    public ResponseEntity<?> MergeObservacionRiesgo(@RequestBody AgregarObservacionDTO observacion_dto ) {
        Map<String, Object> response = new HashMap<>();
        try {
            TipoActor actor = catalogosFacade.buscarTipoActorByCodigo(observacion_dto.getObservacion().getTipoActor().getCodigo());
            RiesgoObservacion riesgo_observacion = new RiesgoObservacion();
            TipoObservacion tipo_observacion = catalogosFacade.buscarTipoObservacionByCodigo(observacion_dto.getObservacion().getTipoObservacion().getCodigo());
            observacion_dto.getObservacion().setTipoActor(actor);
            observacion_dto.getObservacion().setTipoObservacion(tipo_observacion);
            Observacion observacion_new = observacionFacade.guardarObservacion(observacion_dto.getObservacion()); 
            Riesgo riesgo = riesgoFacade.SeleccionarRiesgoPorId(observacion_dto.getId_entity());
            riesgo_observacion.setRiesgo(riesgo);
            riesgo_observacion.setObservacion(observacion_new);
            RiesgoObservacionFacade.guardarRiesgoObservacion(riesgo_observacion);
            new Thread(() -> {
                notificarObservacion(riesgo, actor, observacion_dto);
            }).start();
            return ResponseEntity.ok(riesgo_observacion);
        } catch (Exception e) {
            response.put("message", "Error al agregar observacion a evidencia");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
            
    }

    @Operation(summary = "Actualizar una observación a una riesgo", description = "Actualiza un elemento a la tabla Observacion_Riesgo y Observacion relacionando una evidencia")
    @PutMapping("/actualizar_observacion_riesgo")
    public ResponseEntity<?> ActualizarObservacionRiesgo(@RequestBody ActualizarObservacionDTO observacion_dto ) {
        Map<String, Object> response = new HashMap<>();
        try {
            RiesgoObservacion riesgo_observacion = RiesgoObservacionFacade.encontrarRiesgoObservacion(observacion_dto.getId_entity_observacion());
            riesgo_observacion.getObservacion().setObservacion(observacion_dto.getObservacion().getObservacion());
            riesgo_observacion.getObservacion().setFechaActualizacion(new Date());
            riesgo_observacion.getObservacion().setCorregida(observacion_dto.getObservacion().getCorregida());
            observacionFacade.actualizarObservacion(riesgo_observacion.getObservacion());
            return ResponseEntity.ok(riesgo_observacion);
        } catch (Exception e) {
            response.put("message", "Error al agregar observacion a riesgo");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @Operation(summary = "Obtiene una observación a una evidencia", description = "Obtiene un elemento a la tabla Observacion_Evidencia")
    @GetMapping(produces = ConstantesGlogables.APPLICATION_JSON, path = "seleccionar_observacion_riesgo_by_riesgo_and_codigo_actor/{id_riesgo}/{codigo_actor}")
    public ResponseEntity<?> SeleccionarObservacionRiesgoByRiesgo(@Valid @Positive @PathVariable Long id_riesgo, @Valid @Positive @PathVariable Long codigo_actor) {
        Map<String, Object> response = new HashMap<>();
        try {
            TipoActor actor = catalogosFacade.buscarTipoActorByCodigo(codigo_actor.toString());
            RiesgoObservacion riesgo_observacion = RiesgoObservacionFacade.encontrarRiesgoObservacionByRiesgoAndTipoActor(id_riesgo, actor.getId());
            return ResponseEntity.ok(riesgo_observacion);
        } catch (Exception e) {
            response.put("message", "Error al obtener observacion a evidencia");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }            
    }

    private void notificarObservacion(Riesgo riesgo, TipoActor actor, AgregarObservacionDTO observacion_dto){
        try{
            Usuario lider = riesgo.getUsuario();
            //Agregamos la notificación al lider
            Notificacion notificacion = new Notificacion();
            notificacion.setNotificacion("Un funcionario de la "+actor.getNombre()+" ha agregado una observación al riesgo '"+riesgo.getNombre()+"'");
            notificacion.setUsuario(lider);
            notificacion.setEnlace("/riesgos/editar/"+riesgo.getId());
            notificacionFacade.AgregarNotificacion(notificacion);
            //Enviamos el correo al lider del riesgo
            emailService.sendEmail(
            lider.getEmail(), 
            "Nueva observación de riesgo registrada", 
            "Un funcionario de la "+actor.getNombre()+" ha agregado una observación al riesgo '"+riesgo.getNombre()+"', observación: "+observacion_dto.getObservacion().getObservacion()+".");
        }catch(Exception e){
            System.out.println("Error al notificar observación");
        }
    }
}

