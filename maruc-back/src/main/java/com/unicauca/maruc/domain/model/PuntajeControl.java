package com.unicauca.maruc.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PuntajeControl implements Serializable {

  private static final long serialVersionUID = -5377804292271194777L;
  @Column(name = "PUNTAJE_TIPO_CONTROL")
  private float puntajeTipoControl;
  @Column(name = "PUNTAJE_DIFUSION_CONTROL")
  private float puntajeDifisuionControl;
  @Column(name = "PUNTAJE_PERIODICIDAD_SEGUIMIENTO")
  private float puntajePeriodicidadSeguimiento;
  @Column(name = "PUNTAJE_PERIODICIDAD_EJECUCION")
  private float puntajePeriodicidadEjecucion;
  @Column(name = "PUNTAJE_SE_CUMPLE_O_EJECUTA")
  private float puntajeSeCumpleEjecuta;
  @Column(name = "PUNTAJE_EJECUCION_SISTEMAS_DIGITALES")
  private float puntajeEjecionSistemasDigitales;
}
