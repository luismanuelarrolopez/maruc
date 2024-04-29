package com.unicauca.maruc.controllers.riesgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
@Slf4j
public class TestController {

    @Autowired
    private Environment env;

    @GetMapping("/{key}")
    public String getEnv(@PathVariable(name = "key") final String key) {
        log.info("Llave: {}", key);
        return env.getProperty(key);
    }
}