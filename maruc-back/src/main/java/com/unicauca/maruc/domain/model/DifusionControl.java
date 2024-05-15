package com.unicauca.maruc.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "difusion_control")
@Getter
@NoArgsConstructor
@Setter
public class DifusionControl extends EntidadCatalogo {
  private static final long serialVersionUID = 5596288641743164361L;
  private double valor;

  public DifusionControl(final Long id) {
    this.id = id;
  }
}
