package com.unicauca.maruc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.TipoActor;
import com.unicauca.maruc.repositories.TipoActorDAO;
import com.unicauca.maruc.service.TipoActorService;

@Service
public class TipoActorServiceImpl implements TipoActorService {

  @Autowired
  private TipoActorDAO tipoActorRepository;

  @Override
  public TipoActor findByCodigo(String codigo) {
    return tipoActorRepository.findByCodigo(codigo);
  }
}
