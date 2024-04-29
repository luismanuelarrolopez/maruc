package com.unicauca.maruc.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.unicauca.maruc.domain.model.Actividad;

public interface ActividadDAO extends PagingAndSortingRepository<Actividad, Long> {

}
