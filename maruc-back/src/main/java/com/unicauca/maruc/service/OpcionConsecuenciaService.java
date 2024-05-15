package com.unicauca.maruc.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.unicauca.maruc.domain.model.OpcionConsecuencia;
import com.unicauca.maruc.domain.model.OpcionConsecuenciaRiesgo;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaRiesgoDTO;

public interface OpcionConsecuenciaService {

  void guardarTodo(Set<OpcionConsecuencia> opciones);

  List<OpcionConsecuenciaRiesgoDTO> buscarConsecuenciasRiesgo(Long idRiesgo);

  Optional<OpcionConsecuenciaRiesgo> buscarPorId(Long id);

  OpcionConsecuencia buscarOpcionPorId(Long id);

  void eliminarOpcionConsecuencia(Long id);

  void eliminar(Long id);

  OpcionConsecuencia guardar(OpcionConsecuencia oc);

  OpcionConsecuencia buscarPorIdConsecuencia(Long id);

  void eliminarOpcionConsecuenciaPorIdConsecuencia(Long id);
}
