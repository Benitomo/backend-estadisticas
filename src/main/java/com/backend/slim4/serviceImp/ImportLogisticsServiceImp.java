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
    
    // Variable límite de registros
    private static final int REGISTROS_BATCH = 1000;
    @Override
    public ResponseEntity importLogisticsSelect() {
        // Mensaje de respuesta
        String tituloResp  = "";
        String mensajeResp = "";
        
        // Prepare Stament para inserción en Sql Server, se insertará por bloques.
        String sqlPrepare = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_Logistics]"
                + "(controlId,"
                + "warehouse,"
                + "code,"
                + "supplierNumber,"
                + "supplierName,"
                + "leadTime,"
                + "reviewTime,"
                + "supplierReliability,"
                + "supplierReliabilityType,"
                + "stockedItem,"
                + "MOQ,"
                + "IOQ,"
                + "EOQ,"
                + "logisticUnit1,"
                + "logisticUnit2,"
                + "logisticUnit3,"
                + "logisticUnit4,"
                + "logisticUnit5,"
                + "logisticUnit6,"
                + "insuranceInventory,"
                + "insuranceInventoryType,"
                + "targetServiceLevel,"
                + "plcArticleCode,"
                + "plcDate,"
                + "plcPerc,"
                + "abcClass,"
                + "buyingPrice,"
                + "MSQ,"
                + "ISQ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            // Informix
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            // Sql Server
            Connection cnt2 = GetConnection.sqlServer();
            Statement stmt2 = cnt2.createStatement();
            // Query que trae la información de Informix
            String sql = "SELECT * from logistics";
            System.out.print("\n Entré a ejecutar query select en informix \n");
            try (PreparedStatement pstmt = cnt2.prepareStatement(sqlPrepare)) {
            // Ejecutamos el query que trae la información de Informix    
            ResultSet rs = stmt.executeQuery(sql);
            int counter = 0;
            int r = emptyTable(stmt2);
            System.out.print("\n Resultado del Delete: " + r + "\n");
            if(r>=0){
                while (rs.next()) {
                
                pstmt.setInt(1, rs.getInt("controlid"));
                pstmt.setString(2, rs.getString("warehousecode"));
                pstmt.setString(3, rs.getString("articlecode"));
                pstmt.setString(4, rs.getString("suppliernumber"));
                pstmt.setString(5, rs.getString("suppliername"));
                pstmt.setBigDecimal(6, rs.getBigDecimal("leadtime"));
                pstmt.setBigDecimal(7, rs.getBigDecimal("reviewtime"));
                pstmt.setBigDecimal(8, rs.getBigDecimal("supplierreliability"));
                pstmt.setInt(9, rs.getInt("supplierreliabilitytype"));
                pstmt.setString(10, rs.getString("stockeditem"));
                pstmt.setInt(11, rs.getInt("moq"));
                pstmt.setInt(12, rs.getInt("ioq"));
                pstmt.setInt(13, rs.getInt("eoq"));
                pstmt.setInt(14, rs.getInt("logisticunit1"));
                pstmt.setInt(15, rs.getInt("logisticunit2"));
                pstmt.setInt(16, rs.getInt("logisticunit3"));
                pstmt.setInt(17, rs.getInt("logisticunit4"));
                pstmt.setInt(18, rs.getInt("logisticunit5"));
                pstmt.setInt(19, rs.getInt("logisticunit6"));
                pstmt.setInt(20, rs.getInt("insuranceinventory"));
                pstmt.setInt(21, rs.getInt("insuranceinventorytype"));
                pstmt.setBigDecimal(22, rs.getBigDecimal("targetservicelevel"));
                pstmt.setString(23, rs.getString("plcarticlecode"));
                pstmt.setDate(24, rs.getDate("plcdate"));
                pstmt.setBigDecimal(25, rs.getBigDecimal("plcperc"));
                pstmt.setString(26, rs.getString("abcclass"));
                pstmt.setBigDecimal(27, rs.getBigDecimal("buyingprice"));
                pstmt.setInt(28, rs.getInt("msq"));
                pstmt.setInt(29, rs.getInt("isq"));
                
                pstmt.addBatch();
                counter++;
                if (counter == REGISTROS_BATCH) {
                    pstmt.executeBatch();
                    counter = 0;
                }
                
                }
                //revisamos si todavía hay sentencias pendientes de ejecutar
                if (counter > 0) {
                    pstmt.executeBatch();
                }
                System.out.print("\n Proceso finalizado! \n");
                tituloResp = "Éxito";
                mensajeResp = "se ejecutó la interface Logistics correctamente!";
                }else{
                    tituloResp = "Error";
                    mensajeResp = "Hubo problemas al eliminar la información de Sql Server previo a la inserción";
                }
            }
             
            cnt.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }
    
    public int emptyTable(Statement stmt){
        System.out.print("\n Entré a ejecutar query delete en Sql Server \n");
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_Logistics]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
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
    
    
    
    public int insertDataTable(Statement stmt, ArrayList<ImportLogistics> il) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_Logistics]"
                + "(controlId,"
                + "warehouse,"
                + "code,"
                + "supplierNumber,"
                + "supplierName,"
                + "leadTime,"
                + "reviewTime,"
                + "supplierReliability,"
                + "supplierReliabilityType,"
                + "stockedItem,"
                + "MOQ,"
                + "IOQ,"
                + "EOQ,"
                + "logisticUnit1,"
                + "logisticUnit2,"
                + "logisticUnit3,"
                + "logisticUnit4,"
                + "logisticUnit5,"
                + "logisticUnit6,"
                + "insuranceInventory,"
                + "insuranceInventoryType,"
                + "targetServiceLevel,"
                + "plcArticleCode,"
                + "plcDate,"
                + "plcPerc,"
                + "abcClass,"
                + "buyingPrice,"
                + "MSQ,"
                + "ISQ) "
                + "SELECT * FROM  (VALUES";
            for (int i = 0; i < il.size(); i++) {
                sql =  (i==il.size()-1)? 
                        sql + "(" 
                        + il.get(i).getControlId() + ", "
                        + "'"+ il.get(i).getWarehousecode() +"', "
                        + "'" + il.get(i).getCode() + "', "
                        + "'" + il.get(i).getSupplierNumber()+ "', "
                        + "'" + il.get(i).getSupplierName()+ "', "
                        + ""  + il.get(i).getLeadTime()+ ", "
                        + ""  + il.get(i).getReviewTime()+ ", "
                        + ""  + il.get(i).getSupplierReliability()+ ", "
                        + "'" + il.get(i).getSupplierReliabilityType()+ "', "
                        + " " +null+","
                        //+ "'" + il.get(i).getStockedItem()+ "', "
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
                        + ""  + il.get(i).getTargetServiceLevel()+ ", "
                        + "'" + il.get(i).getPlcArticleCode()+ "', "
                        + "'" + il.get(i).getPlcDate()+ "', "
                        + ""  + il.get(i).getPlcPerc()+ ", "
                        + ""+null+","
                        //+ "'" + il.get(i).getAbcClass()+ "', "
                        + ""  + il.get(i).getBuyingPrice()+ ", "
                        + "'" + il.get(i).getMSQ()+ "', "
                        + "'" + il.get(i).getISQ()+ "'"
                        + "))"
                        + "as temporal"
                        + "(controlId,warehouse,code,supplierNumber,supplierName,leadTime,reviewTime,supplierReliability,supplierReliabilityType,stockedItem,MOQ,IOQ,EOQ,logisticUnit1,logisticUnit2,logisticUnit3,logisticUnit4,logisticUnit5,logisticUnit6,insuranceInventory,insuranceInventoryType,targetServiceLevel,plcArticleCode,plcDate,plcPerc,abcClass,buyingPrice,MSQ,ISQ)"
                            :
                        sql + "(" 
                        + il.get(i).getControlId() + ", "
                        + "'" + il.get(i).getWarehousecode() +"', "
                        + "'" + il.get(i).getCode() + "', "
                        + "'" + il.get(i).getSupplierNumber()+ "', "
                        + "'" + il.get(i).getSupplierName()+ "', "
                        + ""  + il.get(i).getLeadTime()+ ", "
                        + ""  + il.get(i).getReviewTime()+ ", "
                        + ""  + il.get(i).getSupplierReliability()+ ", "
                        + "'" + il.get(i).getSupplierReliabilityType()+ "', "
                        + " " +null+","
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
                        + ""  + il.get(i).getTargetServiceLevel()+ ", "
                        + "'" + il.get(i).getPlcArticleCode()+ "', "
                        + "'" + il.get(i).getPlcDate()+ "', "
                        + ""  + il.get(i).getPlcPerc()+ ", "
                        + ""+null+","
                        + ""  + il.get(i).getBuyingPrice()+ ", "
                        + "'" + il.get(i).getMSQ()+ "', "
                        + "'" + il.get(i).getISQ()+ "'),";
            }
        return stmt.executeUpdate(sql);
    } 
    
}
