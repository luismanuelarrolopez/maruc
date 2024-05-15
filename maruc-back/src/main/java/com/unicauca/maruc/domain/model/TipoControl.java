package com.unicauca.maruc.domain.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_de_control")
@Getter
@Setter
@NoArgsConstructor
public class TipoControl extends EntidadCatalogo {
  public float valor;

  public TipoControl(final Long id) {
    this.id = id;
  }
}
