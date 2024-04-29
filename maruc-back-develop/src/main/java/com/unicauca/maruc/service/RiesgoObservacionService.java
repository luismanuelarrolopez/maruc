package com.unicauca.maruc.service;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.RiesgoObservacion;

/**
 * Permite gestionar la información relacionada a las RiesgoObservacions.
 */
public interface RiesgoObservacionService {
    
    /**
     * Registra una nueva RiesgoObservacion en el sistema.
     * 
     * @param RiesgoObservacion
     * @return - la RiesgoObservacion guardada
     */
    RiesgoObservacion guardarRiesgoObservacion(RiesgoObservacion RiesgoObservacion);
    
    /**
     * Encuentra todas las RiesgoObservacions registradas.
     * 
     * @return - Lista de RiesgoObservacions
     */
    List<RiesgoObservacion> listarRiesgoObservacion();
    
    /**
     * Encuentra una RiesgoObservacion por su identificador.
     * 
     * @param id
     * @return - la RiesgoObservacion encontrada
     */
    RiesgoObservacion encontrarRiesgoObservacion(Long id);

    /**
     * Encuentra una RiesgoObservacion por id_Riesgo.
     * 
     * @param id_Riesgo
     * @return - la RiesgoObservacion encontrada
     */
    RiesgoObservacion encontrarRiesgoObservacionByRiesgo(Long id_Riesgo);

    /**
     * Encuentra una RiesgoObservacion por id_Riesgo y tipo_actor.
     * 
     * @param id_Riesgo 
     * @param id_tipo_actor
     * @return - la RiesgoObservacion encontrada
     */
    RiesgoObservacion encontrarRiesgoObservacionByRiesgoAndTipoActor(Long id_Riesgo, Long id_tipo_actor);

    /**
     * Actualiza una RiesgoObservacion por id_observacion.
     * 
     * @param RiesgoObservacion
     * @return - la RiesgoObservacion encontrada
     */
    RiesgoObservacion actualizarRiesgoObservacion(RiesgoObservacion RiesgoObservacion);

    List<RiesgoObservacion> ListarRiesgoObservacionByRiesgo(Long id_Riesgo, Long id_tipo_actor);
    
}
