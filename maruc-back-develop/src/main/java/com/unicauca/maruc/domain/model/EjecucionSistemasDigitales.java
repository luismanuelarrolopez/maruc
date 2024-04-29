package com.unicauca.maruc.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ejecucion_sistemas_digitales")
@Getter
@NoArgsConstructor
@Setter
public class EjecucionSistemasDigitales extends EntidadCatalogo {
  private float valor;
}
