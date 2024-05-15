package com.unicauca.maruc.service;

import java.util.List;
import java.util.Optional;
import com.unicauca.maruc.domain.model.TipoRiesgo;

public interface TipoRiesgoService {

  List<TipoRiesgo> findAll();

  Optional<TipoRiesgo> buscarPorId(Long id);
}
