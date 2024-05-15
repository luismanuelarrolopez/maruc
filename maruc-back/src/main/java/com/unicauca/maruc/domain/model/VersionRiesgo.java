package com.unicauca.maruc.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "VERSION_RIESGO")
public class VersionRiesgo extends EntidadVersionada{

    @NotNull
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ruta_version")
    private String ruta_version;
}
