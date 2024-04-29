package com.unicauca.maruc.facade.impl;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Observacion;
import com.unicauca.maruc.facade.ObservacionFacade;
import com.unicauca.maruc.service.ObservacionService;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObservacionFacadeImpl implements ObservacionFacade {

    @Autowired
    private ObservacionService ObservacionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Observacion guardarObservacion(Observacion observacion) {
        return ObservacionService.guardarObservacion(observacion);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Observacion> listarObservacion() {
        return ObservacionService.listarObservacion();
    }

    @Override
    public Observacion encontrarObservacion(Long id) {
        return ObservacionService.encontrarObservacion(id);
    }

    @Override
    public Observacion actualizarObservacion(Observacion Observacion) {
        return ObservacionService.actualizarObservacion(Observacion);
    }
    
}
