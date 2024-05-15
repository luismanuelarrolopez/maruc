package com.unicauca.maruc.service;

import com.unicauca.maruc.domain.model.Usuario;

public interface LoginService {

    /**
     * Realiza el login de un nuevo usuario a la aplicación.
     *
     * @param nombreUsuario
     * @param password
     * @return
     */
    Usuario realizarLogin(String nombreUsuario, String password);
}
