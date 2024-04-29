package com.unicauca.maruc.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "usuarios")
public class Usuario extends EntidadVersionada {

  /**
   *
   */
  private static final long serialVersionUID = 3984902098638610959L;

  public Usuario(final String email, final String password, final String nombres,
      final String apellidos) {
    this.email = email;
    this.password = password;
    this.nombres = nombres;
    this.apellidos = apellidos;
  }

  @NotBlank(message = "{Usuario.nombres.NotBlank}")
  private String nombres;
  @NotBlank(message = "{Usuario.apellidos.NotBlank}")
  private String apellidos;
  @JsonIgnore
  @NotBlank(message = "{Usuario.password.NotBlank}")
  private String password;
  @NotBlank(message = "{Usuario.email.NotBlank}")
  @Email(message = "{Usuario.email.Email}")
  private String email;
  private String uuid;
  private boolean verificado;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  private Rol rol;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private Dependencia dependencia;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
  private List<Riesgo> riesgos;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
  private List<Notificacion> notificaciones;

  public Usuario(final Long id) {
    this.id = id;
  }

  public Usuario() {
    this.riesgos = new ArrayList<Riesgo>();
    this.notificaciones = new ArrayList<Notificacion>();
  }
}
