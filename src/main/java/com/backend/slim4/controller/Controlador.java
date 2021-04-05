
package com.backend.slim4.controller ;

import com.backend.slim4.service.ArticleFilterService;
import com.backend.slim4.service.ImportLogisticsService;
import com.backend.slim4.service.PurchaseOrderService;
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
@RequestMapping({"/slim4"})
public class Controlador {
    
    
    @Autowired
    ArticleFilterService service;
    
    @Autowired
    ImportLogisticsService logistics_service;
    
    @Autowired
    PurchaseOrderService purchase_service;
    
    
    // Traer article_filter de Informix e insertar en SqlServer
    @GetMapping(path = {"/article-filter"})
    public ResponseEntity articleFilter()throws IOException{
        return service.articleFilterSelect();
    } 
    
    // Traer iport_logistics de Informix e insertar en SqlServer
    @GetMapping(path = {"/import-logistics"})
    public ResponseEntity importLogistics()throws IOException{
        return logistics_service.importLogisticsSelect();
    } 
    
    // Traer iport_logistics de Informix e insertar en SqlServer
    @GetMapping(path = {"/purchase-order"})
    public ResponseEntity importPurchaseOrder()throws IOException{
        return purchase_service.purchaseOrderSelect();
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
