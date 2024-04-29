package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TipoAfectacionDTO implements Serializable {

    private String id;
    private String nombre;
    private String codigo;
}
