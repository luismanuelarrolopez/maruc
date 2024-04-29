package com.unicauca.maruc.facade;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;

public interface EvidenciaFacade {
    
    /**
     * Registra una nueva evidencia en el sistema.
     * 
     * @param evidencia
     * @return - la evidencia guardada
     */
    Evidencia guardarEvidencia(final Evidencia evidencia);

    /**
     * Encuentra todas las evidencias registradas.
     * 
     * @return - Lista de evidencias
     */
    List<Evidencia> listarEvidencias();

    /**
     * Encuentra una evidencia por su identificador.
     * 
     * @param id
     * @return - la evidencia encontrada
     */
    Evidencia encontrarEvidencia(final Long id);

    Evidencia ActualizarEvidencia(Evidencia evidencia);

}
