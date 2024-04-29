package com.unicauca.maruc.repositories;

import com.unicauca.maruc.domain.model.Periodicidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodicidadDAO extends JpaRepository<Periodicidad, Long> {
}