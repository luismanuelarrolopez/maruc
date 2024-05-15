package com.unicauca.maruc.service;

import java.util.List;
import java.util.Optional;

import com.unicauca.maruc.domain.model.Dependencia;
import com.unicauca.maruc.domain.model.OpcionConsecuenciaRiesgo;
import org.springframework.data.domain.Page;

import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.dto.riesgos.RiesgoDTO;
import com.unicauca.maruc.dto.riesgos.SearchRiesgosDTO.Search;

public interface RiesgoService {

  /**
   * Guarda la información inicial del riesgo.
   * 
   * @param riesgo
   * @return
   */
  Riesgo guardarInformacionInicial(Riesgo riesgo);

  /**
   * Busca un riesgo por id.
   * 
   * @param id
   * @return
   */
  Optional<Riesgo> buscarPorId(Long id);

   /**
   * Lista los riesgo registrados.
   * 
   * @param page
   * @param size
   * @return
   */
  Page<RiesgoDTO> listarRiesgosDTO(int page, int size, Boolean all);
  Page<RiesgoDTO> listarRiesgosLiderDTO(int page, int size, Boolean all, Long idLider);
  Page<Riesgo> listarRiesgos(int page, int size);

  List<RiesgoDTO> listarRiesgos();

  Page<Riesgo> listarByNombre(String key, int page, int size);

  Page<Riesgo> filterByProceso(long key, int page, int size);

  Page<Riesgo> filterByTipoRiesgo(long key, int page, int size);

  // Page<Riesgo> filterByRiesgoResidual(Integer key, int page, int size);

  // Page<Riesgo> filterByRiesgoInherente(Integer key, int page, int size);

  Riesgo SeleccionarRiesgo(long id);

  void guardarConseucenciasDelRiesgo(OpcionConsecuenciaRiesgo opcionConsecuenciaRiesgo);

  List<Riesgo> listarRiesgosByLiderProceso(Long id_lider_proceso);

  Riesgo actualizarRiesgo(Riesgo riesgo);

  Page<RiesgoDTO> filter(Search search, int page, int size);

  Integer countControlesInCumplimientoByProceso(TipoProceso tipo_proceso);

  Integer countControlesInCumplimientoByDependencia(Dependencia dependencia);

  List<Riesgo> findByestatusInformacionRiesgo_valorGreaterThanEqual(Integer valor);
}
