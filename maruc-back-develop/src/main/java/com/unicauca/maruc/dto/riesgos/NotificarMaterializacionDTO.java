package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NotificarMaterializacionDTO implements Serializable {

    private static final long serialVersionUID = 1517930032926601011L;
    private Long id_riesgo;
    private String descripcion;
}
