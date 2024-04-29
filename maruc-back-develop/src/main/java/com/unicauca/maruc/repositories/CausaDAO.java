package com.unicauca.maruc.repositories;

import java.util.List;

import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.unicauca.maruc.domain.model.Causa;
import com.unicauca.maruc.domain.model.Riesgo;

@Repository
public interface CausaDAO extends PagingAndSortingRepository<Causa, Long> {

  List<Causa> findAllByRiesgoOrderBySumatoriaDesc(Riesgo riesgo);

  List<Causa> findTop3ByRiesgoIdOrderBySumatoriaDesc(Long id);

  @Query("select c from Causa c where c.id in" +
          "(select ccr.causa.id from CausaControlResidual ccr " +
          "where ccr.controlResidual.id = :controlId) and c.riesgo.id = :riesgoId")
  List<Causa> buscarCausasDeControlResidual(Long riesgoId, Long controlId);
  @Query("select c from Causa c where c.id not in" +
          "(select ccr.causa.id from CausaControlResidual ccr) and c.riesgo.id = :riesgoId")
  List<Causa> buscarCausasSinControlResidual(Long riesgoId);

  @Query("select c from Causa c where c.id in (:ids)")
  Set<Causa> buscarPorListaIds(List<Long> ids);
}
