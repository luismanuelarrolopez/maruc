package com.unicauca.maruc.service;

import java.util.List;
import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.dto.riesgos.ControlDTO;

public interface ValidarControlService {

  public void generarControlesPorDefecto(Riesgo riesgo);

  List<ControlDTO> buscarControlesRiesgo(Long idRiesgo);

  boolean editarControl(Long idControl, Control control);
}
