package com.backend.estadisticas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BackendestadisticasApplication extends SpringBootServletInitializer{
        
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackendestadisticasApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(BackendestadisticasApplication.class, args);
	}

}
