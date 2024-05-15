package com.unicauca.maruc.domain.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "opciones_consecuencias")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OpcionConsecuencia extends EntidadVersionada {

  /**
   * 
   */
  private static final long serialVersionUID = -2019335490368822271L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  private String descripcion;
  private Integer puntaje;
  @JsonIgnore
  @ManyToOne
  private Consecuencia consecuencia;

  @JsonIgnore
  @OneToMany(mappedBy = "opcionConsecuencia", cascade = CascadeType.ALL)
  private List<OpcionConsecuenciaRiesgo> opcionesConsecuencias;
}
