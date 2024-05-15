package com.unicauca.maruc.service;

import com.unicauca.maruc.domain.model.TipoObservacion;

public interface TipoObservacionService {
    
    TipoObservacion findByCodigo(String codigo);
}
