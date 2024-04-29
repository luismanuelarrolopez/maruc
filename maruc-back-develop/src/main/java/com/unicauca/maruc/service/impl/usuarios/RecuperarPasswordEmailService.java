package com.unicauca.maruc.service.impl.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class RecuperarPasswordEmailService extends UsuarioEmailService {

    private static final String TEMPLATE_FILENAME = "recuperar";
    private static final String SUBJECT = "Recuperar contraseña";

    @Autowired
    private Environment env;

    @Override
    protected String getURI() {
        return env.getProperty("RECUPERAR_PASSWORD_URI");
    }

    @Override
    protected String getTemplateFileName() {
        return TEMPLATE_FILENAME;
    }

    @Override
    protected String getSubject() {
        return SUBJECT;
    }

}
