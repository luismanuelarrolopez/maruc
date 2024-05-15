package com.unicauca.maruc.domain.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "control_residual")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ControlResidual extends EntidadVersionada {

  private static final long serialVersionUID = 1L;

  private String nombre;
  private String responsable;
  private String indicador;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "periodicidad_id")
  private Periodicidad periodicidad;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tipo_de_control_id")
  private TipoControl tipoControl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "riesgo_residual_id")
  private RiesgoResidual riesgoResidual;


  @OneToOne(mappedBy = "control", cascade = CascadeType.ALL)
  private Evidencia evidencia;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "causa_controlresidual", joinColumns = @JoinColumn(name = "control_residual_id"),
   inverseJoinColumns = @JoinColumn(name = "causa_id"))
  private List<Causa> causas;
}
