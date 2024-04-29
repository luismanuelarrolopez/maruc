package com.unicauca.maruc.repositories;

import java.util.List;
import java.util.Optional;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.RiesgoObservacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RiesgoObservacionDAO extends JpaRepository<RiesgoObservacion, Long> {

    Optional<RiesgoObservacion> findByRiesgoId(Long id_Riesgo);
    Optional<RiesgoObservacion> findTopByRiesgoIdAndObservacionTipoActorIdOrderByFechaCreacionDesc(Long id_Riesgo, Long id_tipo_actor);
    List<RiesgoObservacion> findByRiesgoIdAndObservacionTipoActorIdOrderByFechaCreacionDesc(Long id_Riesgo, Long id_tipo_actor);
}
