package com.unicauca.maruc.service.impl.validacioncontrol;

import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.domain.model.PuntajeControl;

public final class ValoracionPuntajeControl {

    public static float calcularValoracion(final Control control) {
        final PuntajeControl puntaje = control.getPuntajeControl();
        float puntajeTipoControl = puntaje.getPuntajeTipoControl()*100;
        float puntajeDifusionControl = puntaje.getPuntajeDifisuionControl();
        float puntajeEjecucionSistemasDigitales = puntaje.getPuntajeEjecionSistemasDigitales();
        float puntajePeriodicidadEjecucion = puntaje.getPuntajePeriodicidadEjecucion();
        float puntajePeriodicidadSeguimiento = puntaje.getPuntajePeriodicidadSeguimiento();
        float puntajeEmpleaEjecuta = puntaje.getPuntajeSeCumpleEjecuta();
        float ret = (puntajeTipoControl)
        - (puntajeDifusionControl
                + puntajeEjecucionSistemasDigitales
                + puntajePeriodicidadEjecucion
                + puntajePeriodicidadSeguimiento
                + puntajeEmpleaEjecuta);
        return ret;
    }
}