package com.unicauca.maruc.facade;

import com.unicauca.maruc.dto.riesgos.CausaDTO;
import com.unicauca.maruc.dto.riesgos.GuardarControlResidualRequest;
import java.util.List;

public interface ControlResidualFacade {

  List<CausaDTO> obtenerCausasSinControl(Long riesgoId);

  List<CausaDTO> obtenerCausasDelControl(Long riesgoId, Long controlId);

  boolean guardarControlResidual(GuardarControlResidualRequest request);

  boolean actualizarControlResidual(Long id, GuardarControlResidualRequest request);
}
