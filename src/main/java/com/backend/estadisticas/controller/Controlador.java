
package com.backend.estadisticas.controller ;

import com.backend.estadisticas.service.EstadisticasService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping({"/estadisticas"})
public class Controlador {
    
    
    @Autowired
    EstadisticasService service;
    
    // Traer article_filter de Informix e insertar en SqlServer
    @GetMapping(path = {"/estadistica"})
    public ResponseEntity Estadisticas()throws IOException{
        return service.estadisticasSelect();
    } 
    
    // Test
    @GetMapping(path = {"/hello-world"})
    public String helloWorld(){
        return "Hola Oscar";
    } 
    
}
