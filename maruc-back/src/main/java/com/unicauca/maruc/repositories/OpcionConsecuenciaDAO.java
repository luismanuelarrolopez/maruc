package com.unicauca.maruc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unicauca.maruc.domain.model.OpcionConsecuencia;

public interface OpcionConsecuenciaDAO extends JpaRepository<OpcionConsecuencia, Long> {

  OpcionConsecuencia findByConsecuenciaId(Long idConsecuencia);

  void deleteByConsecuenciaId(Long idConsecuencia);
}
