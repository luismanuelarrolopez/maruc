package com.unicauca.maruc.domain.model.Monitoreo_Evaluacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.domain.model.EntidadVersionada;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EVIDENCIA")
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Evidencia extends EntidadVersionada{

    @NotNull(message = "{Evidencia.evidencia.NotNull}")
    @NotEmpty(message = "{Evidencia.evidencia.NotEmpty}")
    @Size(max = 255, message = "{Evidencia.evidencia.Size}")
    @Column(name = "evidencia")
    private String evidencia;

    @PositiveOrZero(message = "{Evidencia.porcentajeAvanceOci.PositiveOrZero}")
    @Max(value = 100, message = "{Evidencia.porcentajeAvanceOci.Max}")
    @Column(name = "porcentaje_avance_oci")
    private Float porcentajeAvanceOci;

    @PositiveOrZero(message = "{Evidencia.porcentajeAvanceOpdi.PositiveOrZero}")
    @Max(value = 100, message = "{Evidencia.porcentajeAvanceOpdi.Max}")
    @Column(name = "porcentaje_avance_opdi")
    private Float porcentajeAvanceOpdi;

    @Column(name = "cumplimiento_oci")
    private Boolean cumplimiento_oci;

    @Column(name = "cumplimiento_opdi")
    private Boolean cumplimiento_opdi;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name="control_residual_id", nullable = false)
    private ControlResidual control;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "evidencia", cascade = CascadeType.ALL)
    private List<SoporteEvidencia> soporteEvidencia;
    
    @OneToOne(mappedBy = "evidencia")
    private Monitoreo monitoreo;

    @OneToOne(mappedBy = "evidencia")
    private Evaluacion evaluacion;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "evidencia")
    private List<EvidenciaObservacion> evidenciaObservacion;

    public Evidencia() {
        this.soporteEvidencia = new ArrayList<SoporteEvidencia>();
        this.evidenciaObservacion = new ArrayList<EvidenciaObservacion>();
    }
}
