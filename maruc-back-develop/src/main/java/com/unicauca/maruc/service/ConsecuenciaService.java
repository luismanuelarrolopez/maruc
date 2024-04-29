package com.unicauca.maruc.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.unicauca.maruc.domain.model.Consecuencia;

public interface ConsecuenciaService {

  /**
   * Inserta una nueva consecuencia.
   * 
   * @param consecuencia
   * @return
   */
  Consecuencia registrarConsecuencia(Consecuencia consecuencia);

  /**
   * Actualiza una consecuencia
   * 
   * @param consecuencia
   * @return
   */
  Consecuencia actualizarConsecuencia(Consecuencia consecuencia);

  /**
   * Busca una consecuencia por su identificador
   * 
   * @param id
   * @return
   */
  Optional<Consecuencia> buscarConsecuencia(Long id);

  Page<Consecuencia> listarConsecuencias(int page, int size);

  /**
   * Elimina la consecuencia.
   * 
   * @param consecuencia
   * @return
   */
  Long eliminar(Consecuencia consecuencia);

  /**
   * Guarda todas las consecuencias asociadas a un riesgo.
   */
  //void guardarConsecuencia(List<Consecuencia> consecuencias);
}
