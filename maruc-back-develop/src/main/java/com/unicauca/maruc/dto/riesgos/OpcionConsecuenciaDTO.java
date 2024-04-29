package com.unicauca.maruc.dto.riesgos;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OpcionConsecuenciaDTO {

  private Long id;
  @NotNull(message = "La descripción no debe ser nula")
  private String descripcion;
  private Integer puntaje;
}
