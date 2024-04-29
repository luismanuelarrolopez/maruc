/**
 *
 */
package com.unicauca.maruc.facade.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.RiesgoActividad;
import com.unicauca.maruc.dto.mappers.RiesgoActividadMapper;
import com.unicauca.maruc.dto.riesgos.RiesgoActividadDTO;
import com.unicauca.maruc.facade.ActividadesRiesgoFacade;
import com.unicauca.maruc.service.RiesgoActividadService;

/**
 * @author Desktop
 *
 */
@Service
public class ActividadesRiesgoFacadesImpl implements ActividadesRiesgoFacade {

  @Autowired
  private RiesgoActividadService riesgoActividadService;

  @Override
  @Transactional
  public boolean guardarRiesgo(final RiesgoActividadDTO riesgoActividadDTO) {
    final RiesgoActividad ra = RiesgoActividadMapper.mapFromWebModel(riesgoActividadDTO);
    return riesgoActividadService.guardar(ra) != null;
  }

  @Override
  public List<RiesgoActividadDTO> buscarActividadesPor(final Long idRiesgo) {
    return riesgoActividadService.buscarPor(idRiesgo);
  }

  @Override
  @Transactional
  public boolean eliminarActividadPor(final Long idRiesgoActividad) {
    final RiesgoActividad ra = riesgoActividadService.buscarPorId(idRiesgoActividad)
        .orElseThrow(IllegalArgumentException::new);
    riesgoActividadService.eliminar(ra);
    return true;
  }

  @Override
  public List<Actividad> obtenerActividadesNoEnRiesgo(final Long idRiesgo) {
    return riesgoActividadService.obtenerActividadesNoEnRiesgo(idRiesgo);
  }

}
