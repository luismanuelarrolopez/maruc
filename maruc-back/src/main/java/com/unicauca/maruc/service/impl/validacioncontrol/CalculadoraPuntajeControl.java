package com.unicauca.maruc.service.impl.validacioncontrol;

import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.domain.model.PuntajeControl;

public class CalculadoraPuntajeControl {

  public static void calcularPuntajes(final Control control) {
    final PuntajeControl puntajeControl = new PuntajeControl();
    final float puntajeTipoControl = calcularTipoControl(control);
    puntajeControl.setPuntajeTipoControl(puntajeTipoControl);
    puntajeControl.setPuntajeDifisuionControl(calcularDifusion(control, puntajeTipoControl));
    puntajeControl
        .setPuntajeEjecionSistemasDigitales(calcularSistemasDigitales(control, puntajeTipoControl));
    puntajeControl.setPuntajeSeCumpleEjecuta(
        (float) (control.getEmpleaOEjecuta().getValor() * puntajeTipoControl));
    puntajeControl.setPuntajePeriodicidadEjecucion(
        (float) control.getPeriodicidadEjecucion().getValor() * puntajeTipoControl);
    puntajeControl.setPuntajePeriodicidadSeguimiento(
        (float) control.getPeriodicidadSegumiento().getValor() * puntajeTipoControl);
    control.setPuntajeControl(puntajeControl);
  }

  private static float calcularSistemasDigitales(final Control control,
      final float puntajeTipoControl) {
    return control.getEjecucionSistemasDigitales().getValor() * puntajeTipoControl;
  }

  private static float calcularTipoControl(final Control control) {
    return (float) (control.isAplica() ? control.getTipoControl().getValor() * 1.0
        : control.getTipoControl().getValor() / 2.0);
  }

  private static float calcularDifusion(final Control control, final float puntajeTipoControl) {
    return (float) (control.getDifusion().getValor() * puntajeTipoControl);
  }
}
