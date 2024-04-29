package com.unicauca.maruc.controllers.catalogo;

import com.unicauca.maruc.domain.model.DifusionControl;
import com.unicauca.maruc.facade.RiesgoManagerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/difusion-control")
@CrossOrigin("*")
public class DifusionControlController implements Serializable {

    @Autowired
    private RiesgoManagerFacade riesgoManagerFacade;

    @GetMapping
    public List<DifusionControl> buscar() {
        return riesgoManagerFacade.buscarDifusionesControl();
    }
}
