package com.unicauca.maruc.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.unicauca.maruc.domain.model.VersionRiesgo;
import com.unicauca.maruc.util.ResponseUtils;
import com.unicauca.maruc.facade.VersionRiesgoFacade;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.FieldError;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/version_riesgo")
@Validated
@CrossOrigin(origins = "*")
public class VersionRiesgoController {
    
    @Autowired
    private VersionRiesgoFacade VersionRiesgoFacade;

    @Operation(summary = "Agregar un soporte a una evidencia", description = "Agrega un elemento a la tabla VersionRiesgo relacionando un soporte con una evidencia")
    @PostMapping("/agregar_version_riesgo")
    public ResponseEntity<?> guardarVersionRiesgo(@Valid 
        @NotNull @NotEmpty @RequestParam("nombre") String nombre,
        @RequestParam("file") MultipartFile file) {
            Map<String, Object> response = new HashMap<>();
            if(!file.isEmpty()) {
                String ruta_version =  UUID.randomUUID().toString() + "_" +  file.getOriginalFilename();
                
                Path rutafile = Paths.get("uploads").resolve(ruta_version).toAbsolutePath();
                
                try {
                    Files.copy(file.getInputStream(), rutafile);
                } catch (IOException e) {
                    response.put("mensaje", "Error al subir la imagen del cliente " + ruta_version);
                    response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                VersionRiesgo version_riesgo = new VersionRiesgo(nombre, ruta_version);
                return ResponseUtils.ok(VersionRiesgoFacade.AgregarVersionRiesgo(version_riesgo));
                
            }
            else
            {
                return ResponseUtils.bad_request(response);
            }
    }

    @Operation(summary = "Obtener una VersionRiesgo por su identificador", description = "Obtiene un objeto de tipo VersionRiesgo por el parámetro id_VersionRiesgo")
    @GetMapping("/seleccionar_version_riesgo/{id_VersionRiesgo}")
    public ResponseEntity<VersionRiesgo> seleccionarVersionRiesgo(@Valid @Positive @PathVariable Long id_VersionRiesgo) {
        return ResponseUtils.ok(VersionRiesgoFacade.SeleccionarVersionRiesgo(id_VersionRiesgo));
    }

    @Operation(summary = "Obtener una VersionRiesgo por su identificador", description = "Obtiene un objeto de tipo VersionRiesgo por el parámetro id_VersionRiesgo")
    @GetMapping("/listar")
    public ResponseEntity<List<VersionRiesgo>> listarVersionRiesgo() {
        return ResponseUtils.ok(VersionRiesgoFacade.listarVersionRiesgo());
    }

    @Operation(summary = "Eliminar un soporte de una evidencia", description = "Elimina un elemento de la tabla VersionRiesgo relacionando un soporte con una evidencia")
    @DeleteMapping("/eliminar_version_riesgo/{id_VersionRiesgo}")
    public ResponseEntity<Boolean> eliminarVersionRiesgo(@Valid @Positive @PathVariable Long id_VersionRiesgo) {
        //get soporte evidencia and delete file
        VersionRiesgo version_riesgo = VersionRiesgoFacade.SeleccionarVersionRiesgo(id_VersionRiesgo);
        eliminar_version_riesgo(version_riesgo.getRuta_version());
        return ResponseUtils.ok(VersionRiesgoFacade.EliminarVersionRiesgo(id_VersionRiesgo));
    }

    @Operation(summary = "Actualizar un soporte de una evidencia", description = "Actualiza un elemento de la tabla VersionRiesgo relacionando un soporte con una evidencia")
    @PutMapping("/actualizar_version_riesgo")
    public ResponseEntity<VersionRiesgo> actualizarVersionRiesgo(@RequestBody VersionRiesgo VersionRiesgo) {
        return ResponseUtils.ok(VersionRiesgoFacade.ActualizarVersionRiesgo(VersionRiesgo));
    }

    @Operation(summary = "Descargar un soporte de una evidencia", description = "Descarga un elemento de la tabla VersionRiesgo relacionando un soporte con una evidencia")
    @GetMapping("/descargar_version_riesgo/{id_VersionRiesgo}")
    public ResponseEntity<?> descargarVersionRiesgo(@Valid @Positive @PathVariable Long id_VersionRiesgo) {
        Map<String, Object> response = new HashMap<>();
		boolean banderaRutaCorrecta=true;
        VersionRiesgo version_riesgo = VersionRiesgoFacade.SeleccionarVersionRiesgo(id_VersionRiesgo);
		Path rutaArchivo = Paths.get("uploads").resolve(version_riesgo.getRuta_version()).toAbsolutePath();
		
		Resource recurso = null;		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			banderaRutaCorrecta=false;
		}
		
		if(banderaRutaCorrecta==false || !recurso.exists() || !recurso.isReadable()) {
			response.put("mensaje", "Error no se pudo cargar la imagen: " + version_riesgo.getRuta_version());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError ) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        return errors;
    }

    private void eliminar_version_riesgo(String ruta_version) {
        if(ruta_version != null && !ruta_version.isEmpty()) {
            Path rutafile = Paths.get("uploads").resolve(ruta_version).toAbsolutePath();
            try {
                Files.delete(rutafile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
