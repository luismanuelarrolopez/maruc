package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ListarConsecuenciasRequest implements Serializable {

    private Long idConsecuencia;
    private String nombre;
    private String tipoAfectacion;
}
