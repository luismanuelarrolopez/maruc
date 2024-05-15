package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RiesgoInherenteDTO implements Serializable{

  private final Integer impacto;
  private final Integer nivelProbabilidad;
  private final Integer valoracion;
  private final String tolerancia;
}
