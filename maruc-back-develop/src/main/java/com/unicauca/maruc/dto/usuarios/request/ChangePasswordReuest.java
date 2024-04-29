package com.unicauca.maruc.dto.usuarios.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangePasswordReuest {

  @NotNull(message = "{idUsuario.NotNull}")
  private Long idUsuario;
  @NotNull(message = "Debe ingresar la contaseña actual")
  private String actualPassword;
  @NotNull(message = "Debe ingresar la nueva contraseña")
  private String newPassword;
  @NotNull(message = "Debe ingresar la contraseña de confirmación")
  private String confirmPassword;
}
