package com.unicauca.maruc.service.impl.validacioncontrol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.domain.model.DifusionControl;
import com.unicauca.maruc.domain.model.EjecucionSistemasDigitales;
import com.unicauca.maruc.domain.model.EmpleaOEjecuta;
import com.unicauca.maruc.domain.model.Periodicidad;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.TipoControl;
import com.unicauca.maruc.repositories.ControlDAO;
import com.unicauca.maruc.repositories.CrudDAO;
import com.unicauca.maruc.repositories.TipoControlDAO;

@Component
public class GeneradorControlesExistentes {

  private static final boolean APLICA_RIESGO_POR_DEFECTO = false;
  private static final String CODIGO_PERIODICIDAD_POR_DEFECTO = "10";
  private static final String CODIGO_DIFUSION_POR_DEFECTO = "5";
  private static final String CODIGO_EJECUCION_POR_DEFECTO = "3";
  private static final String CODIGO_EMPLEA_POR_DEFECTO = "2";

  @Autowired
  private TipoControlDAO tipoControlDAO;
  @Autowired
  private CrudDAO crudDAO;
  @Autowired
  private ControlDAO controlDAO;

  public void generarControlesPorDefecto(final Riesgo riesgo) {
    final List<TipoControl> tiposDeControl = tipoControlDAO.findAll();
    for (final TipoControl tc : tiposDeControl) {
      final Control control = new Control();
      control.setRiesgo(riesgo);
      control.setAplica(APLICA_RIESGO_POR_DEFECTO);
      control.setTipoControl(tc);
      final Periodicidad periodicidadPorDefecto = crudDAO.findByCodigo(Periodicidad.class,
          CODIGO_PERIODICIDAD_POR_DEFECTO);
      final DifusionControl difusionControlPorDefecto = crudDAO.findByCodigo(DifusionControl.class,
          CODIGO_DIFUSION_POR_DEFECTO);

      control.setPeriodicidadEjecucion(periodicidadPorDefecto);
      control.setPeriodicidadSegumiento(periodicidadPorDefecto);
      control.setDifusion(difusionControlPorDefecto);
      control.setEjecucionSistemasDigitales(
          crudDAO.findByCodigo(EjecucionSistemasDigitales.class, CODIGO_EJECUCION_POR_DEFECTO));
      control.setEmpleaOEjecuta(crudDAO.findByCodigo(EmpleaOEjecuta.class, CODIGO_EMPLEA_POR_DEFECTO));
      CalculadoraPuntajeControl.calcularPuntajes(control);
      controlDAO.save(control);
    }
  }
}
