package com.unicauca.maruc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicauca.maruc.domain.model.TipoProceso;

public interface TipoProcesoDAO extends JpaRepository<TipoProceso, Long> {

}
