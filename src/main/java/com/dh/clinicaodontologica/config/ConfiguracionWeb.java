package com.dh.clinicaodontologica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/odontologos").setViewName("redirect:/odontologos/");
        registry.addViewController("/odontologos/").setViewName("forward:/odontologos/index.html");
        registry.addViewController("/pacientes").setViewName("redirect:/pacientes/");
        registry.addViewController("/pacientes/").setViewName("forward:/pacientes/index.html");
        registry.addViewController("/turnos").setViewName("redirect:/turnos/");
        registry.addViewController("/turnos/").setViewName("forward:/turnos/index.html");
    }

}