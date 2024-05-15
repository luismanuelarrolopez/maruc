package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RiesgoResidualDTO implements Serializable {
  private static final long serialVersionUID = 7102461480776116484L;
  private Long id;
  @NotNull(message = "{RiesgoResidual.IdRiesgo.NotNull}")
  private Long idRiesgo;
  // @NotBlank(message = "{RiesgoResidual.Tratamiento.NotBlank}")
  private String tratamiento;
  private String nivel;
}
