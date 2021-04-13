/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ImportLogistics;
import com.backend.slim4.service.ImportLogisticsService;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class ImportLogisticsServiceImp implements ImportLogisticsService {
    @Override
    public ResponseEntity importLogisticsSelect() {
    ArrayList<ImportLogistics> logistics = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT first 10000 * from logistics";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ImportLogistics i = new ImportLogistics();
                i.setControlId(rs.getInt("controlid"));
                i.setWarehousecode(rs.getString("warehousecode"));
                i.setCode(rs.getString("articlecode"));
                i.setSupplierNumber(rs.getString("suppliernumber"));
                i.setSupplierName(rs.getString("suppliername"));
                i.setLeadTime(rs.getBigDecimal("leadtime"));
                i.setReviewTime(rs.getBigDecimal("reviewtime"));
                i.setSupplierReliability(rs.getBigDecimal("supplierreliability"));
                i.setSupplierReliabilityType(rs.getInt("supplierreliabilitytype"));
                i.setStockedItem(rs.getString("stockeditem"));
                i.setMOQ(rs.getInt("moq"));
                i.setIOQ(rs.getInt("ioq"));
                i.setEOQ(rs.getInt("eoq"));
                i.setLogisticUnit1(rs.getInt("logisticunit1"));
                i.setLogisticUnit2(rs.getInt("logisticunit2"));
                i.setLogisticUnit3(rs.getInt("logisticunit3"));
                i.setLogisticUnit4(rs.getInt("logisticunit4"));
                i.setLogisticUnit5(rs.getInt("logisticunit5"));
                i.setLogisticUnit6(rs.getInt("logisticunit6"));
                i.setInsuranceInventory(rs.getInt("insuranceinventory"));
                i.setInsuranceInventoryType(rs.getInt("insuranceinventorytype"));
                i.setTargetServiceLevel(rs.getBigDecimal("targetservicelevel"));
                i.setPlcArticleCode(rs.getString("plcarticlecode"));
                i.setPlcDate(rs.getDate("plcdate"));
                i.setPlcPerc(rs.getBigDecimal("plcperc"));
                i.setAbcClass(rs.getString("abcclass"));
                i.setBuyingPrice(rs.getBigDecimal("buyingprice"));
                i.setMSQ(rs.getInt("msq"));
                i.setISQ(rs.getInt("isq"));
                logistics.add(i);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(logistics.size()>0){
            try {
                return importLogisticsInsert(logistics);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("Datos no encontrados", "la tabla logistics está vacía");
            return new ResponseEntity(map, HttpStatus.OK);
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put("Error", "No se pudo traer información de la base de datos Informix");
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }
    
    public ResponseEntity importLogisticsInsert(ArrayList<ImportLogistics> a) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,a);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface Logistics correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_ImportLogistics previo a realizar la inserción.";
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
    
    public int emptyTable(Statement stmt){
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_Logistics]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
    public int insertDataTable(Statement stmt, ArrayList<ImportLogistics> il) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_Logistics]"
                + "(controlId,warehouse,code,supplierNumber,supplierName,leadTime,reviewTime,supplierReliability,supplierReliabilityType,stockedItem,MOQ,IOQ,EOQ,logisticUnit1,logisticUnit2,logisticUnit3,logisticUnit4,logisticUnit5,logisticUnit6,insuranceInventory,insuranceInventoryType,targetServiceLevel,plcArticleCode,plcDate,plcPerc,abcClass,buyingPrice,MSQ,ISQ) "
                + "SELECT * FROM  (VALUES";
            for (int i = 0; i < il.size(); i++) {
                sql =  (i==il.size()-1)? 
                        sql + "(" 
                        + il.get(i).getControlId() + ", "
                        + "'"+ il.get(i).getWarehousecode() +"', "
                        + "'" + il.get(i).getCode() + "', "
                        + "'" + il.get(i).getSupplierNumber()+ "', "
                        + "'" + il.get(i).getSupplierName()+ "', "
                        + "'" + il.get(i).getLeadTime()+ "', "
                        + "'" + il.get(i).getReviewTime()+ "', "
                        + "'" + il.get(i).getSupplierReliability()+ "', "
                        + "'" + il.get(i).getSupplierReliabilityType()+ "', "
                        + "'" + il.get(i).getStockedItem()+ "', "
                        + "'" + il.get(i).getMOQ()+ "', "
                        + "'" + il.get(i).getIOQ()+ "', "
                        + "'" + il.get(i).getEOQ()+ "', "
                        + "'" + il.get(i).getLogisticUnit1()+ "', "
                        + "'" + il.get(i).getLogisticUnit2()+ "', "
                        + "'" + il.get(i).getLogisticUnit3()+ "', "
                        + "'" + il.get(i).getLogisticUnit4()+ "', "
                        + "'" + il.get(i).getLogisticUnit5()+ "', "
                        + "'" + il.get(i).getLogisticUnit6()+ "', "
                        + "'" + il.get(i).getInsuranceInventory()+ "', "
                        + "'" + il.get(i).getInsuranceInventoryType()+ "', "
                        + "'" + il.get(i).getTargetServiceLevel()+ "', "
                        + "'" + il.get(i).getPlcArticleCode()+ "', "
                        + "'" + il.get(i).getPlcDate()+ "', "
                        + "'" + il.get(i).getPlcPerc()+ "', "
                        + "'" + il.get(i).getAbcClass()+ "', "
                        + "'" + il.get(i).getBuyingPrice()+ "', "
                        + "'" + il.get(i).getMSQ()+ "', "
                        + "'" + il.get(i).getISQ()+ "'"
                        + ")) "
                        + "as temporal"
                        + "(controlId,warehouse,code,supplierNumber,supplierName,leadTime,reviewTime,supplierReliability,supplierReliabilityType,stockedItem,MOQ,IOQ,EOQ,logisticUnit1,logisticUnit2,logisticUnit3,logisticUnit4,logisticUnit5,logisticUnit6,insuranceInventory,insuranceInventoryType,targetServiceLevel,plcArticleCode,plcDate,plcPerc,abcClass,buyingPrice,MSQ,ISQ)"
                            :
                        sql + "(" 
                        + il.get(i).getControlId() + ", "
                        + "'"+ il.get(i).getWarehousecode() +"', "
                        + "'" + il.get(i).getCode() + "', "
                        + "'" + il.get(i).getSupplierNumber()+ "', "
                        + "'" + il.get(i).getSupplierName()+ "', "
                        + "'" + il.get(i).getLeadTime()+ "', "
                        + "'" + il.get(i).getReviewTime()+ "', "
                        + "'" + il.get(i).getSupplierReliability()+ "', "
                        + "'" + il.get(i).getSupplierReliabilityType()+ "', "
                        + "'" + il.get(i).getStockedItem()+ "', "
                        + "'" + il.get(i).getMOQ()+ "', "
                        + "'" + il.get(i).getIOQ()+ "', "
                        + "'" + il.get(i).getEOQ()+ "', "
                        + "'" + il.get(i).getLogisticUnit1()+ "', "
                        + "'" + il.get(i).getLogisticUnit2()+ "', "
                        + "'" + il.get(i).getLogisticUnit3()+ "', "
                        + "'" + il.get(i).getLogisticUnit4()+ "', "
                        + "'" + il.get(i).getLogisticUnit5()+ "', "
                        + "'" + il.get(i).getLogisticUnit6()+ "', "
                        + "'" + il.get(i).getInsuranceInventory()+ "', "
                        + "'" + il.get(i).getInsuranceInventoryType()+ "', "
                        + "'" + il.get(i).getTargetServiceLevel()+ "', "
                        + "'" + il.get(i).getPlcArticleCode()+ "', "
                        + "'" + il.get(i).getPlcDate()+ "', "
                        + "'" + il.get(i).getPlcPerc()+ "', "
                        + "'" + il.get(i).getAbcClass()+ "', "
                        + "'" + il.get(i).getBuyingPrice()+ "', "
                        + "'" + il.get(i).getMSQ()+ "', "
                        + "'" + il.get(i).getISQ()+ "'),";}
            
            System.out.print("\n" + " Query de inserción Logistics: " + "\n" + sql + "\n");
        return stmt.executeUpdate(sql);
    } 
    
    public ArrayList<ImportLogistics> getArticleFilterInfo(Statement stmt) throws SQLException{
        ArrayList<ImportLogistics> logistics = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select controlId,TRIM(warehouse) as warehouse,TRIM(code) as code,TRIM(supplierNumber) as supplierNumber,TRIM(supplierName) as supplierName,leadTime,reviewTime,supplierReliability,supplierReliabilityType,stockedItem,MOQ,IOQ,EOQ,logisticUnit1,logisticUnit2,logisticUnit3,logisticUnit4,logisticUnit5,logisticUnit6,insuranceInventory,insuranceInventoryType,targetServiceLevel,TRIM(plcArticleCode) plcArticleCode,plcDate,plcPerc,abcClass,buyingPrice,MSQ,ISQ from [slim4interface_test].[dbo].[S4Import_Logistics]");
            while (rs.next()) {
                ImportLogistics i = new ImportLogistics();
                i.setControlId(rs.getInt("controlId"));
                i.setWarehousecode(rs.getString("warehouse"));
                i.setCode(rs.getString("code"));
                i.setSupplierNumber(rs.getString("supplierNumber"));
                i.setSupplierName(rs.getString("supplierName"));
                i.setLeadTime(rs.getBigDecimal("leadTime"));
                i.setReviewTime(rs.getBigDecimal("reviewTime"));
                i.setSupplierReliability(rs.getBigDecimal("supplierReliability"));
                i.setSupplierReliabilityType(rs.getInt("supplierReliabilityType"));
                i.setStockedItem(rs.getString("stockedItem"));
                i.setMOQ(rs.getInt("MOQ"));
                i.setIOQ(rs.getInt("IOQ"));
                i.setEOQ(rs.getInt("EOQ"));
                i.setLogisticUnit1(rs.getInt("logisticUnit1"));
                i.setLogisticUnit2(rs.getInt("logisticUnit2"));
                i.setLogisticUnit3(rs.getInt("logisticUnit3"));
                i.setLogisticUnit4(rs.getInt("logisticUnit4"));
                i.setLogisticUnit5(rs.getInt("logisticUnit5"));
                i.setLogisticUnit6(rs.getInt("logisticUnit6"));
                i.setInsuranceInventory(rs.getInt("insuranceInventory"));
                i.setInsuranceInventoryType(rs.getInt("insuranceInventoryType"));
                i.setTargetServiceLevel(rs.getBigDecimal("targetServiceLevel"));
                i.setPlcArticleCode(rs.getString("plcArticleCode"));
                i.setPlcDate(rs.getDate("plcDate"));
                i.setPlcPerc(rs.getBigDecimal("plcPerc"));
                i.setAbcClass(rs.getString("abcClass"));
                i.setBuyingPrice(rs.getBigDecimal("buyingPrice"));
                i.setMSQ(rs.getInt("MSQ"));
                i.setISQ(rs.getInt("ISQ"));
                logistics.add(i);
            }
        return logistics;    
    }
}
