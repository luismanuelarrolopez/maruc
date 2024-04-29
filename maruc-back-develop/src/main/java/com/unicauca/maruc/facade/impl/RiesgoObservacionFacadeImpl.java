package com.unicauca.maruc.facade.impl;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.RiesgoObservacion;
import com.unicauca.maruc.facade.RiesgoObservacionFacade;
import com.unicauca.maruc.service.RiesgoObservacionService;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RiesgoObservacionFacadeImpl implements RiesgoObservacionFacade {

    @Autowired
    private RiesgoObservacionService RiesgoObservacionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RiesgoObservacion guardarRiesgoObservacion(RiesgoObservacion Riesgo) {
        return RiesgoObservacionService.guardarRiesgoObservacion(Riesgo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<RiesgoObservacion> listarRiesgoObservacion() {
        return RiesgoObservacionService.listarRiesgoObservacion();
    }

    @Override
    public RiesgoObservacion encontrarRiesgoObservacion(Long id) {
        return RiesgoObservacionService.encontrarRiesgoObservacion(id);
    }

    @Override
    public RiesgoObservacion encontrarRiesgoObservacionByRiesgo(Long id_Riesgo) {
        return RiesgoObservacionService.encontrarRiesgoObservacionByRiesgo(id_Riesgo);
    }

    @Override
    public RiesgoObservacion actualizarRiesgoObservacion(RiesgoObservacion RiesgoObservacion) {
        return RiesgoObservacionService.actualizarRiesgoObservacion(RiesgoObservacion);
    }

    @Override
    public RiesgoObservacion encontrarRiesgoObservacionByRiesgoAndTipoActor(Long id_Riesgo,
            Long id_tipo_actor) {
        return RiesgoObservacionService.encontrarRiesgoObservacionByRiesgoAndTipoActor(id_Riesgo,
                id_tipo_actor);
    }

    @Override
    public List<RiesgoObservacion> ListarRiesgoObservacionByRiesgo(Long id_Riesgo, Long id_tipo_actor) {
        return RiesgoObservacionService.ListarRiesgoObservacionByRiesgo(id_Riesgo, id_tipo_actor);
    }
    
}
