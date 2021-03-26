package com.backend.slim4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BackendSlim4Application extends SpringBootServletInitializer{
        
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackendSlim4Application.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(BackendSlim4Application.class, args);
	}

}
