package com.unicauca.maruc.repositories;

import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EvidenciaDAO extends JpaRepository<Evidencia, Long> {

  Evidencia findByControl(ControlResidual controlResidual);
}

