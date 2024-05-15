package com.unicauca.maruc.repositories;

import com.unicauca.maruc.domain.model.CausaControlResidual;
import com.unicauca.maruc.domain.model.CausaControlResidualId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CausaControlResidualRepository extends PagingAndSortingRepository<CausaControlResidual, CausaControlResidualId> {

  @Modifying
  void deleteCausaControlResidualByControlResidualId(@Param("id") Long id);
}
