package com.unicauca.maruc.repositories;

import com.unicauca.maruc.domain.model.EstatusInformacionRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatusInformacionRiesgoDAO extends JpaRepository<EstatusInformacionRiesgo, Long> {
    EstatusInformacionRiesgo findByCodigo(String codigo);
}
