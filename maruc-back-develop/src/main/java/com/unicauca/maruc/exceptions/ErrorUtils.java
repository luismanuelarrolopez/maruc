package com.unicauca.maruc.exceptions;

/**
 * Clase de utilidad para el manejo de errores en la aplicación
 * 
 * @author Sebastián Carabali
 */
public final class ErrorUtils {

  ErrorUtils() {
    // Empty constructor
  }

  /**
   * Crea un nuevo objeto de <code>Error</code>
   * 
   * @param codigoError
   * @param llaveMensaje
   * @param codigoHttp
   * @return - Objeto creado
   */
  public static Error crearError(final String codigoError, final String llaveMensaje,
      final Integer codigoHttp) {
    final Error error = new Error();
    error.setCodigoError(codigoError);
    error.setMensaje(llaveMensaje);
    error.setCodigoHttp(codigoHttp);
    return error;
  }
}
