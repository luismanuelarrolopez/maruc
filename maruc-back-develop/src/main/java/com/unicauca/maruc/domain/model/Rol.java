package com.unicauca.maruc.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Rol extends EntidadCatalogo {

  /**
   *
   */
  private static final long serialVersionUID = 4754632227479739824L;

  public Rol(final Builder builder) {
    this.id = builder.id;
    this.nombre = builder.nombre;
  }

  public Rol(Long idRol) {
    this.id = idRol;
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

    public Rol build() {
      return new Rol(this);
    }
  }
}
