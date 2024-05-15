package com.unicauca.maruc.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1354252533902281167L;
  private String email;
  private String password;
}
