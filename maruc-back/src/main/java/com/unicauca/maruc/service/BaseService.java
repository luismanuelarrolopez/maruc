package com.unicauca.maruc.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface BaseService<T> {

  /**
   * Elimina una entidad por su identificador.
   * 
   * @param entidad
   */
  void eliminar(T entidad);

  /**
   * Inserta una nueva entidad.
   * 
   * @param consecuencia
   * @return
   */
  T guardar(T entidad);



  /**
   * Actualiza una entidad
   * 
   * @param entidad
   * @return
   */
  T actualizar(T entidad);

  /**
   * Busca una entidad por su identificador
   * 
   * @param id
   * @return
   */
  Optional<T> buscarPorId(Long id);

  /**
   * Lista las entidades
   * 
   * @return
   */
  List<T> buscarTodos();

  List<T> buscarTodos(Specification<T> spec);

  List<T> buscarTodos(Specification<T> spec, Sort sort);
}
