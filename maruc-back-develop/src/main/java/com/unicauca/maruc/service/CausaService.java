package com.unicauca.maruc.service;

import com.unicauca.maruc.domain.model.Causa;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.dto.riesgos.CausaDTO;

import java.util.List;
import java.util.Set;

public interface CausaService extends BaseService<Causa> {

    Causa guardarCausa(CausaDTO causa);

    List<CausaDTO> buscarPorRiesgo(Riesgo riesgo);

    List<CausaDTO> buscarCriticas(Long idRiesgo);

    /**
     * Busca todas las causas que no se encuentran asociadas a un control residual
     *
     * @return lista de causas sin control
     */
    List<CausaDTO> buscarCausasSinControlResidual(Long riesgoId);

    /**
     * Busca las causas que estan asociadas al control
     *
     * @param riesgoId
     * @param controlId - Id del control residual
     * @return lista de causas del control
     */
    List<CausaDTO> buscarCausasDeControlResidual(Long riesgoId, Long controlId);

    Set<Causa> buscarPorListaIds(List<Long> ids);
}
