package com.unicauca.maruc.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GuardarAsesoriaDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -919187429931514812L;
  @NotNull(message = "{Asesoria.tema.NotNull}")
  private String tema;
  @NotNull(message = "{Asesoria.oficinaAsesora.NotNull}")
  @Pattern(regexp = "(OCI|OPDI)", message = "{Asesoria.oficinaAsesora.Pattern}")
  private String oficinaAsesora;
  @NotNull(message = "{Asesoria.userId.NotNull}")
  private Long userId;
}
