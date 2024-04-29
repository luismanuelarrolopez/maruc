package com.unicauca.maruc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unicauca.maruc.domain.model.Asesoria;

@Repository
public interface AsesoriaDAO extends PagingAndSortingRepository<Asesoria, Long> {

  @EntityGraph(value = "entity-grahp-solicitante")
  Page<Asesoria> findAllByOficinaAsesora(Pageable page, String oficinaAsesora);
}
