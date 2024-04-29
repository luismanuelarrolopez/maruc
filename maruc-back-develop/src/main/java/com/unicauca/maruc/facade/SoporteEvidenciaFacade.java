package com.unicauca.maruc.facade;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.SoporteEvidencia;

public interface SoporteEvidenciaFacade {

    /**
     * Registra un nuevo soporte evidencia en el sistema.
     * 
     * @param soporte_evidencia
     * @return - la evidencia guardada
     */
    SoporteEvidencia AgregarSoporteEvidencia(final SoporteEvidencia soporte_evidencia);

    /**
     * Encuentra todas las evidencias registradas.
     * 
     * @return - Lista de evidencias
     */
    List<SoporteEvidencia> listarSoporteEvidencias();

    /**
     * Encuentra una evidencia por su identificador.
     * 
     * @param id
     * @return - la evidencia encontrada
     */
    SoporteEvidencia SeleccionarSoporteEvidencia(final Long id_soporte_evidencia);

    /**
     * Encuentra todos los soportes de una evidencia.
     * 
     * @param id_evidencia
     * @return - Lista de soportes de la evidencia
     */
    List<SoporteEvidencia> SeleccionarSoportesEvidenciaByIdEvidencia(final Long id_evidencia);

    /**
     * Actualiza una evidencia en el sistema.
     * 
     * @param soporte_evidencia
     */
    SoporteEvidencia ActualizarSoporteEvidencia(final SoporteEvidencia soporte_evidencia);

    /**
     * Elimina una evidencia del sistema.
     * 
     * @param id
     */
    Boolean EliminarSoporteEvidencia(final Long id_soporte_evidencia);
}
