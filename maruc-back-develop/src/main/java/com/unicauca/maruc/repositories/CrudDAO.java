package com.unicauca.maruc.repositories;

import java.util.List;
import com.unicauca.maruc.domain.model.EntidadBase;

public interface CrudDAO {

  <T extends EntidadBase> T findByCodigo(Class<T> clazz, String codigo);

  <T extends EntidadBase> List<T> findAll(Class<T> clazz);

  <T extends EntidadBase> T findById(Class<T> clazz, Long id);
}
