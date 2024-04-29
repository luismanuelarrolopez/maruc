package com.unicauca.maruc.dto.monitoreo_evaluacion;

import java.io.Serializable;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarEvaluacionDTO implements Serializable{
    
    private static final long serialVersionUID = 6890699962425666685L;
    private String codigo_actor;
    private Evidencia evidencia;
}
