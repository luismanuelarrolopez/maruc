package com.unicauca.maruc.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import com.unicauca.maruc.repositories.BaseDAO;
import com.unicauca.maruc.service.BaseService;

public class DefaultBaseService<T> implements BaseService<T> {

  @Autowired
  private BaseDAO baseDAO;

  @SuppressWarnings("rawtypes")
  private Class clazz;

  @SuppressWarnings({"unchecked"})
  private <S extends T> Class<S> getClazz() {
    if (this.clazz == null) {
      Type genType = this.getClass().getGenericSuperclass();
      if (!(genType instanceof ParameterizedType)) {
        return null;
      }
      Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
      if (!(params[0] instanceof Class)) {
        return null;
      }
      return (Class<S>) params[0];
    }
    return this.clazz;
  }

  @Override
  public void eliminar(final T entidad) {
    baseDAO.eliminar(entidad);
  }

  @Override
  public T guardar(final T entidad) {
    return baseDAO.guardar(getClazz(), entidad);
  }

  @Override
  public T actualizar(final T entidad) {
    return baseDAO.guardar(getClazz(), entidad);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<T> buscarPorId(final Long id) {
    return Optional.ofNullable(baseDAO.buscarPorId(getClazz(), id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<T> buscarTodos() {
    if (getClazz() == null) {
      return null;
    }
    return baseDAO.buscarTodos(getClazz());
  }

  @Override
  public List<T> buscarTodos(final Specification<T> spec) {
    if (getClazz() == null)
      return null;
    return baseDAO.buscarTodos(getClazz(), spec, null);
  }

  @Override
  public List<T> buscarTodos(final Specification<T> spec, final Sort sort) {
    if (getClazz() == null)
      return null;
    return baseDAO.buscarTodos(getClazz(), spec, sort);
  }
}
