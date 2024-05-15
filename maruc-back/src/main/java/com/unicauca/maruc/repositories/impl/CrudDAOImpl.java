package com.unicauca.maruc.repositories.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import com.unicauca.maruc.domain.model.EntidadBase;
import com.unicauca.maruc.repositories.CrudDAO;

@Component
public class CrudDAOImpl implements CrudDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  public <T extends EntidadBase> T findByCodigo(final Class<T> clazz, final String codigo) {
    final CriteriaBuilder cb = em.getCriteriaBuilder();
    final CriteriaQuery<T> cq = cb.createQuery(clazz);
    final Root<T> root = cq.from(clazz);
    cq.select(root).where(cb.equal(root.get("codigo"), codigo));
    final TypedQuery<T> tq = em.createQuery(cq);
    return tq.getSingleResult();
  }

  @Override
  public <T extends EntidadBase> List<T> findAll(final Class<T> clazz) {
    final CriteriaBuilder cb = em.getCriteriaBuilder();
    final CriteriaQuery<T> cq = cb.createQuery(clazz);
    final Root<T> root = cq.from(clazz);
    cq.select(root);
    final TypedQuery<T> tq = em.createQuery(cq);
    return tq.getResultList();
  }

  @Override
  public <T extends EntidadBase> T findById(final Class<T> clazz, final Long id) {
    final CriteriaBuilder cb = em.getCriteriaBuilder();
    final CriteriaQuery<T> cq = cb.createQuery(clazz);
    final Root<T> root = cq.from(clazz);
    cq.select(root).where(cb.equal(root.get("id"), id));
    final TypedQuery<T> tq = em.createQuery(cq);
    return tq.getSingleResult();
  }

}
