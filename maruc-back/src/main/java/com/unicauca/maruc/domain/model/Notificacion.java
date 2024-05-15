package com.unicauca.maruc.domain.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "NOTIFICACION")
public class Notificacion extends EntidadVersionada{

    @Column(name="notificacion")
    private String notificacion;

    @Column(name="enlace")
    private String enlace;

    @Column(name="leida")
    private boolean leida;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
