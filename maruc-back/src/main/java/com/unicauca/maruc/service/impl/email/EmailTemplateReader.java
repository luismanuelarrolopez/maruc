package com.unicauca.maruc.service.impl.email;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.util.ResourceUtils;

public class EmailTemplateReader {
    public static String htmlText(final String filename) throws IOException {
        final File file = ResourceUtils.getFile("classpath:emailtemplates/" + filename + ".html");
        return Files.readString(file.toPath());
    }
}
