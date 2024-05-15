package com.unicauca.maruc.repositories;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface BaseDAO {

  <T> T buscarPorId(Class<T> clazz, Long id);

  <T> List<T> buscarTodos(Class<T> clazz);

  <T> List<T> buscarTodos(Class<T> clazz, Specification<T> spec, Sort sort);


  <T> T guardar(Class<T> clazz, T entidad);

  <T> void eliminar(T id);
}
