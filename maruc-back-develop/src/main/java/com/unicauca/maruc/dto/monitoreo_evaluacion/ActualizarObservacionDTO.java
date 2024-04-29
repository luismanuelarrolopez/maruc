package com.unicauca.maruc.dto.monitoreo_evaluacion;

import java.io.Serializable;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Observacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarObservacionDTO implements Serializable{
    
    private static final long serialVersionUID = 6890699962425666685L;
    private Long id_entity;
    private Long id_entity_observacion;
    private Observacion observacion;
}
