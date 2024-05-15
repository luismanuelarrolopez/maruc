package com.unicauca.maruc.dto.mappers;

import org.springframework.beans.BeanUtils;
import com.unicauca.maruc.domain.model.Causa;
import com.unicauca.maruc.dto.riesgos.CausaDTO;

public class CausaMapper {

  public static void mapFromDTO(final CausaDTO causaDTO, final Causa causa) {
    causa.setCausa(causaDTO.getCausa());
    causa.setPuntaje1(causaDTO.getPuntaje1());
    causa.setPuntaje2(causaDTO.getPuntaje2());
    causa.setPuntaje3(causaDTO.getPuntaje3());
    causa.setPorque1(causaDTO.getPorque1());
    causa.setPorque2(causaDTO.getPorque2());
    causa.setPorque3(causaDTO.getPorque3());
    causa.setSumatoria(causaDTO.getSumatoria());
    causa.setCausaInicial(causaDTO.getCausaInicial());
  }

  public static Causa mapFromDTO(final CausaDTO causaDto) {
    final Causa causa = new Causa(causaDto.getCausa(), causaDto.getPuntaje1(),
        causaDto.getPuntaje2(), causaDto.getPuntaje3(), causaDto.getPorque1(),
        causaDto.getPorque2(), causaDto.getPorque3(), null);
    causa.setSumatoria(causaDto.getSumatoria());
    return causa;
  }

  public static CausaDTO mapFromEntity(final Causa causa) {
    final CausaDTO causaDTO = new CausaDTO();
    BeanUtils.copyProperties(causa, causaDTO);
    causaDTO.setIdRiesgo(causa.getRiesgo().getId());
    return causaDTO;
  }
}
