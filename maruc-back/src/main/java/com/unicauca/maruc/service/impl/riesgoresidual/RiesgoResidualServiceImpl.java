package com.unicauca.maruc.service.impl.riesgoresidual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.RiesgoResidual;
import com.unicauca.maruc.repositories.RiesgoResidualDAO;
import com.unicauca.maruc.service.RiesgoResidualService;
import com.unicauca.maruc.service.impl.DefaultBaseService;

@Service
public class RiesgoResidualServiceImpl extends DefaultBaseService<RiesgoResidual>
        implements RiesgoResidualService {

    @Autowired
    private RiesgoResidualDAO riesgoResidualDAO;

    @Override
    public String calcularNivelRiesgoResidual(Long idRiesgo) {
        return null;
    }

    @Override
    public RiesgoResidual buscarPorIdRiesgo(Long idRiesgo) {
        return riesgoResidualDAO.findByRiesgoId(idRiesgo)
                .orElse(new RiesgoResidual());
    }
}
