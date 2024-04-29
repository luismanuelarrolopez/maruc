package com.unicauca.maruc.service;

import java.util.List;

public interface CausaRiesgoResidualService {

    /**
     * Asocia una causa al control residual del riesgo.
     * @param causaId
     * @param controlId
     */
    void asociarCausaControlResidual(Long causaId, Long controlId);

    /**
     * Asocia muchas causas a un control residual
     * @param causasId
     * @param controlId
     */
    void asoricarCausasControlResidual(List<Long> causasId, Long controlId);
}
