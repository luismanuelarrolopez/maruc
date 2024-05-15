package com.unicauca.maruc.dto.riesgos;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OpcionConsecuenciaRiesgoDTO {

  private Long id;
  @NotNull(message = "{OpcionConsecuenciaRiesgo.idRiesgo.NotNull}")
  private Long idRiesgo;
  @NotNull(message = "{OpcionConsecuenciaRiesgo.idOpcionConsecuencia.NotNull}")
  private Long idOpcionConsecuencia;
  private String tipoAfectacion;
  private String consecuencia;
  private Integer puntaje;
}
