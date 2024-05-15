package com.unicauca.maruc.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "causas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Causa extends EntidadVersionada {

  /**
   *
   */
  private static final long serialVersionUID = -2175057933398033870L;
  private String causa;
  private int puntaje1;
  private int puntaje2;
  private int puntaje3;
  private String porque1;
  private String porque2;
  private String porque3;
  @JoinColumn(name = "riesgo_id")
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Riesgo riesgo;

  private int sumatoria;

  @Column(name = "causa_inicial")
  private String causaInicial;

//  @JsonIgnore
//  @ManyToMany(mappedBy = "causas", cascade = CascadeType.ALL)
//  private List<ControlResidual> controlResiduales;

  public Causa(final Long id) {
    this.id = id;
  }

  public Causa(final String causa, final int puntaje1, final int puntaje2, final int puntaje3,
      final String porque1, final String porque2, final String porque3, final Riesgo riesgo) {
    super();
    this.causa = causa;
    this.puntaje1 = puntaje1;
    this.puntaje2 = puntaje2;
    this.puntaje3 = puntaje3;
    this.porque1 = porque1;
    this.porque2 = porque2;
    this.porque3 = porque3;
    this.riesgo = riesgo;
  }
}
