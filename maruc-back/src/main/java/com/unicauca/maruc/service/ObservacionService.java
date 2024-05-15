package com.unicauca.maruc.service;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Observacion;

/**
 * Permite gestionar la información relacionada a las Observacions.
 */
public interface ObservacionService {
    
    /**
     * Registra una nueva Observacion en el sistema.
     * 
     * @param Observacion
     * @return - la Observacion guardada
     */
    Observacion guardarObservacion(Observacion Observacion);
    
    /**
     * Encuentra todas las Observacions registradas.
     * 
     * @return - Lista de Observacions
     */
    List<Observacion> listarObservacion();
    
    /**
     * Encuentra una Observacion por su identificador.
     * 
     * @param id
     * @return - la Observacion encontrada
     */
    Observacion encontrarObservacion(Long id);

    /**
     * Actualiza una Observacion por id_observacion.
     * 
     * @param Observacion
     * @return - la Observacion encontrada
     */
    Observacion actualizarObservacion(Observacion Observacion);
    
}
