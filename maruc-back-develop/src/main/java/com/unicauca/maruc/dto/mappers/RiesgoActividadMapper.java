package com.unicauca.maruc.dto.mappers;

import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.RiesgoActividad;
import com.unicauca.maruc.dto.riesgos.RiesgoActividadDTO;

public final class RiesgoActividadMapper {

  public static RiesgoActividad mapFromWebModel(final RiesgoActividadDTO riesgoActividadDTO) {
    final RiesgoActividad riesgoActividad = new RiesgoActividad();
    final Riesgo riesgo = new Riesgo();
    riesgo.setId(riesgoActividadDTO.getIdRiesgo());
    final Actividad actividad = new Actividad();
    actividad.setId(riesgoActividadDTO.getIdActividad());
    riesgoActividad.setRiesgo(riesgo);
    riesgoActividad.setActividad(actividad);
    riesgoActividad.setFrecuencia(riesgoActividadDTO.getFrecuencia());
    return riesgoActividad;
  }
}
