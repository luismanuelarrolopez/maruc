package com.unicauca.maruc.repositories;

import com.unicauca.maruc.domain.model.TipoControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoControlDAO extends JpaRepository<TipoControl, Long> {
}
