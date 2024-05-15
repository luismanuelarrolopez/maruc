package com.unicauca.maruc.service;

import com.unicauca.maruc.domain.model.EstatusInformacionRiesgo;
import com.unicauca.maruc.repositories.EstatusInformacionRiesgoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoInformaconRiegoService {
    @Autowired
    private EstatusInformacionRiesgoDAO estatusInformacionRiesgoDAO;

    public EstatusInformacionRiesgo findByCodigo(String codigo) {
        return this.estatusInformacionRiesgoDAO.findByCodigo(codigo);
    }
}
