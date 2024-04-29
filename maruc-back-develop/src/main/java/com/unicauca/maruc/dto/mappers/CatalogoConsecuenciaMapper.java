package com.unicauca.maruc.dto.mappers;

import com.unicauca.maruc.domain.model.Consecuencia;
import com.unicauca.maruc.domain.model.OpcionConsecuencia;
import com.unicauca.maruc.domain.model.TipoAfectacion;
import com.unicauca.maruc.domain.model.enums.TipoCampo;
import com.unicauca.maruc.dto.catalogos.CatalogoConsecuenciaDTO;
import com.unicauca.maruc.dto.catalogos.OpcionConsecuenciaMapper;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
@Slf4j
public class CatalogoConsecuenciaMapper {

  public static CatalogoConsecuenciaDTO toDto(final Consecuencia consecuencia) {
    return CatalogoConsecuenciaDTO.builder()
        .id(consecuencia.getId())
        .descripcion(consecuencia.getDescripcion())
        .idTipoAfectacion(consecuencia.getTipoAfectacion().getId())
        .tipoAfectacion(consecuencia.getTipoAfectacion().getDescripcion())
        .tipoCampo(consecuencia.getTipoCampo().name())
        .listaOpciones(
            consecuencia.getOpciones().stream().map(OpcionConsecuenciaMapper::toDto).toList())
        .build();
  }

  public static Consecuencia fromDto(final Consecuencia actual,
      final CatalogoConsecuenciaDTO dto) {
    actual.setDescripcion(dto.getDescripcion());
    actual.setTipoCampo(TipoCampo.valueOf(dto.getTipoCampo()));
    actual.setTipoAfectacion(new TipoAfectacion(dto.getIdTipoAfectacion()));
    return actual;
  }

  public static List<CatalogoConsecuenciaDTO> mapPageToDto(Page<Consecuencia> consecuencias) {
    return consecuencias.stream().map(CatalogoConsecuenciaMapper::toDto).toList();
  }
}
