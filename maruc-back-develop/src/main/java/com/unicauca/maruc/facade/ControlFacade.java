package com.unicauca.maruc.facade;

import java.util.List;
import com.unicauca.maruc.domain.model.Control;

public interface ControlFacade {

  List<Control> buscarPorIdRiesgo(Long idRiesgo);

  /**
   * Encuentra un control por su identificador.
   *
   * @param id
   * @return - el control encontrado
   */
  Control encontrarControl(Long id);

  /**
   * Registra un nuevo control en el sistema.
   *
   * @param control
   * @return - el control guardado
   */
  Control guardarControl(final Control control);

  /**
   * Encuentra todos los controles registrados.
   *
   * @return - Lista de controles
   */
  List<Control> listarControles();
}
