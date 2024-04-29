package com.unicauca.maruc.service;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.SoporteEvidencia;

public interface SoporteEvidenciaService {
    
    /**
     * Registra un nuevo soporte de evidencia en el sistema.
     * 
     * @param soporte_evidencia
     * @return - el soporte de evidencia guardado
     */
    SoporteEvidencia guardarSoporteEvidencia(SoporteEvidencia soporte_evidencia);
    
    /**
     * Encuentra todos los soportes de evidencias registrados.
     * 
     * @return - Lista de soportes de evidencias
     */
    List<SoporteEvidencia> listarSoporteEvidencias();
    
    /**
     * Encuentra un soporte de evidencia por su identificador.
     * 
     * @param id
     * @return - el soporte de evidencia encontrado
     */
    SoporteEvidencia SeleccionarSoporteEvidencia(Long id);

    /**
     * Actualiza un soporte de evidencia en el sistema.
     * 
     * @param soporte_evidencia
     */
    SoporteEvidencia ActualizarSoporteEvidencia(SoporteEvidencia soporte_evidencia);

    /**
     * Elimina un soporte de evidencia del sistema.
     * 
     * @param id
     */
    Boolean EliminarSoporteEvidencia(Long id);

    /**
     * Encuentra todos los soportes de una evidencia.
     * 
     * @param id_evidencia
     * @return - Lista de soportes de la evidencia
     */
    List<SoporteEvidencia> SeleccionarSoportesEvidenciaByIdEvidencia(Long id_evidencia);    
}
