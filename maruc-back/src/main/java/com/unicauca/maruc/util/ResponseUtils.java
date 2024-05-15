package com.unicauca.maruc.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseUtils {

  public static <T> ResponseEntity<T> ok(final T entity) {
    log.debug("?========= Respuesta correcta");
    return new ResponseEntity<>(entity, HttpStatus.OK);
  }

  public static <T> ResponseEntity<T> bad_request(final T entity) {
    log.debug("?========= Respuesta incorrecta");
    return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
  }
}
