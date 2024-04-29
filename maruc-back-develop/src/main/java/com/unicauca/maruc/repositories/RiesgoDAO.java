package com.unicauca.maruc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.unicauca.maruc.domain.model.Riesgo;

@Repository
public interface RiesgoDAO extends JpaRepository<Riesgo, Long> {

  @Override
  @EntityGraph(value = Riesgo.GRAFO_TIPO_RIESGO)
  Page<Riesgo> findAll(Pageable pageable);

  @EntityGraph(value = Riesgo.GRAFO_TIPO_RIESGO)
  Page<Riesgo> findByUsuarioId(Pageable pageable, Long id);

  @EntityGraph(value = Riesgo.GRAFO_TIPO_RIESGO)
  Page<Riesgo> findByNombreContainingIgnoreCase(Pageable page, String nombre);

  @EntityGraph(value = Riesgo.GRAFO_TIPO_RIESGO)
  Page<Riesgo> findByTipoProcesoId(Pageable page, long key);

  @EntityGraph(value = Riesgo.GRAFO_TIPO_RIESGO)
  Page<Riesgo> findByTipoRiesgoId(Pageable page, long key);

  List<Riesgo> findByUsuarioId(Long id_lider_proceso);
  //@EntityGraph(value = Riesgo.GRAFO_TIPO_RIESGO)
  //Page<Riesgo> findByRiesgoInherenteBetween(Pageable page, int start, int end);

  // @Query("SELECT r FROM riesgos r WHERE r.estatusInformacionRiesgo.valor >= :valor")
  List<Riesgo> findByestatusInformacionRiesgo_valorGreaterThanEqual(int valor);

  @EntityGraph(value = Riesgo.GRAFO_TIPO_RIESGO)
  Page<Riesgo> findByestatusInformacionRiesgo_valorGreaterThanEqual(Pageable pageable, int valor);

  @EntityGraph(value = Riesgo.GRAFO_TIPO_RIESGO)
  Page<Riesgo> findByestatusInformacionRiesgo_valorGreaterThanEqualAndUsuarioId(Pageable pageable, int valor, Long id);

  Page<Riesgo> findByNombreContainingIgnoreCaseAndTipoProcesoIdAndTipoRiesgoId(Pageable page, String key, long proceso,
        long tipo_riesgo);
  @Query("SELECT r from Riesgo r WHERE r.id IN ("+ 
    "SELECT r1.id from Riesgo r1 "+
     "INNER JOIN TipoRiesgo tr on r1.tipoRiesgo.id = tr.id "+
     "INNER JOIN TipoProceso tp on r1.tipoProceso.id = tp.id "+
     "INNER JOIN EstatusInformacionRiesgo eir on r1.estatusInformacionRiesgo.id = eir.id "+
     "WHERE r1.nombre LIKE CASE WHEN :nombre_riesgo != '' THEN CONCAT('%',:nombre_riesgo,'%') ELSE '%%' END "+
     "AND tr.codigo LIKE CASE WHEN :tipo_riesgo != '0' THEN :tipo_riesgo ELSE '%%' END "+
     "AND tp.codigo LIKE CASE WHEN :tipo_proceso != '0' THEN :tipo_proceso ELSE '%%' END "+
     "AND eir.valor >= CASE WHEN :estatus_riesgo = TRUE THEN 8 ELSE 0 END)")
  Page<Riesgo> filter(Pageable page, String nombre_riesgo, String tipo_riesgo, String tipo_proceso, Boolean estatus_riesgo);

  @Query("SELECT COUNT(*) FROM ControlResidual c "+
  "inner join Evidencia e on c.id = e.control.id "+
  "inner join RiesgoResidual re on c.riesgoResidual.id = re.id "+
  "inner join Riesgo r on re.riesgo.id = r.id "+
  "inner join TipoProceso tp on tp.id = r.tipoProceso.id "+
  "where tp.id = :tipo_proceso and e.cumplimiento_oci = true and e.cumplimiento_opdi = true")
  Integer countControlesInCumplimientoByProceso(long tipo_proceso);

  @Query("SELECT COUNT(*) FROM ControlResidual c "+
  "inner join Evidencia e on c.id = e.control.id "+
  "inner join RiesgoResidual re on c.riesgoResidual.id = re.id "+
  "inner join Riesgo r on re.riesgo.id = r.id "+
  "inner join Usuario u on r.usuario.id = u.id "+
  "inner join Dependencia de on de.id = u.dependencia.id "+
  "where de.id = :dependencia and e.cumplimiento_oci = true and e.cumplimiento_opdi = true")
  Integer countControlesInCumplimientoByDependencia(long dependencia);
}
