package com.unicauca.maruc.facade.impl;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;
import com.unicauca.maruc.facade.EvidenciaFacade;
import com.unicauca.maruc.service.EvidenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EvidenciaFacadeImpl implements EvidenciaFacade{

    @Autowired
    private EvidenciaService evidenciaService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Evidencia guardarEvidencia(Evidencia evidencia) {
        return evidenciaService.guardarEvidencia(evidencia);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Evidencia> listarEvidencias() {
        return evidenciaService.listarEvidencias();
    }

    @Override
    public Evidencia encontrarEvidencia(Long id) {
        return evidenciaService.encontrarEvidencia(id);
    }

    @Override
    public Evidencia ActualizarEvidencia(Evidencia evidencia) {
        return evidenciaService.actualizarEvidencia(evidencia);
    }
    
    
}
