package com.unicauca.maruc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicauca.maruc.domain.model.TipoAfectacion;
import com.unicauca.maruc.repositories.TipoAfectacionDAO;
import com.unicauca.maruc.service.TipoAfectacionService;

@Service
public class TipoAfectacionDefaultService extends DefaultBaseService<TipoAfectacion>
    implements TipoAfectacionService {

  @Autowired
  private TipoAfectacionDAO tipoAfectacionDAO;

  @Override
  public List<TipoAfectacion> buscarConConsecuencias() {
    return tipoAfectacionDAO.findAll();
  }

}
