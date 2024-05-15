package com.unicauca.maruc.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "opcion_consecuencia_riesgo")
public class OpcionConsecuenciaRiesgo extends EntidadVersionada {
    /**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 4016583046737405273L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "riesgo_id")
    private Riesgo riesgo;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "opcion_id")
    private OpcionConsecuencia opcionConsecuencia;
}
