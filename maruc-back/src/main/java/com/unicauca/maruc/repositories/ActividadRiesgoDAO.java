package com.unicauca.maruc.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.RiesgoActividad;
import com.unicauca.maruc.dto.riesgos.RiesgoActividadDTO;

@Repository
public interface ActividadRiesgoDAO extends JpaRepository<RiesgoActividad, Long> {

  @Query("SELECT new com.unicauca.maruc.dto.riesgos.RiesgoActividadDTO(ra.id, ra.frecuencia, ra.actividad.nombre,"
      + "ra.actividad.frecuenciaInvertida) FROM RiesgoActividad ra WHERE ra.riesgo.id = :idRiesgo")
  List<RiesgoActividadDTO> buscarPorRiesgo(Long idRiesgo);

  @Query("SELECT act FROM Actividad act WHERE act.id NOT IN("
      + "SELECT ra.actividad.id FROM RiesgoActividad ra WHERE ra.riesgo.id = :idRiesgo)")
  List<Actividad> buscarNoEnRiesgo(Long idRiesgo);
}
