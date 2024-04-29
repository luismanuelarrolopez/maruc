package com.unicauca.maruc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unicauca.maruc.domain.model.RiesgoActividad;

@Repository
public interface RiesgoActividadDAO extends JpaRepository<RiesgoActividad, Long> {

}
