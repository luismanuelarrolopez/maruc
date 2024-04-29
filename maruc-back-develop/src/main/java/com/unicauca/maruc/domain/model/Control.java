package com.unicauca.maruc.domain.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "controles")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Control extends EntidadVersionada {

  private static final long serialVersionUID = -8290492859863458688L;

  private String nombre;
  private String responsable;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "riesgo_id", nullable = false)
  private Riesgo riesgo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tipo_de_control_id")
  private TipoControl tipoControl;
  @ManyToOne
  @JoinColumn(name = "periodicidad_de_seguimiento")
  private Periodicidad periodicidadSegumiento;

  @ManyToOne
  @JoinColumn(name = "periodicidad_de_ejecucion")
  private Periodicidad periodicidadEjecucion;

  @ManyToOne
  @JoinColumn(name = "difusion_del_control_id")
  private DifusionControl difusion;

  @ManyToOne
  @JoinColumn(name = "ejecucion_sistemas_digitales_id")
  private EjecucionSistemasDigitales ejecucionSistemasDigitales;

  @ManyToOne
  @JoinColumn(name = "emplea_o_ejecuta_id")
  private EmpleaOEjecuta empleaOEjecuta;

  private boolean aplica;

  // @OneToOne(mappedBy = "control", fetch = FetchType.EAGER)
  // private Evidencia evidencia;

  @Embedded
  private PuntajeControl puntajeControl;

  public Control() {}
}
