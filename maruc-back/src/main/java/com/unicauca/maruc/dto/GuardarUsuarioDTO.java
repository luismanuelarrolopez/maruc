package com.unicauca.maruc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuardarUsuarioDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 6890699962425666685L;
  @NotNull(message = "{Usuario.Email.NotNull}")
  @Email(message = "{Usuario.Email.Email}")
  private String email;
  @NotNull(message = "{Usuario.Nombres.NotNull}")
  private String nombres;
  @NotNull(message = "{Usuario.Apellidos.NotNull}")
  private String apellidos;
  @NotNull(message = "{Usuario.Rol.NotNull}")
  private Long idRol;
  @NotNull(message = "{Usuario.Dependencia.NotNull}")
  private Long idDependencia;
}
