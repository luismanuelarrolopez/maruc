package com.unicauca.maruc.service.impl.validacioncontrol;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.domain.model.DifusionControl;
import com.unicauca.maruc.domain.model.EjecucionSistemasDigitales;
import com.unicauca.maruc.domain.model.EmpleaOEjecuta;
import com.unicauca.maruc.domain.model.Periodicidad;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.dto.mappers.ControlMapper;
import com.unicauca.maruc.dto.riesgos.ControlDTO;
import com.unicauca.maruc.repositories.ControlDAO;
import com.unicauca.maruc.repositories.CrudDAO;
import com.unicauca.maruc.service.ValidarControlService;

@Service
public class ValidarControlesServiceImpl implements ValidarControlService {

  @Autowired
  private GeneradorControlesExistentes generadorControlesExistentes;
  @Autowired
  private ControlDAO controlDAO;
  @Autowired
  private CrudDAO crudDAO;

  @Override
  public void generarControlesPorDefecto(final Riesgo riesgo) {
    generadorControlesExistentes.generarControlesPorDefecto(riesgo);
  }

  @Override
  public List<ControlDTO> buscarControlesRiesgo(final Long idRiesgo) {
    return controlDAO.findByRiesgoId(idRiesgo).stream().map(ControlMapper::mapToDTO).toList();
  }

  @Override
  public boolean editarControl(final Long idControl, final Control control) {
    final Control c = controlDAO.findById(idControl).orElseThrow(IllegalArgumentException::new);
    c.setNombre(control.getNombre());
    c.setAplica(control.isAplica());
    c.setResponsable(control.getResponsable());
    c.setPeriodicidadSegumiento(
        crudDAO.findById(Periodicidad.class, control.getPeriodicidadSegumiento().getId()));
    c.setPeriodicidadEjecucion(
        crudDAO.findById(Periodicidad.class, control.getPeriodicidadEjecucion().getId()));
    c.setDifusion(crudDAO.findById(DifusionControl.class, control.getDifusion().getId()));
    c.setEjecucionSistemasDigitales(crudDAO.findById(EjecucionSistemasDigitales.class,
        control.getEjecucionSistemasDigitales().getId()));
    c.setEmpleaOEjecuta(
        crudDAO.findById(EmpleaOEjecuta.class, control.getEmpleaOEjecuta().getId()));
    CalculadoraPuntajeControl.calcularPuntajes(c);
    controlDAO.save(c);
    return true;
  }
}
