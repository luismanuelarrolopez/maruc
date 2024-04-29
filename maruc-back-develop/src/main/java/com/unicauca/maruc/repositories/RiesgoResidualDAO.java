package com.unicauca.maruc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unicauca.maruc.domain.model.RiesgoResidual;

@Repository
public interface RiesgoResidualDAO extends JpaRepository<RiesgoResidual, Long> {

    Optional<RiesgoResidual> findByRiesgoId(Long id);
}
