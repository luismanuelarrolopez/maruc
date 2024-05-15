package com.unicauca.maruc.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tipo_riesgo")
public class TipoRiesgo extends EntidadCatalogo {

  /**
   *
   */
  private static final long serialVersionUID = -6272305079169626340L;

}
