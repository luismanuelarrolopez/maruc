package com.unicauca.maruc.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@NamedEntityGraph(name = "entity-grahp-solicitante", attributeNodes = { @NamedAttributeNode(value = "solicitante") })
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "asesorias")
public class Asesoria extends EntidadVersionada {
  /**
  * 
  */
  private static final long serialVersionUID = 3275193944825720270L;

  @Column
  @NotNull(message = "{Asesoria.tema.NotNull}")
  private String tema;

  @Column(name = "fecha_solicitud", updatable = false)
  @CreationTimestamp
  private LocalDateTime fechaSolicitud;

  @Column(name = "fecha_agendada")
  private LocalDateTime fechaAgendada;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "solicitante")
  @NotNull(message = "{Asesoria.solicitante.NotNull}")
  private Usuario solicitante;

  @Column(name = "oficina_asesora")
  @NotNull(message = "{Asesoria.oficinaAsesora.NotNull}")
  private String oficinaAsesora;
}
