package com.unicauca.maruc.dto.mappers;

import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.domain.model.Periodicidad;
import com.unicauca.maruc.domain.model.TipoControl;
import com.unicauca.maruc.dto.riesgos.ControlResidualDTO;

public class ControlResidualMapper {

  public static ControlResidual mapFromDTO(final ControlResidualDTO controlResidualDTO) {
    final ControlResidual control = new ControlResidual();
    if (controlResidualDTO.getId() != null) {
      control.setId(controlResidualDTO.getId());
    }
    control.setTipoControl(new TipoControl(controlResidualDTO.getIdTipoControl()));
    control.setPeriodicidad(new Periodicidad(controlResidualDTO.getIdPeriodicidad()));
    control.setNombre(controlResidualDTO.getNombre());
    control.setResponsable(controlResidualDTO.getResponsable());
//    control.setEvidencia(controlResidualDTO.getEvidencia());
    control.setIndicador(controlResidualDTO.getIndicador());
    return control;
  }

  public static ControlResidual setEntityValuesFromDTO(final ControlResidual entity, final ControlResidualDTO dto) {
//    entity.setEvidencia(new Evidencia());
    entity.setResponsable(dto.getResponsable());
    entity.setIndicador(dto.getIndicador());
    entity.setNombre(dto.getNombre());
    entity.setPeriodicidad(new Periodicidad(dto.getIdPeriodicidad()));
    entity.setTipoControl(new TipoControl(dto.getIdTipoControl()));
    return entity;
  }
}
