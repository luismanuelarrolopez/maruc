package com.unicauca.maruc.exceptions;

import lombok.Getter;

@Getter
public class EntidadYaExisteException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 2665399427928981800L;

  private final String llaveMensaje;
  private final String codigo;

  public EntidadYaExisteException(CodigoError code) {
    super(code.getCodigo());
    this.llaveMensaje = code.getLlaveMensaje();
    this.codigo = code.getCodigo();
  }

  public EntidadYaExisteException(final String message) {
    super(message);
    this.llaveMensaje = CodigoError.ENTIDAD_YA_EXISTE.getLlaveMensaje();
    this.codigo = CodigoError.ENTIDAD_YA_EXISTE.getCodigo();
  }
}
