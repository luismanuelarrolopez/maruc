package com.unicauca.maruc.exceptions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestApiExceptionHandler {

  @SuppressWarnings("unused")
  private final MessageSource messageSource;

  @Autowired
  public RestApiExceptionHandler(final MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
      final Exception ex, final Locale locale) {
    log.debug("Error genérico capturado: {}", ex.getMessage());
    final Error error = ErrorUtils
        .crearError(CodigoError.ERROR_GENERICO.getCodigo(),
            CodigoError.ERROR_GENERICO.getLlaveMensaje(), HttpStatus.INTERNAL_SERVER_ERROR.value())
        .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EntidadYaExisteException.class)
  public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
      final EntidadYaExisteException ex, final Locale locale) {
    log.debug("Error genérico capturado: {}", ex.getMessage());
    final Error error = ErrorUtils
        .crearError(CodigoError.ENTIDAD_YA_EXISTE.getCodigo(),
            String.format("%s, %s", CodigoError.ENTIDAD_YA_EXISTE.getLlaveMensaje(),
                ex.getMessage()),
            HttpStatus.NOT_ACCEPTABLE.value())
        .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
    return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(EntityNotFountException.class)
  public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
      final EntityNotFountException ex, final Locale locale) {
    log.debug("Error genérico capturado: {}", ex.getMessage());
    final Error error = ErrorUtils
        .crearError(CodigoError.ENTIDAD_YA_EXISTE.getCodigo(), ex.formatException(),
            HttpStatus.NOT_FOUND.value())
        .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ReglaNegocioExcepcion.class)
  public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
      final ReglaNegocioExcepcion ex, final Locale locale) {
    log.debug("Error genérico capturado: {}", ex.getMessage());
    final Error error = ErrorUtils
        .crearError(CodigoError.VIOLACION_REGLA_DE_NEGOCIO.getCodigo(), ex.formatException(),
            HttpStatus.NOT_FOUND.value())
        .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
    return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(EntidadNoExisteException.class)
  public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
      final EntidadNoExisteException ex, final Locale locale) {
    log.debug("Excepción entidad no existe capturada: {}", ex.getMessage());
    final Error error = ErrorUtils
        .crearError(CodigoError.ENTIDAD_NO_ENCONTRADA.getCodigo(), ex.getMessage(),
            HttpStatus.NOT_FOUND.value())
        .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
    return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<Error> handleBadCredentialsException(final HttpServletRequest req,
      final BadCredentialsException ex, final Locale locale) {
    log.debug("Credenciales invalidas: {}", ex.getMessage());
    final Error error = ErrorUtils
        .crearError(CodigoError.CREDENCIALES_INVALIDAS.getCodigo(), ex.getMessage(),
            HttpStatus.UNAUTHORIZED.value())
        .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(InternalAuthenticationServiceException.class)
  public ResponseEntity<Error> handleDisabledException(final HttpServletRequest req,
      final InternalAuthenticationServiceException ex, final Locale locale) {
    log.debug("Usuario deshabilitado: {}", ex.getMessage());
    final Error error = ErrorUtils
        .crearError(CodigoError.USUARIO_DESHABILITADO.getCodigo(), ex.getMessage(),
            HttpStatus.UNAUTHORIZED.value())
        .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(MarucError.class)
  public ResponseEntity<MarucServiceError> handleDisabledException(final HttpServletRequest req,
      final MarucError ex, final Locale locale) {
    return new ResponseEntity<>(ex.getError(), ex.getError().getStatus());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return errors;
  }
}
