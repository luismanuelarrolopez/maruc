package com.unicauca.maruc.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "dependencias")
public class Dependencia extends EntidadCatalogo {

  /**
   * 
   */
  private static final long serialVersionUID = 6918318568266207483L;

  public Dependencia(final Builder builder) {
    this.id = builder.id;
    this.nombre = builder.nombre;
  }

  public Dependencia(Long idDependencia) {
    this.id = idDependencia;
  }

  public static class Builder {
    private Long id;
    private String nombre;

    public Builder id(final Long id) {
      this.id = id;
      return this;
    }

    public Builder nombre(final String nombre) {
      this.nombre = nombre;
      return this;
    }

    public Dependencia build() {
      return new Dependencia(this);
    }
  }
}
