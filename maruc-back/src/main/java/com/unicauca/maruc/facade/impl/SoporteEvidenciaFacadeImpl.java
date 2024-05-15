package com.unicauca.maruc.facade.impl;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.SoporteEvidencia;
import com.unicauca.maruc.facade.SoporteEvidenciaFacade;
import com.unicauca.maruc.service.SoporteEvidenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SoporteEvidenciaFacadeImpl implements SoporteEvidenciaFacade{

    @Autowired
    private SoporteEvidenciaService soporteEvidenciaService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SoporteEvidencia AgregarSoporteEvidencia(SoporteEvidencia soporte_evidencia) {
        return soporteEvidenciaService.guardarSoporteEvidencia(soporte_evidencia);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<SoporteEvidencia> listarSoporteEvidencias() {
        return soporteEvidenciaService.listarSoporteEvidencias();
    }

    @Override
    public SoporteEvidencia SeleccionarSoporteEvidencia(Long id_soporte_evidencia) {
        return soporteEvidenciaService.SeleccionarSoporteEvidencia(id_soporte_evidencia);
    }
    
    @Override
    public List<SoporteEvidencia> SeleccionarSoportesEvidenciaByIdEvidencia(Long id_evidencia) {
        return soporteEvidenciaService.SeleccionarSoportesEvidenciaByIdEvidencia(id_evidencia);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean EliminarSoporteEvidencia(Long id_soporte_evidencia) {
        return soporteEvidenciaService.EliminarSoporteEvidencia(id_soporte_evidencia);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SoporteEvidencia ActualizarSoporteEvidencia(SoporteEvidencia soporte_evidencia) {
        return soporteEvidenciaService.ActualizarSoporteEvidencia(soporte_evidencia);
    }
}
