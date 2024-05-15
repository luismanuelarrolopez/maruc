package com.unicauca.maruc.exceptions;

public class EntityNotFountException extends MarucRuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -7668001895629164307L;

  private String entityName;
  private Object value;
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public EntityNotFountException(final String message) {
    super();
    this.message = message;
  }

  public EntityNotFountException(final CodigoError codigoError, final String entityName,
      final Object value) {
    super(codigoError);
    this.entityName = entityName;
    this.value = value;
    this.message = codigoError.name();
  }


  @Override
  public String formatException() {
    if (value != null) {
      return String.format("%s: No se ha encontrado %s con valor %s", getCodigoError().getCodigo(),
          entityName, value.toString());
    } else {
      return String.format("%s", getMessage());
    }
  }

}
