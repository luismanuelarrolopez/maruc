package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicauca.maruc.domain.model.TipoRiesgo;
import com.unicauca.maruc.repositories.TipoRiesgoDAO;
import com.unicauca.maruc.service.TipoRiesgoService;

@Service
public class TipoRiesgoServiceImpl implements TipoRiesgoService {

  @Autowired
  private TipoRiesgoDAO tipoRiesgoRepository;

  @Override
  public List<TipoRiesgo> findAll() {
    return tipoRiesgoRepository.findAll();
  }

  @Override
  public Optional<TipoRiesgo> buscarPorId(final Long id) {
    return tipoRiesgoRepository.findById(id);
  }
}
