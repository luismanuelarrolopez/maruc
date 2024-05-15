package com.unicauca.maruc.domain.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class EntidadBase implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3342603217442299276L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  protected @Getter @Setter Long id;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private @Getter @Setter Date fechaCreacion;

  @UpdateTimestamp
  @Column(name = "update_at")
  private @Getter @Setter Date fechaActualizacion;
}
