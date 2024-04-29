package com.unicauca.maruc.domain.model;

import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class EntidadCatalogo extends EntidadBase {

  /**
   * 
   */
  private static final long serialVersionUID = 2882620578698589397L;

  protected String codigo;
  protected String descripcion;
  protected String nombre;
}
