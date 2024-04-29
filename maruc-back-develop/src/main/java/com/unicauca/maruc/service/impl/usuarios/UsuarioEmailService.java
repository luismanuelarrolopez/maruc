package com.unicauca.maruc.service.impl.usuarios;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.service.EmailService;
import com.unicauca.maruc.service.impl.email.EmailTemplateReader;

public abstract class UsuarioEmailService {
    @Autowired
    private EmailService emailService;

    public void sendEmail(final Usuario usuario) throws IOException {
        emailService.sendEmail(usuario.getEmail(), getSubject(), readHtmlTemplate(usuario.getUuid()));
    }

    private String readHtmlTemplate(String uuid) throws IOException {
        final String emailTemplate = EmailTemplateReader.htmlText(getTemplateFileName());
        return emailTemplate.replace("{{link}}", getURICOMPLETA(uuid));
    }

    private String getURICOMPLETA(String uuid) {
        System.out.println(getURI());
        return String.format("%s/%s", getURI(), uuid);
    }

    protected abstract String getURI();

    protected abstract String getTemplateFileName();

    protected abstract String getSubject();
}
