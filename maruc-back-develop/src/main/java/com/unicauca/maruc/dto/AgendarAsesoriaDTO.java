package com.unicauca.maruc.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendarAsesoriaDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5804063236251437381L;
  private Date fechaAgenda;
}
