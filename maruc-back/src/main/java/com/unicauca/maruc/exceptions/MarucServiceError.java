package com.unicauca.maruc.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MarucServiceError {

  private HttpStatus status;
  private String where;
  private String message;
  private int statusCode;
}
