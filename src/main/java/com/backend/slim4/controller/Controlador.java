
package com.backend.slim4.controller ;

import com.backend.slim4.service.ArticleCodeMasterService;
import com.backend.slim4.service.ArticleFilterService;
import com.backend.slim4.service.BillOfMaterialService;
import com.backend.slim4.service.ConfirmedDemandService;
import com.backend.slim4.service.HistoricalPurchaseOrdersService;
import com.backend.slim4.service.ImportLogisticsService;
import com.backend.slim4.service.PurchaseOrderService;
import com.backend.slim4.service.StockDetailsService;
import com.backend.slim4.service.SuppliersService;
import com.backend.slim4.service.TransactionsService;
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
    
    @Autowired
    ConfirmedDemandService confirmed_service;
    
    @Autowired
    TransactionsService transactions_service;
    
    @Autowired
    StockDetailsService stock_service;
    
    @Autowired
    ArticleCodeMasterService master_service;
    
    @Autowired
    BillOfMaterialService bill_service;
    
    @Autowired
    SuppliersService suppliers_service;
    
    @Autowired
    HistoricalPurchaseOrdersService historical_service;
    
    // Traer article_filter de Informix e insertar en SqlServer
    @GetMapping(path = {"/article-filter"})
    public ResponseEntity articleFilter()throws IOException{
        return service.articleFilterSelect();
    } 
    
    // Traer import_logistics de Informix e insertar en SqlServer
    @GetMapping(path = {"/logistics"})
    public ResponseEntity importLogistics()throws IOException{
        return logistics_service.importLogisticsSelect();
    } 
    
    // Traer purchase_order de Informix e insertar en SqlServer
    @GetMapping(path = {"/purchase-order"})
    public ResponseEntity importPurchaseOrder()throws IOException{
        return purchase_service.purchaseOrderSelect();
    } 
    
    // Traer confirmed_demand de Informix e insertar en SqlServer
    @GetMapping(path = {"/confirmed-demand"})
    public ResponseEntity importConfirmedDemand()throws IOException{
        return confirmed_service.confirmedDemandSelect();
    } 
    
    // Traer transactions de Informix e insertar en SqlServer
    @GetMapping(path = {"/transactions"})
    public ResponseEntity importTransactions()throws IOException{
        return transactions_service.transactionsSelect();
    } 
    
    // Traer stockdetails de Informix e insertar en SqlServer
    @GetMapping(path = {"/stock-details"})
    public ResponseEntity importStockDetails()throws IOException{
        return stock_service.stockDetailsSelect();
    } 
    
    // Traer articlecodemaster de Informix e insertar en SqlServer
    @GetMapping(path = {"/article-code-master"})
    public ResponseEntity importArticleCodeMaster()throws IOException{
        return master_service.articleCodeMasterSelect();
    } 
    
    // Traer billofmaterial de Informix e insertar en SqlServer
    @GetMapping(path = {"/bill-of-material"})
    public ResponseEntity importBillOfMaterial()throws IOException{
        return bill_service.billOfMaterialSelect();
    } 
    
    // Traer suppliers de Informix e insertar en SqlServer
    @GetMapping(path = {"/suppliers"})
    public ResponseEntity importSuppliers()throws IOException{
        return suppliers_service.suppliersSelect();
    } 
    
    // Traer historicalpo de Informix e insertar en SqlServer
    @GetMapping(path = {"/historical-po"})
    public ResponseEntity importHistoricalPO()throws IOException{
        return historical_service.historicalPurchaseOrdersSelect();
    } 
    
    // Test
    @GetMapping(path = {"/hello-world"})
    public String helloWorld(){
        return "Hola Mundo";
    } 
    
    
}
