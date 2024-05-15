package com.unicauca.maruc.service;

import java.util.List;
import com.unicauca.maruc.domain.model.Control;

/**
 * Permite gestionar la información relacionada a los controles.
 */
public interface ControlService {

  List<Control> buscarPorIdRiesgo(Long idRiesgo);

  /**
   * Registra un nuevo control en el sistema.
   *
   * @param control
   * @return - el control guardado
   */
  Control guardarControl(Control control);

  /**
   * Encuentra todos los controles registrados.
   *
   * @return - Lista de controles
   */
  List<Control> listarControles();

  /**
   * Encuentra un control por su identificador.
   *
   * @param id
   * @return - el control encontrado
   */
  Control seleccionarControl(Long id);
}
