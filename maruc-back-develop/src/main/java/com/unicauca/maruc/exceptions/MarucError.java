package com.unicauca.maruc.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MarucError extends RuntimeException {

  private MarucServiceError error;
  public MarucError(HttpStatus status, String where, String message) {
    error = MarucServiceError.builder()
        .status(status)
        .where(where)
        .message(message)
        .statusCode(status.value())
        .build();
  }
}
