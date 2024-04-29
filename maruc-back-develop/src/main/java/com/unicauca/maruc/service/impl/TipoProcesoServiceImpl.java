package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.repositories.TipoProcesoDAO;
import com.unicauca.maruc.service.TipoProcesoService;

@Service
public class TipoProcesoServiceImpl implements TipoProcesoService {

  @Autowired
  private TipoProcesoDAO tipoProcesoRepository;

  @Override
  public List<TipoProceso> findAll() {
    return tipoProcesoRepository.findAll();
  }

  @Override
  public Optional<TipoProceso> buscarPorId(final Long id) {
    return tipoProcesoRepository.findById(id);
  }
}
