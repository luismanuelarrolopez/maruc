package com.unicauca.maruc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.TipoObservacion;
import com.unicauca.maruc.repositories.TipoObservacionDAO;
import com.unicauca.maruc.service.TipoObservacionService;

@Service
public class TipoObservacionServiceImpl implements TipoObservacionService {

  @Autowired
  private TipoObservacionDAO tipoObservacionRepository;

  @Override
  public TipoObservacion findByCodigo(String codigo) {
    return tipoObservacionRepository.findByCodigo(codigo);
  }
}
