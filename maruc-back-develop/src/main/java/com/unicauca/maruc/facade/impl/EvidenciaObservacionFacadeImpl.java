package com.unicauca.maruc.facade.impl;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.EvidenciaObservacion;
import com.unicauca.maruc.facade.EvidenciaObservacionFacade;
import com.unicauca.maruc.service.EvidenciaObservacionService;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EvidenciaObservacionFacadeImpl implements EvidenciaObservacionFacade {

    @Autowired
    private EvidenciaObservacionService EvidenciaObservacionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EvidenciaObservacion guardarEvidenciaObservacion(EvidenciaObservacion evidencia) {
        return EvidenciaObservacionService.guardarEvidenciaObservacion(evidencia);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<EvidenciaObservacion> listarEvidenciaObservacion() {
        return EvidenciaObservacionService.listarEvidenciaObservacion();
    }

    @Override
    public EvidenciaObservacion encontrarEvidenciaObservacion(Long id) {
        return EvidenciaObservacionService.encontrarEvidenciaObservacion(id);
    }

    @Override
    public EvidenciaObservacion encontrarEvidenciaObservacionByEvidencia(Long id_evidencia) {
        return EvidenciaObservacionService.encontrarEvidenciaObservacionByEvidencia(id_evidencia);
    }

    @Override
    public EvidenciaObservacion actualizarEvidenciaObservacion(EvidenciaObservacion evidenciaObservacion) {
        return EvidenciaObservacionService.actualizarEvidenciaObservacion(evidenciaObservacion);
    }

    @Override
    public EvidenciaObservacion encontrarEvidenciaObservacionByEvidenciaAndTipoActor(Long id_evidencia,
            Long id_tipo_actor) {
        return EvidenciaObservacionService.encontrarEvidenciaObservacionByEvidenciaAndTipoActor(id_evidencia,
                id_tipo_actor);
    }

    @Override
    public List<EvidenciaObservacion> ListarEvidenciaObservacionByEvidencia(Long id_evidencia, Long id_tipo_actor) {
        return EvidenciaObservacionService.ListarEvidenciaObservacionByEvidencia(id_evidencia, id_tipo_actor);
    }
    
}
