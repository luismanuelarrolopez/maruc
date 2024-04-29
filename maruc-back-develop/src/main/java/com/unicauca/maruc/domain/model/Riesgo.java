package com.unicauca.maruc.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.RiesgoObservacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@NamedEntityGraphs({ @NamedEntityGraph(name = Riesgo.GRAFO_TIPO_RIESGO, attributeNodes = {
    @NamedAttributeNode(value = "tipoRiesgo") }) })
@Data
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "riesgos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Riesgo extends EntidadVersionada {
  public static final String GRAFO_TIPO_RIESGO = "grafo_tipo_riesgo";
  /**
   *
   */
  private static final long serialVersionUID = -871408572679405170L;
  private String nombre;
  private boolean relacionConObjetivo;
  private String objetivo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tipo_riesgo_id")
  private TipoRiesgo tipoRiesgo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tipo_proceso_id")
  private TipoProceso tipoProceso;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tipo_tratamiento_id")
  private TipoTratamiento tipoTratamiento;

  // @ManyToOne(fetch = FetchType.LAZY)
  // @JoinColumn(name = "tipo_tratamiento_id")
  // private TipoTratamiento tipoTratamiento;

  private String motivacion;
  private String responsabilidad;
  private String oportunidad;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "riesgo")
  private List<Control> controles;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "estatus_info_riesgo_id")
  private EstatusInformacionRiesgo estatusInformacionRiesgo;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "riesgo")
  private List<RiesgoObservacion> RiesgoObservacion;

  @Column(name = "mayor_consecuencia")
  private String mayorConsecuencia;

  public Riesgo() {
    controles = new ArrayList<>();
  }

  public Riesgo(final Long id) {
    this.id = id;
  }

  public Riesgo(final String nombre, final boolean relacionConObjetivo, final String objetivo,
      final TipoRiesgo tipoRiesgo, final String motivacion, final String responsabilidad,
      final String oportunidad) {
    this.nombre = nombre;
    this.relacionConObjetivo = relacionConObjetivo;
    this.objetivo = objetivo;
    this.tipoRiesgo = tipoRiesgo;
    this.motivacion = motivacion;
    this.responsabilidad = responsabilidad;
    this.oportunidad = oportunidad;
  }
}
