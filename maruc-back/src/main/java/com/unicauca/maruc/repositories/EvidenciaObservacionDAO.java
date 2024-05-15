package com.unicauca.maruc.repositories;

import java.util.List;
import java.util.Optional;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.EvidenciaObservacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EvidenciaObservacionDAO extends JpaRepository<EvidenciaObservacion, Long> {

    Optional<EvidenciaObservacion> findByEvidenciaId(Long id_evidencia);
    Optional<EvidenciaObservacion> findTopByEvidenciaIdAndObservacionTipoActorIdOrderByFechaCreacionDesc(Long id_evidencia, Long id_tipo_actor);
    List<EvidenciaObservacion> findByEvidenciaIdAndObservacionTipoActorIdOrderByFechaCreacionDesc(Long id_evidencia, Long id_tipo_actor);
}
