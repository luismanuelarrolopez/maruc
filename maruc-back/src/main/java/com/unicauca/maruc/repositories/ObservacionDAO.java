package com.unicauca.maruc.repositories;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Observacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservacionDAO extends JpaRepository<Observacion, Long> {    
}
