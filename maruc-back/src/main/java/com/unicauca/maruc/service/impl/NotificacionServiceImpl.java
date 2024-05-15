package com.unicauca.maruc.service.impl;

import com.unicauca.maruc.domain.model.Notificacion;
import com.unicauca.maruc.repositories.NotificacionDAO;
import com.unicauca.maruc.service.NotificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServiceImpl implements NotificacionService {
    
    @Autowired
    private NotificacionDAO NotificacionDAO;

    @Override
    public Page<Notificacion> ListarNotificaciones(long user_id, int page, int size) {
        return NotificacionDAO.findByUsuarioIdOrderByFechaCreacionDesc(PageRequest.of(page, size),user_id);
    }

    @Override
    public Integer contarNotificacionesSinLeer(long user_id) {
        return NotificacionDAO.countByUsuarioIdAndLeida(user_id,false);
    }

    @Override
    public Notificacion actualizarNotificacion(Notificacion notificacion) {
        return NotificacionDAO.save(notificacion);
    }

    @Override
    public Notificacion obtenerNotificacion(long id) {
        return NotificacionDAO.findById(id).get();
    }

    @Override
    public Notificacion AgregarNotificacion(Notificacion notificacion) {
        return NotificacionDAO.save(notificacion);
    } 

}
