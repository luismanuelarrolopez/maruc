package com.unicauca.maruc.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unicauca.maruc.domain.model.OpcionConsecuenciaRiesgo;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaRiesgoDTO;

@Repository
public interface OpcionConsecuenciaRiesgoDAO extends JpaRepository<OpcionConsecuenciaRiesgo, Long> {
  @Query("SELECT new "
      + "com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaRiesgoDTO(ocr.id, ocr.riesgo.id,"
      + "ocr.opcionConsecuencia.id, ocr.opcionConsecuencia.consecuencia.tipoAfectacion.nombre, "
      + "ocr.opcionConsecuencia.consecuencia.descripcion, ocr.opcionConsecuencia.puntaje) "
      + "FROM OpcionConsecuenciaRiesgo ocr WHERE ocr.riesgo.id = :idRiesgo")
  List<OpcionConsecuenciaRiesgoDTO> buscarConsecuenciasRiesgo(Long idRiesgo);
}
