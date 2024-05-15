package com.unicauca.maruc.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unicauca.maruc.domain.model.TipoAfectacion;

@Repository
public interface TipoAfectacionDAO extends JpaRepository<TipoAfectacion, Long> {

  @EntityGraph(value = TipoAfectacion.GRAFO_CONSECUENCIAS)
  List<TipoAfectacion> findAll();
}
