package com.unicauca.maruc.domain.model.Monitoreo_Evaluacion;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.unicauca.maruc.domain.model.EntidadVersionada;
import com.unicauca.maruc.domain.model.Riesgo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "riesgo_observacion")
public class RiesgoObservacion extends EntidadVersionada{
    
    @OneToOne
    @JoinColumn(name = "observacion_id")
    private Observacion observacion;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "riesgo_id", nullable = false)
    private Riesgo riesgo;
}
