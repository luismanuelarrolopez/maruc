package com.unicauca.maruc.service;

import com.unicauca.maruc.domain.model.RiesgoResidual;

public interface RiesgoResidualService extends BaseService<RiesgoResidual> {

    String calcularNivelRiesgoResidual(Long idRiesgo);

    RiesgoResidual buscarPorIdRiesgo(Long idRiesgo);
}
