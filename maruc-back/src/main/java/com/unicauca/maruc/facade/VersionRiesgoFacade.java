package com.unicauca.maruc.facade;

import java.util.List;

import com.unicauca.maruc.domain.model.VersionRiesgo;

public interface VersionRiesgoFacade {

    /**
     * Registra un nuevo soporte evidencia en el sistema.
     * 
     * @param version_riesgo
     * @return - la evidencia guardada
     */
    VersionRiesgo AgregarVersionRiesgo(final VersionRiesgo version_riesgo);

    /**
     * Encuentra todas las evidencias registradas.
     * 
     * @return - Lista de evidencias
     */
    List<VersionRiesgo> listarVersionRiesgo();

    /**
     * Encuentra una evidencia por su identificador.
     * 
     * @param id
     * @return - la evidencia encontrada
     */
    VersionRiesgo SeleccionarVersionRiesgo(final Long id_version_riesgo);

    /**
     * Actualiza una evidencia en el sistema.
     * 
     * @param version_riesgo
     */
    VersionRiesgo ActualizarVersionRiesgo(final VersionRiesgo version_riesgo);

    /**
     * Elimina una evidencia del sistema.
     * 
     * @param id
     */
    Boolean EliminarVersionRiesgo(final Long id_version_riesgo);
}
