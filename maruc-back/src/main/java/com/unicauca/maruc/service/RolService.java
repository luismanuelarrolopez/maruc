package com.unicauca.maruc.service;

import com.unicauca.maruc.domain.model.Rol;
import com.unicauca.maruc.domain.model.enums.ERol;

public interface RolService extends BaseService<Rol> {

    Rol findByCodigo(ERol oci);
}
