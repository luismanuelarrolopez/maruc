package com.unicauca.maruc.service;

import com.unicauca.maruc.domain.model.Notificacion;

import org.springframework.data.domain.Page;

public interface NotificacionService {
    
    /**
     * Obtiene las notificaciones del usuario.
     * 
     * @param user_id
     * @return - lista de notificaciones
     */
    Page<Notificacion> ListarNotificaciones(long user_id, int page, int size);

    Notificacion actualizarNotificacion(Notificacion notificacion);

    Notificacion obtenerNotificacion(long id);

    Notificacion AgregarNotificacion(Notificacion notificacion);

    Integer contarNotificacionesSinLeer(long user_id);
}
