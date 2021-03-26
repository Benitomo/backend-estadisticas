
package com.backend.slim4.controller ;

import com.backend.slim4.model.ArticleFilter;
import com.backend.slim4.service.InterfacesSlim4Service;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
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
@RequestMapping({"/slim4"})
public class Controlador {
    
    @Autowired
    private ServletContext servletContext;
    
    @Autowired
    InterfacesSlim4Service service;
    
    
    
    // Traer article_filter de Informix e insertar en SqlServer
    @GetMapping(path = {"/article-filter"})
    public List<ArticleFilter> articleFilter()throws IOException{
        System.out.print("Llegue aquí");
        return service.articleFilter();
    } 
    
    // Traer article_filter de Informix e insertar en SqlServer
    @GetMapping(path = {"/test"})
    public List<ArticleFilter> test()throws IOException{
        System.out.print("Llegue aquí sql server");
        return service.test();
    } 
    
    
    
    // Test
    @GetMapping(path = {"/hello-world"})
    public String helloWorld(){
        return "Hola Mundo";
    } 
    
    
    // Método para leer el documento pdf, encontrar la palabra clave como parámetro y llamar método para obtener las páginas.
    @PostMapping(path = {"/leer-pdf"})
    public ResponseEntity leerPdf() throws IOException, InterruptedException {
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        map.put("encontrado", "testing");
        map.put("paginas", "testing");
        map.put("no_encontrado", "testing");
        return new ResponseEntity(map, HttpStatus.OK);
    }
    
    
}
