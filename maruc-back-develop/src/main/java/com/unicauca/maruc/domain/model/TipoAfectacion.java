package com.unicauca.maruc.domain.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedEntityGraph(name = TipoAfectacion.GRAFO_CONSECUENCIAS,
    attributeNodes = {@NamedAttributeNode(value = "consecuencias")})
@Entity
@Table(name = "tipo_afectacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class TipoAfectacion extends EntidadCatalogo {

  public static final String GRAFO_CONSECUENCIAS = "grafoTipoAfectacionConsecuencias";
  /**
   * 
   */
  private static final long serialVersionUID = -4510650821291371833L;
  @OneToMany(mappedBy = "tipoAfectacion")
  private List<Consecuencia> consecuencias;

  public TipoAfectacion(Long id) {
    this.id = id;
  }
}
