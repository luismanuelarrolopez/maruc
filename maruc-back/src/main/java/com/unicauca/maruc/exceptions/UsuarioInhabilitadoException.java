package com.unicauca.maruc.exceptions;

public class UsuarioInhabilitadoException extends RuntimeException {
    public UsuarioInhabilitadoException(String username) {
        super(String.format("El usuario con email %s no se encuentra habilitado", username));
    }
}
