package com.unicauca.maruc.dto.mappers;

import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.RiesgoResidual;
import com.unicauca.maruc.dto.riesgos.RiesgoResidualDTO;

public class RiesgoResidualMapper {

  public static RiesgoResidual buildFromDTO(final RiesgoResidualDTO riesgoResidualDTO) {
    return RiesgoResidual.builder()
        .id(riesgoResidualDTO.getId())
        .riesgo(new Riesgo(riesgoResidualDTO.getIdRiesgo()))
        .tratamiento(riesgoResidualDTO.getTratamiento())
        .build();
  }
}
