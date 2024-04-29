package com.unicauca.maruc.domain.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class EntidadVersionada extends EntidadBase {

  /**
   * 
   */
  private static final long serialVersionUID = -4900715380104294572L;

  @Version
  @JsonIgnore
  private @Getter @Setter int version;
}
