package com.unicauca.maruc.dto.mappers;

import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.domain.model.DifusionControl;
import com.unicauca.maruc.domain.model.Periodicidad;
import com.unicauca.maruc.domain.model.PuntajeControl;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.TipoControl;
import com.unicauca.maruc.dto.riesgos.ControlDTO;

public class ControlMapper {

  public static Control mapFromDTO(final ControlDTO dto) {
    final Control control = new Control();
    if (dto.getId() != null) {
      control.setId(dto.getId());
    }
    control.setNombre(dto.getNombre());
    control.setResponsable(dto.getResponsable());
    control.setRiesgo(new Riesgo(dto.getIdRiesgo()));
    control.setTipoControl(new TipoControl(dto.getIdTipoControl()));
    control.setPeriodicidadSegumiento(new Periodicidad(dto.getIdPeriodicidadSeguimiento()));
    control.setPeriodicidadEjecucion(new Periodicidad(dto.getIdPeriodicidadEjecucion()));
    control.setDifusion(new DifusionControl(dto.getIdDifusionControl()));
    control.setPuntajeControl(
        new PuntajeControl(dto.getPuntajeTipoControl(), dto.getPuntajeDifusionControl(),
            dto.getPuntajePeriodicidadSeguimiento(), dto.getPuntajePeriodicidadEjecucion(),
            dto.getPuntajeCumpleOEjecuta(), dto.getPuntajeEjecuionSistemasDigitales()));
    return control;
  }

  public static ControlDTO mapToDTO(final Control control) {
    return null;
  }
}
