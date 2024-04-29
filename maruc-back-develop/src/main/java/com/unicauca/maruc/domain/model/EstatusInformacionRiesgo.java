package com.unicauca.maruc.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "estatus_info_riesgo")
@Entity
@Getter
@Setter
public class EstatusInformacionRiesgo extends EntidadCatalogo {

    @Column(name = "valor", nullable = false, unique = true)
    private Integer valor;
}
