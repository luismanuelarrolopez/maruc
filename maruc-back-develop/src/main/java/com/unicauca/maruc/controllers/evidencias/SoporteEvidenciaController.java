package com.unicauca.maruc.controllers.evidencias;

import java.util.HashMap;
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

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.SoporteEvidencia;
import com.unicauca.maruc.util.ResponseUtils;
import com.unicauca.maruc.facade.SoporteEvidenciaFacade;

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
@RequestMapping("/soporte_evidencia")
@Validated
@CrossOrigin(origins = "*")
public class SoporteEvidenciaController {
    
    @Autowired
    private SoporteEvidenciaFacade SoporteEvidenciaFacade;

    @Operation(summary = "Agregar un soporte a una evidencia", description = "Agrega un elemento a la tabla SoporteEvidencia relacionando un soporte con una evidencia")
    @PostMapping("/agregar_soporte_evidencia")
    public ResponseEntity<?> guardarSoporteEvidencia(@Valid 
        @NotNull @NotEmpty @RequestParam("nombre") String nombre,
        @NotNull @Positive @RequestParam("id_evidencia") Long id_evidencia,
        @RequestParam("file") MultipartFile file) {
            Map<String, Object> response = new HashMap<>();
            if(!file.isEmpty()) {
                String ruta_soporte =  UUID.randomUUID().toString() + "_" +  file.getOriginalFilename();
                
                Path rutafile = Paths.get("uploads").resolve(ruta_soporte).toAbsolutePath();
                
                try {
                    Files.copy(file.getInputStream(), rutafile);
                } catch (IOException e) {
                    response.put("mensaje", "Error al subir la imagen del cliente " + ruta_soporte);
                    response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Evidencia evidencia = new Evidencia();
                evidencia.setId(id_evidencia);
                SoporteEvidencia soporte_evidencia = new SoporteEvidencia(nombre, ruta_soporte,evidencia);
                return ResponseUtils.ok(SoporteEvidenciaFacade.AgregarSoporteEvidencia(soporte_evidencia));
                
            }
            else
            {
                return ResponseUtils.bad_request(response);
            }
    }

    @Operation(summary = "Obtener una SoporteEvidencia por su identificador", description = "Obtiene un objeto de tipo SoporteEvidencia por el parámetro id_SoporteEvidencia")
    @GetMapping("/seleccionar_soporte_evidencia/{id_SoporteEvidencia}")
    public ResponseEntity<SoporteEvidencia> seleccionarSoporteEvidencia(@Valid @Positive @PathVariable Long id_SoporteEvidencia) {
        return ResponseUtils.ok(SoporteEvidenciaFacade.SeleccionarSoporteEvidencia(id_SoporteEvidencia));
    }

    @Operation(summary = "Obtener soportes de una evidencia", description = "Obtiene una lista de objetos de tipo SoporteEvidencia que pertence a una evidencia")
    @GetMapping("/seleccionar_soportes_evidencia/{id_evidencia}")
    public ResponseEntity<Iterable<SoporteEvidencia>> seleccionarSoportesEvidencia(@Valid @Positive @PathVariable Long id_evidencia) {
        return ResponseUtils.ok(SoporteEvidenciaFacade.SeleccionarSoportesEvidenciaByIdEvidencia(id_evidencia));
    }

    @Operation(summary = "Eliminar un soporte de una evidencia", description = "Elimina un elemento de la tabla SoporteEvidencia relacionando un soporte con una evidencia")
    @DeleteMapping("/eliminar_soporte_evidencia/{id_SoporteEvidencia}")
    public ResponseEntity<Boolean> eliminarSoporteEvidencia(@Valid @Positive @PathVariable Long id_SoporteEvidencia) {
        //get soporte evidencia and delete file
        SoporteEvidencia soporte_evidencia = SoporteEvidenciaFacade.SeleccionarSoporteEvidencia(id_SoporteEvidencia);
        eliminar_soporte_evidencia(soporte_evidencia.getRuta_soporte());
        return ResponseUtils.ok(SoporteEvidenciaFacade.EliminarSoporteEvidencia(id_SoporteEvidencia));
    }

    @Operation(summary = "Actualizar un soporte de una evidencia", description = "Actualiza un elemento de la tabla SoporteEvidencia relacionando un soporte con una evidencia")
    @PutMapping("/actualizar_soporte_evidencia")
    public ResponseEntity<SoporteEvidencia> actualizarSoporteEvidencia(@RequestBody SoporteEvidencia SoporteEvidencia) {
        return ResponseUtils.ok(SoporteEvidenciaFacade.ActualizarSoporteEvidencia(SoporteEvidencia));
    }

    @Operation(summary = "Descargar un soporte de una evidencia", description = "Descarga un elemento de la tabla SoporteEvidencia relacionando un soporte con una evidencia")
    @GetMapping("/descargar_soporte_evidencia/{id_SoporteEvidencia}")
    public ResponseEntity<?> descargarSoporteEvidencia(@Valid @Positive @PathVariable Long id_SoporteEvidencia) {
        Map<String, Object> response = new HashMap<>();
		boolean banderaRutaCorrecta=true;
        SoporteEvidencia soporte_evidencia = SoporteEvidenciaFacade.SeleccionarSoporteEvidencia(id_SoporteEvidencia);
		Path rutaArchivo = Paths.get("uploads").resolve(soporte_evidencia.getRuta_soporte()).toAbsolutePath();
		
		Resource recurso = null;		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			banderaRutaCorrecta=false;
		}
		
		if(banderaRutaCorrecta==false || !recurso.exists() || !recurso.isReadable()) {
			response.put("mensaje", "Error no se pudo cargar la imagen: " + soporte_evidencia.getRuta_soporte());
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

    private void eliminar_soporte_evidencia(String ruta_soporte) {
        if(ruta_soporte != null && !ruta_soporte.isEmpty()) {
            Path rutafile = Paths.get("uploads").resolve(ruta_soporte).toAbsolutePath();
            try {
                Files.delete(rutafile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
