package com.unicauca.maruc.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "actividades")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Actividad extends EntidadVersionada {

  /**
   * 
   */
  @Id
  private Long id;
  private static final long serialVersionUID = 9036430195486382902L;
  @NotNull(message = "{Actividad.nombre.NotNull}")
  @Size(message = "{Actividad.nombre.Size}", min = 6)
  private String nombre;
  private boolean frecuenciaInvertida;

  public Actividad(Long id) {
    this.id = id;
  }
}
