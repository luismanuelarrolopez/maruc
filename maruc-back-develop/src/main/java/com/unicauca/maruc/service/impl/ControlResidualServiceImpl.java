package com.unicauca.maruc.service.impl;

import java.util.List;

import com.unicauca.maruc.domain.model.RiesgoResidual;
import com.unicauca.maruc.dto.mappers.ControlResidualMapper;
import com.unicauca.maruc.dto.riesgos.ControlResidualDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.repositories.ControlResidualDAO;
import com.unicauca.maruc.service.ControlResidualService;

@Service
public class ControlResidualServiceImpl extends DefaultBaseService<ControlResidual>
        implements ControlResidualService {

    @Autowired
    private ControlResidualDAO controlResidualDAO;

    @Override
    public List<ControlResidual> buscarPorRiesgoResidual(Long idRiesgoResidual) {
        return controlResidualDAO.findAllByRiesgoResidualId(idRiesgoResidual);
    }

    @Override
    public List<ControlResidual> buscarPorRiesgo(Long idRiesgo) {
        return controlResidualDAO.findAllByRiesgoResidualRiesgoId(idRiesgo);
    }

    @Override
    public ControlResidual guardarControlResidual(Long idRiesgoResidual, ControlResidualDTO controlResidualDTO) {
        ControlResidual controlResidual = ControlResidualMapper.mapFromDTO(controlResidualDTO);
        controlResidual.setRiesgoResidual(new RiesgoResidual(idRiesgoResidual));
        controlResidual = guardar(controlResidual);
        return controlResidual;
    }
}
