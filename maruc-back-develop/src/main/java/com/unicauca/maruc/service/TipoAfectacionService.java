package com.unicauca.maruc.service;

import java.util.List;
import com.unicauca.maruc.domain.model.TipoAfectacion;

public interface TipoAfectacionService extends BaseService<TipoAfectacion> {

  List<TipoAfectacion> buscarConConsecuencias();
}
