package com.unicauca.maruc.service;

import org.springframework.data.domain.Page;
import com.unicauca.maruc.domain.model.Actividad;

import java.util.List;

/**
 * Permite gestionar la información relacionada a las actividad.
 * 
 * @author Sebastián Carabali
 */
public interface ActividadService extends BaseService<Actividad> {

  Page<Actividad> fetchActividades(int page, int size);

  List<Actividad> fetchActividades();
}
