package com.unicauca.maruc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.RiesgoActividad;
import com.unicauca.maruc.dto.riesgos.RiesgoActividadDTO;
import com.unicauca.maruc.repositories.ActividadRiesgoDAO;
import com.unicauca.maruc.service.RiesgoActividadService;

@Service
@Transactional
public class RiesgoActividadServiceImpl extends DefaultBaseService<RiesgoActividad>
    implements RiesgoActividadService {

  @Autowired
  private ActividadRiesgoDAO riesgoActividadDAO;

  @Override
  @Transactional(readOnly = true)
  public List<RiesgoActividadDTO> buscarPor(final Long idRiesgo) {
    return riesgoActividadDAO.buscarPorRiesgo(idRiesgo);
  }

  @Override
  public List<Actividad> obtenerActividadesNoEnRiesgo(final Long idRiesgo) {
    return riesgoActividadDAO.buscarNoEnRiesgo(idRiesgo);
  }
}
