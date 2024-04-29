package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InformacionBasicaRiesgoDTO implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1517930032926601011L;
  private Long id;
  @NotNull(message = "{InformacionBasica.nombre.NotNull}")
  private String nombre;
  @NotNull(message = "{InformacionBasica.relacionConObjetivo.NotNull}")
  private boolean relacionConObjetivo;
  @NotNull(message = "{InformacionBasica.objetivo.NotNull}")
  private String objetivo;
  @NotNull(message = "{InformacionBasica.idTipoRiesgo.NotNull}")
  private Long idTipoRiesgo;
  @NotNull(message = "{InformacionBasica.idProceso.NotNull}")
  private Long idProceso;
  private String motivacion;
  private String responsabilidad;
  private String oportunidad;
  @NotNull(message = "{InformacionBasica.idUsuario.NotNull}")
  private Long idUsuario;
}
