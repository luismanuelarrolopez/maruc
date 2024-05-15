package com.unicauca.maruc.service;

import java.util.List;

import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.dto.riesgos.ControlResidualDTO;

public interface ControlResidualService extends BaseService<ControlResidual> {

    List<ControlResidual> buscarPorRiesgoResidual(Long idRiesgoResidual);
    List<ControlResidual> buscarPorRiesgo(Long idRiesgo);

    ControlResidual guardarControlResidual(Long idRiesgo, ControlResidualDTO controlResidualDTO);
}
