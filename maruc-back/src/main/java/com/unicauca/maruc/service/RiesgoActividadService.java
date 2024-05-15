package com.unicauca.maruc.service;

import java.util.List;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.RiesgoActividad;
import com.unicauca.maruc.dto.riesgos.RiesgoActividadDTO;

public interface RiesgoActividadService extends BaseService<RiesgoActividad> {

  List<RiesgoActividadDTO> buscarPor(Long idRiesgo);

  List<Actividad> obtenerActividadesNoEnRiesgo(Long idRiesgo);
}
