package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class ControlResidualDTO implements Serializable {

  private static final long serialVersionUID = -5678252111078472957L;
  private Long id;
  @NotNull(message = "{ControlResidual.Nombre.NotNull}")
  private String nombre;
  @NotNull(message = "{ControlResidual.Responsable.NotNull}")
  private String responsable;
  @NotNull(message = "{ControlResidual.TipoControl.NotNull}")
  private Long idTipoControl;
  @NotNull(message = "{ControlResidual.Periodicidad.NotNull}")
  private Long idPeriodicidad;
  private Evidencia evidencia;
  private String indicador;
}
