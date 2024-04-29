package com.unicauca.maruc.dto.usuarios.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UsuarioRequest implements Serializable {

    @NotNull
    private Long id;
    @NotNull
    private String nombres;
    @NotNull
    private String apellidos;
    @NotNull
    private String email;
    @NotNull
    private Long idRol;
    @NotNull
    private Long idDependencia;
}
