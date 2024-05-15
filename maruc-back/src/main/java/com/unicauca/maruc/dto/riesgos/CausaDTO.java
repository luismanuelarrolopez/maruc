package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.unicauca.maruc.domain.model.Causa;
import com.unicauca.maruc.domain.model.ControlResidual;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CausaDTO implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 829234615469548789L;
  private Long id;
  @NotNull(message = "{Causa.idRiesgo.NotNull}")
  private Long idRiesgo;
  @NotNull(message = "{Causa.causa.NotNull}")
  private String causa;
  @Min(value = 0, message = "{Causa.puntaje.Min}")
  @Max(value = 10, message = "{Causa.puntaje.Max}")
  private int puntaje1;
  @Min(value = 0, message = "{Causa.puntaje.Min}")
  @Max(value = 10, message = "{Causa.puntaje.Max}")
  private int puntaje2;
  @Min(value = 0, message = "{Causa.puntaje.Min}")
  @Max(value = 10, message = "{Causa.puntaje.Max}")
  private int puntaje3;
  private String porque1;
  private String porque2;
  private String porque3;
  private int sumatoria;
  private ControlResidual control;
  private String causaInicial;

  public static Causa Dto_To_Entity(final CausaDTO dto) {
    return Causa.builder()
        .causa(dto.getCausa())
        .puntaje1(dto.getPuntaje1())
        .puntaje2(dto.getPuntaje2())
        .puntaje3(dto.getPuntaje3())
        .porque1(dto.getPorque1())
        .porque2(dto.getPorque2())
        .porque3(dto.getPorque3())
        .sumatoria(dto.getSumatoria())
        .causaInicial(dto.getCausaInicial())
        .build();
  }
}
