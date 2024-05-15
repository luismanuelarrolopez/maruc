package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties
@Getter
@Setter
@NoArgsConstructor
public class RiesgoActividadDTO implements Serializable {

  private static final long serialVersionUID = -3614372882896612347L;
  private Long id;
  @NotNull(message = "{RiesgoActividad.NotNull.frecuencia}")
  @Min(message = "{RiesgoActividad.Min.frecuencia}", value = 20)
  @Max(message = "{RiesgoActividad.Max.frecuencia}", value = 100)
  private short frecuencia;
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotNull(message = "{RiesgoActividad.NotNull.idActividad}")
  private Long idActividad;
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotNull(message = "{RiesgoActividad.NotNull.idRiesgo}")
  private Long idRiesgo;

  private String nombreActividad;
  private boolean frecuenciaInvertida;

  public RiesgoActividadDTO(final Long id, final short frecuencia, final String nombreActividad,
      final boolean frecuenciaInvertida) {
    super();
    this.id = id;
    this.frecuencia = frecuencia;
    this.nombreActividad = nombreActividad;
    this.frecuenciaInvertida = frecuenciaInvertida;
  }
}
