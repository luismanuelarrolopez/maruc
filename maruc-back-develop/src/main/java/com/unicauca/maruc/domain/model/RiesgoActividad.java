package com.unicauca.maruc.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ACTIVIDAD_RIESGO")
public class RiesgoActividad extends EntidadVersionada {

  private static final long serialVersionUID = -3303620840927707460L;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "riesgo_id")
  private Riesgo riesgo;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "actividad_id")
  private Actividad actividad;
  @Column(name = "frecuencia")
  private short frecuencia;
}
