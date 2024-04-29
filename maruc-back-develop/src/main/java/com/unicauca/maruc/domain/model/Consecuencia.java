package com.unicauca.maruc.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.unicauca.maruc.domain.model.enums.TipoCampo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "consecuencias")
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Consecuencia extends EntidadVersionada {

  /**
   *
   */
  private static final long serialVersionUID = 4016583046737405273L;

  @Id
  private Long id;
  private String descripcion;
  @Enumerated(EnumType.STRING)
  private TipoCampo tipoCampo;
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonProperty(access = Access.WRITE_ONLY)
  private TipoAfectacion tipoAfectacion;
  @OneToMany(mappedBy = "consecuencia", fetch = FetchType.LAZY,
      cascade = CascadeType.ALL)
  private Set<OpcionConsecuencia> opciones;
}
