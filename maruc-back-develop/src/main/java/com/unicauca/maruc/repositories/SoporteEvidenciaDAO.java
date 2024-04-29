package com.unicauca.maruc.repositories;

import java.util.List;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.SoporteEvidencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SoporteEvidenciaDAO extends JpaRepository<SoporteEvidencia, Long> {

    //@Query("SELECT * FROM soporte_evidencia s WHERE s.id_evidencia = ?1")
    public List<SoporteEvidencia> findByEvidencia(Long evidencia_id);
}