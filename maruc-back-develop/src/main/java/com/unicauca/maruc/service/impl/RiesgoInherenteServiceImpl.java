package com.unicauca.maruc.service.impl;

import java.util.NavigableMap;
import java.util.OptionalDouble;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaRiesgoDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoActividadDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoInherenteDTO;
import com.unicauca.maruc.repositories.ActividadRiesgoDAO;
import com.unicauca.maruc.repositories.OpcionConsecuenciaRiesgoDAO;
import com.unicauca.maruc.service.RiesgoInherenteService;

@Service
public class RiesgoInherenteServiceImpl implements RiesgoInherenteService {

  @Autowired
  private OpcionConsecuenciaRiesgoDAO opcionConsecuenciaRiesgoDAO;
  @Autowired
  private ActividadRiesgoDAO actividadRiesgoDAO;

  @Override
  public RiesgoInherenteDTO calcularRiesgoInherente(final Long idRiesgo) {
    final Integer impacto = calcularImpacto(idRiesgo);
    final Integer nivelProbabilidad = calcularNivelProbabilidad(idRiesgo);
    final Integer valoracion = impacto * nivelProbabilidad;
    final String tolerancia = calcularTolerancia(valoracion);
    return new RiesgoInherenteDTO(impacto, nivelProbabilidad, valoracion, tolerancia);
  }

  private Integer calcularImpacto(final Long idRiesgo) {
    final Integer sumatoriaConsecuencias = calcularSumatoriaConsecuencias(idRiesgo);
    final NavigableMap<Integer, Integer> map = new TreeMap<>();
    map.put(0, 1);
    map.put(13, 3);
    map.put(25, 5);
    map.put(37, 10);
    map.put(49, 20);
    return map.floorEntry(sumatoriaConsecuencias).getValue();
  }

  private Integer calcularSumatoriaConsecuencias(final Long idRiesgo) {
    return opcionConsecuenciaRiesgoDAO.buscarConsecuenciasRiesgo(idRiesgo).stream()
        .map(OpcionConsecuenciaRiesgoDTO::getPuntaje).reduce(0, Integer::sum);
  }

  private Integer calcularNivelProbabilidad(final Long idRiesgo) {
    final OptionalDouble average = actividadRiesgoDAO.buscarPorRiesgo(idRiesgo).stream()
        .mapToInt(RiesgoActividadDTO::getFrecuencia).average();
    if (average.isPresent()) {
      final Double avg = average.getAsDouble();
      return calcularNivelProbabilidad(avg);
    }
    return 0;
  }

  private Integer calcularNivelProbabilidad(final Double average) {
    final NavigableMap<Double, Integer> map = new TreeMap<>();
    map.put(0D, 1);
    map.put(21D, 2);
    map.put(41D, 3);
    map.put(61D, 4);
    map.put(81D, 5);
    return map.floorEntry(average).getValue();
  }

  private String calcularTolerancia(final Integer valoracion) {
    final NavigableMap<Integer, String> map = new TreeMap<>();
    map.put(1, "Bajo");
    map.put(4, "Medio");
    map.put(10, "Alto");
    map.put(21, "Extremo");
    return map.floorEntry(valoracion).getValue();
  }
}
