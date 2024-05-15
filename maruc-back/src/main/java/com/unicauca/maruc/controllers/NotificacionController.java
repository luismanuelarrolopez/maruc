package com.unicauca.maruc.controllers;

import com.unicauca.maruc.domain.model.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.maruc.comun.ConstantesGlogables;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.facade.NotificacionFacade;
import com.unicauca.maruc.util.ResponseUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/notificacion")
@CrossOrigin(origins = "*")
public class NotificacionController {
    
    @Autowired
    private NotificacionFacade notificacionManagerFacade;

    @GetMapping(produces = ConstantesGlogables.APPLICATION_JSON, path = "/{user_id}", params = {"page", "size"})
    @Operation(summary = "Obtiene las notificaciones de un usuario",
      parameters = @Parameter(name = "user_id", description = "Identificador del usuario"))
    public Page<Notificacion> ListarNotificaciones(@PathVariable(name = "user_id") final Long user_id,  @RequestParam(name = "page") final int page,
    @RequestParam(name = "size") final int size) throws EntityNotFountException{
        
        return notificacionManagerFacade.ListarNotificaciones(user_id, page, size);
  }

  //servicio para obtener las notificaciones sin leer
  @GetMapping(produces = ConstantesGlogables.APPLICATION_JSON, path = "notificaciones_sin_leer/{user_id}")
  @Operation(summary = "Obtiene las notificaciones sin leer de un usuario",
    parameters = @Parameter(name = "user_id", description = "Identificador del usuario"))
  public ResponseEntity<?> contarNotificacionesSinLeer(@PathVariable(name = "user_id") final Long user_id) throws EntityNotFountException{
    Integer cantidad = notificacionManagerFacade.contarNotificacionesSinLeer(user_id);
    return ResponseUtils.ok(cantidad);
  }

  @PutMapping("/marcar_leida/{notificacion_id}")
    @Operation(summary = "Obtiene las notificaciones de un usuario",
      parameters = @Parameter(name = "notificacion", description = "notificaciona actualizar"))
    public ResponseEntity<?> MarcarLeida(@PathVariable(name = "notificacion_id") final Long notificacion_id) throws EntityNotFountException{
        Notificacion notificacion = notificacionManagerFacade.obtenerNotificacion(notificacion_id);
        notificacion.setLeida(true);
        notificacionManagerFacade.actualizarNotificacion(notificacion);
        return ResponseUtils.ok(notificacion);
  }
}
