package com.unicauca.maruc.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emplea_o_ejecuta")
@Getter
@Setter
@NoArgsConstructor
public class EmpleaOEjecuta extends EntidadCatalogo {
  private double valor;
}
