package com.unicauca.maruc.facade;

import java.util.List;

import org.springframework.data.domain.Page;

import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.Causa;
import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.domain.model.DifusionControl;
import com.unicauca.maruc.domain.model.EntidadCatalogo;
import com.unicauca.maruc.domain.model.Periodicidad;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.RiesgoActividad;
import com.unicauca.maruc.domain.model.TipoAfectacion;
import com.unicauca.maruc.domain.model.TipoControl;
import com.unicauca.maruc.domain.model.TipoRiesgo;
import com.unicauca.maruc.dto.riesgos.CausaDTO;
import com.unicauca.maruc.dto.riesgos.ControlDTO;
import com.unicauca.maruc.dto.riesgos.ControlResidualDTO;
import com.unicauca.maruc.dto.riesgos.IndicadorDTO;
import com.unicauca.maruc.dto.riesgos.InformacionBasicaRiesgoDTO;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaRiesgoDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoInherenteDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoResidualDTO;
import com.unicauca.maruc.dto.riesgos.SearchRiesgosDTO.Search;
import com.unicauca.maruc.exceptions.EntityNotFountException;

public interface RiesgoManagerFacade {

  List<TipoAfectacion> buscarConConsecuencias();

  List<DifusionControl> buscarDifusionesControl();

  List<Periodicidad> buscarPeriodicidades();

  /**
   * Obtiene todos los tipos de riesgo.
   *
   * @return
   */
  List<TipoRiesgo> buscarTiposDeRiesgo();

  /**
   * Carga toda la información asociada a un riesgo.
   *
   * @param idRiesgo
   * @return
   */
  RiesgoDTO cargarRiesgo(Long idRiesgo);

  Page<Riesgo> filterByProceso(long key, int page, int size);

  // Page<Riesgo> filterByRiesgoInherente(Integer key, int page, int size);

  // Page<Riesgo> filterByRiesgoResidual(Integer key, int page, int size);

  Page<Riesgo> filterByTipoRiesgo(long key, int page, int size);

  boolean guardarActividadesRiesgo(List<RiesgoActividad> actividadesRiesgo);

  /**
   * Guarda las causas asosiadas a un riesgo.
   *
   * @param causas
   */
  void guardarCausas(List<CausaDTO> causas);

  boolean guardarControles(List<ControlDTO> controles);

  /**
   * Registra la información inicial de un riesgo.
   *
   * @param riesgo
   * @return
   */
  Riesgo guardarInformacionInicial(InformacionBasicaRiesgoDTO informacionBasicaRiesgoDTO);

  List<Actividad> listarActividades();

  Page<Riesgo> listarByNombre(String key, int page, int size);

  Page<RiesgoDTO> filter(Search search, int page, int size);

  /**
   * Lista todos los riesgo registrados.
   *
   * @param page
   * @param size
   * @return
   */
  Page<RiesgoDTO> listarRiesgosDTO(int page, int size, Boolean all);
  Page<RiesgoDTO> listarRiesgosLiderDTO(int page, int size, Boolean all, long idLider);
  
  List<RiesgoDTO> listarRiesgos();

  Page<Riesgo> listarRiesgos(int page, int size);

  List<TipoControl> listarTiposDeControl();

  Riesgo SeleccionarRiesgoPorId(Long id);

  boolean actualizarEstadoRiesgo(Long idRiesgo, String codigoEstado);

  Riesgo actualizarInformacionBasica(InformacionBasicaRiesgoDTO informacionBasicaRiesgoDTO)
      throws EntityNotFountException;

  Causa guardarCausa(CausaDTO causa);

  Causa actualizarCausa(CausaDTO causa) throws EntityNotFountException;

  List<CausaDTO> listarCausas(Long idRiesgo);

  List<CausaDTO> buscarCriticas(Long idRiesgo);

  boolean eliminarCausa(Long idCausa);

  /*
   * Consecuencias
   */

  List<OpcionConsecuenciaRiesgoDTO> listarConsecuencias(Long idRiesgo);

  OpcionConsecuenciaRiesgoDTO guardarConsecuencia(
      OpcionConsecuenciaRiesgoDTO opcionConsecuenciaRiesgoDTO);

  boolean eliminarConsecuencia(Long idConsecuencia);

  List<Riesgo> listarRiesgosByLiderProceso(Long id_lider_proceso);

  /*
   * Riesgo inherente
   */
  RiesgoInherenteDTO calcularRiesgoInherente(Long idRiesgo);

  <T extends EntidadCatalogo> List<T> cargarCatalogo(Class<T> Clasecatalogo);

  boolean editarControl(Long idControl, Control control);

  /*
   * Riesgo residual
   */
  RiesgoResidualDTO calcularNivelRiesgoResidual(Long idRiesgo);

  List<ControlResidual> buscarPorRiesgoResidual(Long idRiesgoResidual);

  boolean guardarRiesgoResidual(RiesgoResidualDTO riesgoResidualDTO);

  boolean actualizarRiesgoResidual(Long idRiesgoResidual, RiesgoResidualDTO riesgoResidualDTO);

  boolean guardarControlResidual(Long idRiesgoResidual, ControlResidualDTO controlResidualDTO);

  boolean eliminarControlResidual(Long idControlResidual);

  List<IndicadorDTO> generarIndicadores();

  boolean editarControlResidual(Long idControlResidual, ControlResidualDTO controlResidualDTO);

  void actualizarMayorConsecuencia(Long idRiesgo, String consecuencia);
}
