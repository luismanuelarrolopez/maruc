package com.unicauca.maruc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.unicauca.maruc.domain.model.Rol;
import com.unicauca.maruc.domain.model.enums.ERol;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.repositories.RolDAO;
import com.unicauca.maruc.service.RolService;

@Service
public class RolServiceImpl extends DefaultBaseService<Rol> implements RolService {

    @Autowired
    private RolDAO rolDAO;
    
    @Override
    public Rol findByCodigo(ERol rol) {
        //get the optional value of the rol
        Optional<Rol> rolObj = rolDAO.findByCodigo(rol.name());
        //if the rol is not found, throw an exception
        if(!rolObj.isPresent())
        throw new EntidadNoExisteException(
            String.format("No se proveyó un identificador en el objeto.", rol.toString()));
        //return the rol
        return rolObj.get();
    }

}
