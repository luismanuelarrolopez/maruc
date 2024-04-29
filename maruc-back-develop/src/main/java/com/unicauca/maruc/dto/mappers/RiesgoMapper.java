package com.unicauca.maruc.dto.mappers;

import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.domain.model.TipoRiesgo;
import com.unicauca.maruc.dto.riesgos.InformacionBasicaRiesgoDTO;

public class RiesgoMapper {

  public static void setValuesFromInformacionInicialDTO(
      final InformacionBasicaRiesgoDTO informacionBasicaRiesgoDTO, final Riesgo riesgo) {
    riesgo.setNombre(informacionBasicaRiesgoDTO.getNombre());
    riesgo.setRelacionConObjetivo(informacionBasicaRiesgoDTO.isRelacionConObjetivo());
    riesgo.setObjetivo(informacionBasicaRiesgoDTO.getObjetivo());
    riesgo.setMotivacion(informacionBasicaRiesgoDTO.getMotivacion());
    riesgo.setResponsabilidad(informacionBasicaRiesgoDTO.getResponsabilidad());
    riesgo.setOportunidad(informacionBasicaRiesgoDTO.getOportunidad());
    TipoRiesgo tipo_riesgo = new TipoRiesgo();
    tipo_riesgo.setId(informacionBasicaRiesgoDTO.getIdTipoRiesgo());
    TipoProceso tipoProceso = new TipoProceso();
    tipoProceso.setId(informacionBasicaRiesgoDTO.getIdProceso());
    riesgo.setTipoProceso(tipoProceso);
    riesgo.setTipoRiesgo(tipo_riesgo);
  }
}
