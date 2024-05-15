package com.unicauca.maruc.repositories.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;
import com.unicauca.maruc.repositories.BaseDAO;

@Repository
@Slf4j
public class BaseDAOImpl implements BaseDAO {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public <T> T buscarPorId(final Class<T> clazz, final Long id) {
    return entityManager.find(clazz, id);
  }

  @Override
  public <T> List<T> buscarTodos(final Class<T> clazz) {
    return obtenerConsulta(clazz, null, null).getResultList();
  }

  @Override
  public <T> T guardar(final Class<T> clazz, final T entidad) {
    Object id = obtenerValorId(clazz, entidad);
    log.info("???????? Guardando entidad");
    if (id == null) {
      entityManager.persist(entidad);
      return entidad;
    } else {
      log.info("????????? Actualizando entidad");
      return entityManager.merge(entidad);
    }
  }

  @Override
  public <T> void eliminar(final T entidad) {
    entityManager.remove(entidad);
  }

  private <T> TypedQuery<T> obtenerConsulta(final Class<T> nombreClase, final Specification<T> spec,
      final Sort sort) {
    final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    final CriteriaQuery<T> cq = cb.createQuery(nombreClase);

    final Root<T> raiz = aplicarEspecificacion(nombreClase, cq, spec);
    cq.select(raiz);

    if (sort != null) {
      cq.orderBy(QueryUtils.toOrders(sort, raiz, cb));
    }
    return entityManager.createQuery(cq);
  }

  private <T> Root<T> aplicarEspecificacion(final Class<T> clazz, final CriteriaQuery<T> cq,
      final Specification<T> spec) {
    final Root<T> raiz = cq.from(clazz);

    if (spec == null)
      return raiz;

    final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    final Predicate predicate = spec.toPredicate(raiz, cq, cb);
    if (predicate != null) {
      cq.where(predicate);
    }
    return raiz;
  }

  private <T> Object obtenerValorId(final Class<T> clazz, final T entity) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> query = cb.createQuery(clazz);
    Root<T> root = query.from(clazz);
    SingularAttribute<? super T, ?> id =
        root.getModel().getId(root.getModel().getIdType().getJavaType());
    Member member = id.getJavaMember();
    if (member instanceof Field) {
      Field field = (Field) member;
      if (!Modifier.isPublic(field.getDeclaringClass().getModifiers())
          || !Modifier.isPublic(field.getModifiers())) {
        field.setAccessible(true);
      }
      try {
        Object val = field.get(entity);
        return val;
      } catch (Exception e) {
        return null;
      }
    } else {
      return null;
    }
  }

  @Override
  public <T> List<T> buscarTodos(Class<T> clazz, Specification<T> spec, Sort sort) {
    return obtenerConsulta(clazz, spec, sort).getResultList();
  }
}
