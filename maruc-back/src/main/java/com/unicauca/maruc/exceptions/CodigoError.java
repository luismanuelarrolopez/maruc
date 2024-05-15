package com.unicauca.maruc.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CodigoError {

  ERROR_GENERICO("MARUC-0001", "ERROR GENERICO"), ENTIDAD_YA_EXISTE("MARUC-0002",
      "ERROR ENTIDAD YA EXISTE"),
  ENTIDAD_NO_ENCONTRADA("MARUC-0003",
      "Entidad no encontrada"),
  VIOLACION_REGLA_DE_NEGOCIO("MARUC-0004",
      "Regla de negocio violada"),
  CREDENCIALES_INVALIDAS("MARUC-0005", "Error al iniciar sesión, compruebe sus credenciales y vuelva a intentarlo"),
  USUARIO_DESHABILITADO("MARUC-0006",
      "El usuario no ha sido verificado, por favor revise su correo para verificar su cuenta");

  private final String codigo;
  private final String llaveMensaje;
}
