package com.unicauca.maruc.controllers.riesgo;

import com.unicauca.maruc.dto.riesgos.InformacionBasicaRiesgoDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/riesgos/actualizar")
@CrossOrigin("*")
public class RiesgoUpdateController {

    @PutMapping("/informacion-basica")
    public boolean actualizarInformacionBasica(final InformacionBasicaRiesgoDTO informacionBasica) {
        // TODO: Llamar servicio para actualizar información básica.
        return false;
    }
}
