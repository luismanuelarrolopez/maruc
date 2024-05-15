package com.unicauca.maruc.service;

import java.util.List;

import com.unicauca.maruc.domain.model.VersionRiesgo;

public interface VersionRiesgoService {
    
    /**
     * Registra un nuevo soporte de evidencia en el sistema.
     * 
     * @param version_riesgo
     * @return - el soporte de evidencia guardado
     */
    VersionRiesgo guardarVersionRiesgo(VersionRiesgo version_riesgo);
    
    /**
     * Encuentra todos los soportes de evidencias registrados.
     * 
     * @return - Lista de soportes de evidencias
     */
    List<VersionRiesgo> listarVersionRiesgos();
    
    /**
     * Encuentra un soporte de evidencia por su identificador.
     * 
     * @param id
     * @return - el soporte de evidencia encontrado
     */
    VersionRiesgo SeleccionarVersionRiesgo(Long id);

    /**
     * Actualiza un soporte de evidencia en el sistema.
     * 
     * @param version_riesgo
     */
    VersionRiesgo ActualizarVersionRiesgo(VersionRiesgo version_riesgo);

    /**
     * Elimina un soporte de evidencia del sistema.
     * 
     * @param id
     */
    Boolean EliminarVersionRiesgo(Long id);

}
