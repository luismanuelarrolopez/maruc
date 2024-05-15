package com.unicauca.maruc.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -714998306773987365L;
  private String token;
}
