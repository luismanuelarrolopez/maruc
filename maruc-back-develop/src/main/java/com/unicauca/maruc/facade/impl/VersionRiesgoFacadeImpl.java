package com.unicauca.maruc.facade.impl;

import java.util.List;

import com.unicauca.maruc.domain.model.VersionRiesgo;
import com.unicauca.maruc.facade.VersionRiesgoFacade;
import com.unicauca.maruc.service.VersionRiesgoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VersionRiesgoFacadeImpl implements VersionRiesgoFacade{

    @Autowired
    private VersionRiesgoService VersionRiesgoService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public VersionRiesgo AgregarVersionRiesgo(VersionRiesgo soporte_evidencia) {
        return VersionRiesgoService.guardarVersionRiesgo(soporte_evidencia);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<VersionRiesgo> listarVersionRiesgo() {
        return VersionRiesgoService.listarVersionRiesgos();
    }

    @Override
    public VersionRiesgo SeleccionarVersionRiesgo(Long id_soporte_evidencia) {
        return VersionRiesgoService.SeleccionarVersionRiesgo(id_soporte_evidencia);
    }
    

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean EliminarVersionRiesgo(Long id_soporte_evidencia) {
        return VersionRiesgoService.EliminarVersionRiesgo(id_soporte_evidencia);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public VersionRiesgo ActualizarVersionRiesgo(VersionRiesgo soporte_evidencia) {
        return VersionRiesgoService.ActualizarVersionRiesgo(soporte_evidencia);
    }
}
