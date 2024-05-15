package com.unicauca.maruc.service.impl.riesgoresidual;

import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicauca.maruc.service.ControlService;
import com.unicauca.maruc.service.RiesgoInherenteService;
import com.unicauca.maruc.service.impl.validacioncontrol.ValoracionPuntajeControl;

@Component
public class CalculadoraNivelRiesgoResidual {

    @Autowired
    private ControlService controlService;

    @Autowired
    private RiesgoInherenteService riesgoInherenteService;

    public String ejecutar(final Long idRiesgo) {
        Integer puntajeControles = obtenerPuntajeControles(idRiesgo);
        Integer riesgoInherente = riesgoInherenteService.calcularRiesgoInherente(idRiesgo).getValoracion();
        Double valoracion = (double) (riesgoInherente / puntajeControles);
        return obtenerEtiquetaNivelRiesgo(valoracion);
    }

    private String obtenerEtiquetaNivelRiesgo(Double valoracion) {
        final NavigableMap<Double, String> map = new TreeMap<>();
        map.put(0.25, "Bajo");
        map.put(3.75, "Medio");
        map.put(10.0, "Alto");
        map.put(100.0, "Extremo");
        return map.floorEntry(valoracion).getValue();
    }

    private Integer obtenerPuntajeControles(final Long idRiesgo) {
        float totalPuntajes = controlService.buscarPorIdRiesgo(idRiesgo)
                .stream().map(ValoracionPuntajeControl::calcularValoracion)
                .reduce(0.0f, (a, b) -> a + b);
        return calcularPuntajeControles(totalPuntajes);
    }

    private Integer calcularPuntajeControles(float totalPuntajes) {
        if (enRango(totalPuntajes, 0, 60)) {
            return 1;
        } else if (enRango(totalPuntajes, 61, 80)) {
            return 2;
        } else if (totalPuntajes > 80) {
            return 4;
        }
        return 0;
    }

    private boolean enRango(float x, int a, int b) {
        return x >= a && x <= b;
    }
}
