package com.unicauca.maruc.repositories;

import com.unicauca.maruc.domain.model.Dependencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependenciaDAO extends JpaRepository<Dependencia, Long> {
}