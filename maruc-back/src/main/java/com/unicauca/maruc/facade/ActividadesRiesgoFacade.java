package com.unicauca.maruc.facade;

import java.util.List;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.dto.riesgos.RiesgoActividadDTO;

public interface ActividadesRiesgoFacade {

  boolean guardarRiesgo(RiesgoActividadDTO riesgoActividadDTO);

  List<RiesgoActividadDTO> buscarActividadesPor(Long idRiesgo);

  boolean eliminarActividadPor(Long idRiesgoActividad);

  List<Actividad> obtenerActividadesNoEnRiesgo(Long idRiesgo);
}
