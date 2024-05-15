package com.unicauca.maruc.service.impl.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import org.springframework.util.ResourceUtils;

public class ReportReader {
    public static File getReport(final String filename, final String model) throws IOException {
        final File file = ResourceUtils.getFile("classpath:reports/" +model+"/"+ filename + ".jasper");
        return file;
    }

    public static File getImage(final String filename,  final String model) throws IOException {
        final File file = ResourceUtils.getFile("classpath:reports/" +model+"/img/"+ filename + ".jpg");
        return file;
    }

    public static Path getPath() throws FileNotFoundException{
        return ResourceUtils.getFile("classpath:reports/").toPath();
    }
}
