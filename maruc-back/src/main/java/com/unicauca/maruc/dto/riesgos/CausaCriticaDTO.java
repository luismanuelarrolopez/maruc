package com.unicauca.maruc.dto.riesgos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CausaCriticaDTO {

  private final Long idCausa;
  private final String nombreCausa;
  private final Float valoracion;
}