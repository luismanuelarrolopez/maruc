package com.unicauca.maruc.service;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.EvidenciaObservacion;

/**
 * Permite gestionar la información relacionada a las EvidenciaObservacions.
 */
public interface EvidenciaObservacionService {
    
    /**
     * Registra una nueva EvidenciaObservacion en el sistema.
     * 
     * @param EvidenciaObservacion
     * @return - la EvidenciaObservacion guardada
     */
    EvidenciaObservacion guardarEvidenciaObservacion(EvidenciaObservacion EvidenciaObservacion);
    
    /**
     * Encuentra todas las EvidenciaObservacions registradas.
     * 
     * @return - Lista de EvidenciaObservacions
     */
    List<EvidenciaObservacion> listarEvidenciaObservacion();
    
    /**
     * Encuentra una EvidenciaObservacion por su identificador.
     * 
     * @param id
     * @return - la EvidenciaObservacion encontrada
     */
    EvidenciaObservacion encontrarEvidenciaObservacion(Long id);

    /**
     * Encuentra una EvidenciaObservacion por id_evidencia.
     * 
     * @param id_evidencia
     * @return - la EvidenciaObservacion encontrada
     */
    EvidenciaObservacion encontrarEvidenciaObservacionByEvidencia(Long id_evidencia);

    /**
     * Encuentra una EvidenciaObservacion por id_evidencia y tipo_actor.
     * 
     * @param id_evidencia 
     * @param id_tipo_actor
     * @return - la EvidenciaObservacion encontrada
     */
    EvidenciaObservacion encontrarEvidenciaObservacionByEvidenciaAndTipoActor(Long id_evidencia, Long id_tipo_actor);

    /**
     * Actualiza una EvidenciaObservacion por id_observacion.
     * 
     * @param EvidenciaObservacion
     * @return - la EvidenciaObservacion encontrada
     */
    EvidenciaObservacion actualizarEvidenciaObservacion(EvidenciaObservacion EvidenciaObservacion);

    List<EvidenciaObservacion> ListarEvidenciaObservacionByEvidencia(Long id_evidencia, Long id_tipo_actor);
    
}
