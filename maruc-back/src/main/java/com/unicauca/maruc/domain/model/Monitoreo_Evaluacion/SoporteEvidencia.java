package com.unicauca.maruc.domain.model.Monitoreo_Evaluacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unicauca.maruc.domain.model.EntidadVersionada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "SOPORTE_EVIDENCIA")
public class SoporteEvidencia extends EntidadVersionada{

    @NotNull
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ruta_soporte")
    private String ruta_soporte;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "evidencia_id", nullable = false)
    private Evidencia evidencia;
}
