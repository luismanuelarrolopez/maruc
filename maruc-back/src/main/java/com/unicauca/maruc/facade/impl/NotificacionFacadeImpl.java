package com.unicauca.maruc.facade.impl;

import com.unicauca.maruc.domain.model.Notificacion;
import com.unicauca.maruc.facade.NotificacionFacade;
import com.unicauca.maruc.service.NotificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class NotificacionFacadeImpl implements NotificacionFacade {

    @Autowired
    private NotificacionService NotificacionService;

    @Override
    public Page<Notificacion> ListarNotificaciones(long user_id, int page, int size) {
        return NotificacionService.ListarNotificaciones(user_id, page, size);
    }

    @Override
    public Notificacion actualizarNotificacion(Notificacion notificacion) {
        return NotificacionService.actualizarNotificacion(notificacion);
    }

    @Override
    public Notificacion obtenerNotificacion(long id) {
        return NotificacionService.obtenerNotificacion(id);
    }

    @Override
    public Notificacion AgregarNotificacion(Notificacion notificacion) {
        return NotificacionService.AgregarNotificacion(notificacion);
    }

    @Override
    public Integer contarNotificacionesSinLeer(long user_id) {
        return NotificacionService.contarNotificacionesSinLeer(user_id);
    }
}