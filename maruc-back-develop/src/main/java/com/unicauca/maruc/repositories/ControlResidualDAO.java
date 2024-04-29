package com.unicauca.maruc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unicauca.maruc.domain.model.ControlResidual;

@Repository
public interface ControlResidualDAO extends JpaRepository<ControlResidual, Long> {

    List<ControlResidual> findAllByRiesgoResidualId(Long id);

    List<ControlResidual> findAllByRiesgoResidualRiesgoId(Long idRiesgo);
}
