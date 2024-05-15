package com.unicauca.maruc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unicauca.maruc.domain.model.TipoActor;

@Repository
public interface TipoActorDAO extends JpaRepository<TipoActor, Long> {

    TipoActor findByCodigo(String codigo);
}
