package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControlDTO implements Serializable {

  private static final long serialVersionUID = -5678252111078472957L;
  private Long id;
  private String nombre;
  private String responsable;
  private Long idRiesgo;
  private Long idTipoControl;
  private Long idPeriodicidadEjecucion;
  private Long idPeriodicidadSeguimiento;
  private Long idDifusionControl;
  private Long idEjecucionPorSistemasDigitales;
  private Long idCumpleOEjecuta;

  private float puntajeTipoControl;
  private float puntajeDifusionControl;
  private float puntajePeriodicidadSeguimiento;
  private float puntajePeriodicidadEjecucion;
  private float puntajeCumpleOEjecuta;
  private float puntajeEjecuionSistemasDigitales;
}
