package com.unicauca.maruc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicauca.maruc.domain.model.EntidadCatalogo;
import com.unicauca.maruc.repositories.CrudDAO;

@Service
public class CatalogoService {

  @Autowired
  private CrudDAO crudDAO;

  public <T extends EntidadCatalogo> List<T> cargarCatalogo(final Class<T> claseCatalogo) {
    return crudDAO.findAll(claseCatalogo);
  }
}
