package com.unicauca.maruc.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "periodicidad")
@NoArgsConstructor
@Getter
@Setter
public class Periodicidad extends EntidadCatalogo {
  private double valor;

  public Periodicidad(final Long id) {
    this.id = id;
  }
}
