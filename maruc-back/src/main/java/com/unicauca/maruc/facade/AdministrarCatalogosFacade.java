package com.unicauca.maruc.facade;

import com.unicauca.maruc.dto.catalogos.CatalogoConsecuenciaDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.Consecuencia;
import com.unicauca.maruc.domain.model.Rol;
import com.unicauca.maruc.domain.model.TipoActor;
import com.unicauca.maruc.domain.model.TipoAfectacion;
import com.unicauca.maruc.domain.model.TipoObservacion;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.domain.model.enums.ERol;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.exceptions.ReglaNegocioExcepcion;

/**
 * Permite administrar la información de los catálogos de consecuencias y actividades.
 * 
 * @author Sebastián Carabali
 */
public interface AdministrarCatalogosFacade {

  /**
   * Registra una nueva actividad en el catálogo de actividades.
   * 
   * @param actividad
   * @return - la actividad guardada
   */
  Actividad registrarActividad(Actividad actividad);

  /**
   * Encuentra todas las actividades registradas.
   * 
   * @return - Lista de actividades
   */
  Page<Actividad> listarActividades(int page, int size);

  /**
   * Edita una actividad registrada en el sistema.
   *
   * @param id
   * @param actividad
   * @return
   */
  Actividad editarActividad(Long id, Actividad actividad) throws EntityNotFountException;

  /**
   * Elimina una actividad de la base de datos por el id de la misma.
   *
   * @param idActividad - id de la actividad a eliminar.
   * @return true si se elimina correctamente.
   */
  void eliminarActividad(Long idActividad) throws EntityNotFountException;

  /**
   * Busca una actividad por el identificador.
   * 
   * @param id - Identificador de la actividad
   * @return <code>Actividad</code>
   * @throws EntityNotFountException
   */
  Actividad buscarActividad(Long id) throws EntityNotFountException;


  /**
   * Registra una nueva consecuencia en el catálogo de consecuencias.
   * 
   * @param consecuencia
   * @return - la consecuencia guardada
   */
  Consecuencia registrarConsecuencia(Consecuencia consecuencia) throws ReglaNegocioExcepcion;

  /**
   * Actualiza la información de la consecuencia
   * 
   * @param consecuencia - Consecuencia a actualizar
   * @return Consecuencia actualizada
   */
  Consecuencia editarConsecuencia(CatalogoConsecuenciaDTO consecuencia) throws EntityNotFountException;

  /**
   * Busca una consecuencia por su identificador
   * 
   * @param id - Identificador de la consecuencia
   * @return
   */
  CatalogoConsecuenciaDTO buscarConsecuencia(Long id) throws EntityNotFountException;

  /**
   * Lista las consecuencias registradas en bd
   * 
   * @return
   */
  Page<CatalogoConsecuenciaDTO> listarConsecuencias(int page, int size);

  /**
   * Elimina una consecuencia por su identificador
   * 
   * @param id - Identificador de la consecuencia
   * @return
   * @throws EntityNotFountException - Si no se encuentra la cosnecuencia
   */
  Long eliminarConsecuencia(Long id) throws EntityNotFountException;

  /**
   * Obtiene los tipos de afectación configurados
   * 
   * @return
   */
  List<TipoAfectacion> buscarTiposAfectacion();

  /**
   * Lista los tipos proceso registrados en bd
   * 
   * @return
   */
  List<TipoProceso> listarTipoProceso();

  /**
   * Obtiene un tipo actor por código	
   * 
   * @param codigo - Código del tipo actor
   * @return - el tipo de actor
   */
  TipoActor buscarTipoActorByCodigo(String codigo);

  /**
   * Obtiene un tipo evaluacion por código
   * 
   * @param codigo - Código del tipo observación
   * @return - el tipo de observación
   */
  TipoObservacion buscarTipoObservacionByCodigo(String codigo);

  /**
   * Obtiene un rol por código
   * 
   * @param codigo - Código del rol
   * @return - el rol
   */
  Rol SeleccionarRolByCodigo(ERol oci);

  boolean eliminarOpcionConsecuencia(Long id);
}
