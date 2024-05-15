package com.unicauca.maruc.domain.model.Monitoreo_Evaluacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;

import com.unicauca.maruc.domain.model.EntidadVersionada;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EVALUACION")

public class Evaluacion extends EntidadVersionada{

    @Column(name = "observacion")
    private String observacion;

    @PositiveOrZero
    @Max(100)
    @Column(name = "porcentaje_avance")
    private Float porcentajeAvance;

    @OneToOne
    @JoinColumn(name = "evidencia_id", nullable = false)
    private Evidencia evidencia;
}
