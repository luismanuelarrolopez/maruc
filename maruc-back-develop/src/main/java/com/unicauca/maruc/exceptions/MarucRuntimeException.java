package com.unicauca.maruc.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class MarucRuntimeException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 6625469570603452191L;

  protected CodigoError codigoError;

  public abstract String formatException();
}
