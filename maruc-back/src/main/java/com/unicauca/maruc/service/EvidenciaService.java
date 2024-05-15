package com.unicauca.maruc.service;

import com.unicauca.maruc.domain.model.ControlResidual;
import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;

/**
 * Permite gestionar la información relacionada a las evidencias.
 */
public interface EvidenciaService {
    
    /**
     * Registra una nueva evidencia en el sistema.
     * 
     * @param evidencia
     * @return - la evidencia guardada
     */
    Evidencia guardarEvidencia(Evidencia evidencia);
    
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
    Evidencia encontrarEvidencia(Long id);

    Evidencia actualizarEvidencia(Evidencia evidencia);

    Evidencia findByControlResidual(ControlResidual controlResidual);
    
}
