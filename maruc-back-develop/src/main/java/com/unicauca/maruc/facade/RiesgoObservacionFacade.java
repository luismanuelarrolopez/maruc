package com.unicauca.maruc.facade;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.RiesgoObservacion;

public interface RiesgoObservacionFacade {
    
    /**
     * Registra una nueva Riesgo en el sistema.
     * 
     * @param RiesgoObservacion
     * @return - la Riesgo guardada
     */
    RiesgoObservacion guardarRiesgoObservacion(final RiesgoObservacion RiesgoObservacion);

    /**
     * Encuentra todas las Riesgos registradas.
     * 
     * @return - Lista de Riesgos
     */
    List<RiesgoObservacion> listarRiesgoObservacion();

    /**
     * Encuentra una Riesgo por su identificador.
     * 
     * @param id
     * @return - la Riesgo encontrada
     */
    RiesgoObservacion encontrarRiesgoObservacion(final Long id);

    /**
     * Encuentra una Riesgo observacion por id_Riesgo.
     * 
     * @param id_Riesgo
     * @return - la Riesgo encontrada
     */
    RiesgoObservacion encontrarRiesgoObservacionByRiesgo(final Long id_Riesgo);

    /**
     * Encuentra una Riesgo observacion por id_Riesgo y tipo actor
     * 
     * @param id_Riesgo
     * @return - la Riesgo encontrada
     */
    RiesgoObservacion encontrarRiesgoObservacionByRiesgoAndTipoActor(final Long id_Riesgo, final Long id_tipo_actor);

    /**
     * Actualiza una Riesgo observacion por id_observacion.
     * 
     * @param RiesgoObservacion
     * @return - la Riesgo encontrada
     */
    RiesgoObservacion actualizarRiesgoObservacion(final RiesgoObservacion RiesgoObservacion);

    List<RiesgoObservacion> ListarRiesgoObservacionByRiesgo(final Long id_Riesgo, Long id_tipo_actor);
}
