package com.unicauca.maruc.facade;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Observacion;

public interface ObservacionFacade {
    
    /**
     * Registra una nueva  en el sistema.
     * 
     * @param Observacion
     * @return - la  guardada
     */
    Observacion guardarObservacion(final Observacion Observacion);

    /**
     * Encuentra todas las s registradas.
     * 
     * @return - Lista de s
     */
    List<Observacion> listarObservacion();

    /**
     * Encuentra una  por su identificador.
     * 
     * @param id
     * @return - la  encontrada
     */
    Observacion encontrarObservacion(final Long id);

    /**
     * Actualiza una  observacion por id_observacion.
     * 
     * @param Observacion
     * @return - la  encontrada
     */
    Observacion actualizarObservacion(final Observacion Observacion);
}
