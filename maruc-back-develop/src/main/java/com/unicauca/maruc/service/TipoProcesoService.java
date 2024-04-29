package com.unicauca.maruc.service;

import java.util.List;
import java.util.Optional;

import com.unicauca.maruc.domain.model.TipoProceso;

public interface TipoProcesoService {

    List<TipoProceso> findAll();

    Optional<TipoProceso> buscarPorId(Long id);
}
