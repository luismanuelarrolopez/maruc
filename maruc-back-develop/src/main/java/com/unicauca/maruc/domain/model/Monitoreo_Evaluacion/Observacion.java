package com.unicauca.maruc.domain.model.Monitoreo_Evaluacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.maruc.domain.model.EntidadVersionada;
import com.unicauca.maruc.domain.model.TipoActor;
import com.unicauca.maruc.domain.model.TipoObservacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OBSERVACION")
public class Observacion extends EntidadVersionada{
    
    @Column(name = "observacion")
    private String observacion;

    @Column(name = "corregida")
    private Boolean corregida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_actor_id")
    private TipoActor tipoActor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_observacion_id")
    private TipoObservacion tipoObservacion;

    @OneToOne(mappedBy = "observacion")
    @JsonIgnore
    private EvidenciaObservacion evidenciaObservacion;
}
