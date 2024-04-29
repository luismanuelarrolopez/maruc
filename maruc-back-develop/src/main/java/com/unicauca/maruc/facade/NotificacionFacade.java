package com.unicauca.maruc.facade;

import com.unicauca.maruc.domain.model.Notificacion;

import org.springframework.data.domain.Page;

public interface NotificacionFacade {

    /**
        * Actualiza la notificacion
        *
        * @param notificacion
        * @return
        */
        public Notificacion AgregarNotificacion(Notificacion notificacion);

    /**
        * Actualiza la notificacion
        *
        * @param notificacion
        * @return
        */
    public Notificacion actualizarNotificacion(Notificacion notificacion);

    /**
     * Obtiene las notificaciones del usuario.
     * 
     * @param user_id
     * @return - lista de notificaciones
     */
    Page<Notificacion> ListarNotificaciones(final long user_id, int page, int size);

    /**
     * Obtiene una notificacion por su id.
     * 
     * @param id
     * @return - notificacion
     * */
    Notificacion obtenerNotificacion(final long id);

    /**
     * Contar las notificaciones sin leer.
     *  
     * @param user_id
     * @return - cantidad de notificaciones sin leer
     * */
    Integer contarNotificacionesSinLeer(final long user_id);
}
