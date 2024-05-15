package com.unicauca.maruc.facade;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.EvidenciaObservacion;

public interface EvidenciaObservacionFacade {
    
    /**
     * Registra una nueva evidencia en el sistema.
     * 
     * @param EvidenciaObservacion
     * @return - la evidencia guardada
     */
    EvidenciaObservacion guardarEvidenciaObservacion(final EvidenciaObservacion evidenciaObservacion);

    /**
     * Encuentra todas las evidencias registradas.
     * 
     * @return - Lista de evidencias
     */
    List<EvidenciaObservacion> listarEvidenciaObservacion();

    /**
     * Encuentra una evidencia por su identificador.
     * 
     * @param id
     * @return - la evidencia encontrada
     */
    EvidenciaObservacion encontrarEvidenciaObservacion(final Long id);

    /**
     * Encuentra una evidencia observacion por id_evidencia.
     * 
     * @param id_evidencia
     * @return - la evidencia encontrada
     */
    EvidenciaObservacion encontrarEvidenciaObservacionByEvidencia(final Long id_evidencia);

    /**
     * Encuentra una evidencia observacion por id_evidencia y tipo actor
     * 
     * @param id_evidencia
     * @return - la evidencia encontrada
     */
    EvidenciaObservacion encontrarEvidenciaObservacionByEvidenciaAndTipoActor(final Long id_evidencia, final Long id_tipo_actor);

    /**
     * Actualiza una evidencia observacion por id_observacion.
     * 
     * @param EvidenciaObservacion
     * @return - la evidencia encontrada
     */
    EvidenciaObservacion actualizarEvidenciaObservacion(final EvidenciaObservacion evidenciaObservacion);

    List<EvidenciaObservacion> ListarEvidenciaObservacionByEvidencia(final Long id_evidencia, Long id_tipo_actor);
}
