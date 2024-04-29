package com.unicauca.maruc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unicauca.maruc.domain.model.TipoObservacion;

@Repository
public interface TipoObservacionDAO extends JpaRepository<TipoObservacion, Long> {

    TipoObservacion findByCodigo(String codigo);
}
