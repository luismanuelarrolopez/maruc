package com.unicauca.maruc.service.impl;

import com.unicauca.maruc.domain.model.TipoControl;
import com.unicauca.maruc.repositories.TipoControlDAO;
import com.unicauca.maruc.service.TipoControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoControlServiceImpl implements TipoControlService {

    @Autowired
    private TipoControlDAO tipoControlDAO;

    @Override
    public List<TipoControl> findAll() {
        return tipoControlDAO.findAll();
    }
}
