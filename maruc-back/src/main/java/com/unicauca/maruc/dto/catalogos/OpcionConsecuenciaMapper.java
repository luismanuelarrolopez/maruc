package com.unicauca.maruc.dto.catalogos;

import com.unicauca.maruc.domain.model.OpcionConsecuencia;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaDTO;
import java.io.Serializable;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpcionConsecuenciaMapper implements Serializable {

  public static OpcionConsecuenciaDTO toDto(final OpcionConsecuencia opcionConsecuencia) {
    return OpcionConsecuenciaDTO.builder()
        .id(opcionConsecuencia.getId())
        .descripcion(opcionConsecuencia.getDescripcion())
        .puntaje(opcionConsecuencia.getPuntaje())
        .build();
  }

  public static OpcionConsecuencia fromDto(final OpcionConsecuenciaDTO opcionConsecuenciaDTO) {
    log.info("Descripción de la opción: {}", opcionConsecuenciaDTO.getDescripcion());
    OpcionConsecuencia opcion = OpcionConsecuencia.builder()
        .puntaje(opcionConsecuenciaDTO.getPuntaje())
        .descripcion(opcionConsecuenciaDTO.getDescripcion())
        .build();
    if (Objects.nonNull(opcionConsecuenciaDTO.getId())) {
      opcion.setId(opcionConsecuenciaDTO.getId());
    }
    return opcion;
  }

  public static OpcionConsecuencia setFromDto(final OpcionConsecuenciaDTO opcionConsecuenciaDTO,
      OpcionConsecuencia opcionConsecuenciaRiesgo) {
    opcionConsecuenciaRiesgo.setDescripcion(opcionConsecuenciaDTO.getDescripcion());
    opcionConsecuenciaRiesgo.setPuntaje(opcionConsecuenciaDTO.getPuntaje());
    return opcionConsecuenciaRiesgo;
  }
}
