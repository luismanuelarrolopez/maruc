package com.unicauca.maruc.controllers.control;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.facade.ControlFacade;
import com.unicauca.maruc.util.ResponseUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/control")
@Validated
public class ControlController {
    
    @Autowired
    private ControlFacade controlFacade;

    @Operation(summary = "Agregar un control", description = "Agrega un elemento a la tabla control")
    @PostMapping("/agregar_control")
    public ResponseEntity<Control> guardarControl(@Valid @RequestBody Control control) {
        return ResponseUtils.ok(controlFacade.guardarControl(control));
    }

    @Operation(summary = "Obtener un control por su identificador", description = "Obtiene un objeto de tipo control por el parámetro id_control")
    @GetMapping("/seleccionar_control/{id_control}")
    public ResponseEntity<Control> seleccionarControl(@Valid @Positive(message = "{EL id_control debe ser positivo}") @PathVariable Long id_control) {
        return ResponseUtils.ok(controlFacade.encontrarControl(id_control));
    }
}
