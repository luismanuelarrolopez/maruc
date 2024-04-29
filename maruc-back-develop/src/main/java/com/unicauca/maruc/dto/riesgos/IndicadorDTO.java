package com.unicauca.maruc.dto.riesgos;
import java.io.Serializable;

import com.unicauca.maruc.domain.model.enums.EIndicador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class IndicadorDTO implements Serializable{
    private static final long serialVersionUID = -5678252111078472957L;
    private String name;
    private String first;
    private Double first_value;
    private String second;
    private Double second_value;
    private String message;
    private Double value;
    private EIndicador tipo_indicador;

}
