
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ImportControl;
import com.backend.slim4.model.Suppliers;
import com.backend.slim4.service.ImportControlService;
import com.backend.slim4.service.SuppliersService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SuppliersServiceImp implements SuppliersService{
    @Autowired
    ImportControlService control_service;
    // Variable límite de registros
    private static final int REGISTROS_BATCH = 1000;
    // ID de la interface
    private static final int importType = 13;
    
    @Override
    public ResponseEntity suppliersSelect() {
        // Mensaje de respuesta
        String tituloResp  = "";
        String mensajeResp = "";
        // Prepare Stament para inserción en Sql Server, se insertará por bloques.
        String sqlPrepare = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_Suppliers]"
                + "("
                + "controlId,"
                + "warehouse,"
                + "code,"
                + "supplierNumber, "
                + "supplierName,"
                + "primarySupplier,"
                + "preference,"
                + "leadTime,"
                + "reviewTime,"
                + "buyingPrice,"
                + "currencyCode,"
                + "minimumOrderQuantity,"
                + "incrementalOrderQuantity,"
                + "economicOrderQuantity,"
                + "supplierReliability,"
                + "supplierReliabilitySetting,"
                + "supplierArticleCode,"
                + "availableInventory,"
                + "desiredSplit,"
                + "suppliedQuantity,"
                + "orderFromDate,"
                + "orderToDate,"
                + "logisticUnit1,"
                + "logisticUnit2,"
                + "logisticUnit3,"
                + "logisticUnit4,"
                + "logisticUnit5,"
                + "logisticUnit6,"
                + "uD1,"
                + "uD2,"
                + "uD3,"
                + "uD4,"
                + "uD5"
                + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            // Informix
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            // Sql Server
            Connection cnt2 = GetConnection.sqlServer();
            Statement stmt2 = cnt2.createStatement();
            // Query que trae la información de Informix
            String sql = "SELECT "
                    + "controlid,"
                    + "TRIM(warehousecode) as warehousecode,"
                    + "TRIM(articlecode) as articlecode,"
                    + "TRIM(suppliernumber) as suppliernumber,"
                    + "TRIM(suppliername) as suppliername,"
                    + "primarysupplier,"
                    + "preference,"
                    + "leadtime,"
                    + "reviewtime,"
                    + "buyingprice,"
                    + "currencycode,"
                    + "moq,"
                    + "ioq,"
                    + "eoq,"
                    + "supplierreliability,"
                    + "supplierreliabilitytype,"
                    + "TRIM(supplierarticlecode) as supplierarticlecode,"
                    + "availableinventory,"
                    + "desiredsplit,"
                    + "suppliedquantity,"
                    + "orderfromdate,"
                    + "ordertodate,"
                    + "logisticUnit1,"
                    + "logisticUnit2,"
                    + "logisticUnit3,"
                    + "logisticUnit4,"
                    + "logisticUnit5,"
                    + "logisticUnit6,"
                    + "TRIM(ud1) as ud1,"
                    + "TRIM(ud2) as ud2,"
                    + "TRIM(ud3) as ud3,"
                    + "TRIM(ud4) as ud4,"
                    + "TRIM(ud5) as ud5 "
                    + "from suppliers";
            System.out.print("\n ------------------------------SUPPLIERS------------------------------ \n");
            System.out.print("\n Entré a ejecutar query select en informix \n");
            
            try (PreparedStatement pstmt = cnt2.prepareStatement(sqlPrepare)) {
               // Ejecutamos el query que trae la información de Informix    
               ResultSet rs = stmt.executeQuery(sql);
               // Contador que nos permite saber cuando llegamos al límite de inserción
               int counter = 0;
               int r = emptyTable(stmt2);
               System.out.print("\n Resultado del Delete: " + r + "\n");
               if(r>=0){
                   System.out.print("\n Entré al while next \n");
                   while (rs.next()) {
                        pstmt.setInt(1, rs.getInt("controlid"));
                        pstmt.setString(2, rs.getString("warehousecode"));
                        pstmt.setString(3, rs.getString("articlecode"));
                        pstmt.setString(4, rs.getString("suppliernumber"));
                        pstmt.setString(5, rs.getString("suppliername"));
                        pstmt.setInt(6, rs.getInt("primarysupplier"));
                        pstmt.setInt(7, rs.getInt("preference"));
                        pstmt.setBigDecimal(8, rs.getBigDecimal("leadtime"));
                        pstmt.setBigDecimal(9, rs.getBigDecimal("reviewtime"));
                        pstmt.setBigDecimal(10, rs.getBigDecimal("buyingprice"));
                        pstmt.setString(11, rs.getString("currencycode"));
                        pstmt.setInt(12, rs.getInt("moq"));
                        pstmt.setInt(13, rs.getInt("ioq"));
                        pstmt.setInt(14, rs.getInt("eoq"));
                        pstmt.setBigDecimal(15, rs.getBigDecimal("supplierreliability"));
                        pstmt.setInt(16, rs.getInt("supplierreliabilitytype"));
                        pstmt.setString(17, rs.getString("supplierarticlecode"));
                        pstmt.setInt(18, rs.getInt("availableinventory"));
                        pstmt.setBigDecimal(19, rs.getBigDecimal("desiredsplit"));
                        pstmt.setInt(20, rs.getInt("suppliedquantity"));
                        pstmt.setDate(21, rs.getDate("orderfromdate"));
                        pstmt.setDate(22, rs.getDate("ordertodate"));
                        pstmt.setInt(23, rs.getInt("logisticUnit1"));
                        pstmt.setInt(24, rs.getInt("logisticUnit2"));
                        pstmt.setInt(25, rs.getInt("logisticUnit3"));
                        pstmt.setInt(26, rs.getInt("logisticUnit4"));
                        pstmt.setInt(27, rs.getInt("logisticUnit5"));
                        pstmt.setInt(28, rs.getInt("logisticUnit6"));
                        pstmt.setString(29, rs.getString("ud1"));
                        pstmt.setString(30, rs.getString("ud1"));
                        pstmt.setString(31, rs.getString("ud1"));
                        pstmt.setString(32, rs.getString("ud1"));
                        pstmt.setString(33, rs.getString("ud1"));
                        
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
                        ImportControl control = new ImportControl();
                        control.setControlID(1);
                        control.setImportType(importType);
                        control.setControlTimestamp("");
                        control.setControlStatus(4);
                        control_service.insert(stmt2, importType, control);
                         System.out.print("\n Proceso finalizado! \n");
                         System.out.print("\n ------------------------------SUPPLIERS------------------------------ \n");
                         tituloResp = "Éxito";
                         mensajeResp = "se ejecutó la interface Suppliers correctamente!";
                }else{
                    tituloResp = "Error";
                    mensajeResp = "Hubo problemas al eliminar la información de Sql Server previo a la inserción";
                   }
               
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }
    
    public int emptyTable(Statement stmt){
        System.out.print("\n Entré a eliminar los registros de Sql Server \n");
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_Suppliers]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
    
     public ResponseEntity suppliersInsert(ArrayList<Suppliers> t) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,t);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface Suppliers correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_Suppliers previo a realizar la inserción.";
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
    
    
    
    public int insertDataTable(Statement stmt, ArrayList<Suppliers> t) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_Suppliers]"
                + "("
                + "controlId,"
                + "warehouse,"
                + "code,"
                + "supplierNumber, "
                + "supplierName,"
                + "primarySupplier,"
                + "preference,"
                + "leadTime,"
                + "reviewTime,"
                + "buyingPrice,"
                + "currencyCode,"
                + "minimumOrderQuantity,"
                + "incrementalOrderQuantity,"
                + "economicOrderQuantity,"
                + "supplierReliability,"
                + "supplierArticleCode,"
                + "availableInventory,"
                + "desiredSplit,"
                + "suppliedQuantity,"
                + "orderFromDate,"
                + "orderToDate,"
                + "logisticUnit1,"
                + "logisticUnit2,"
                + "logisticUnit3,"
                + "logisticUnit4,"
                + "logisticUnit5,"
                + "logisticUnit6,"
                + "uD1,"
                + "uD2,"
                + "uD3,"
                + "uD4,"
                + "uD5"
                + ")"
                + "SELECT * FROM  (VALUES";
            for (int i = 0; i < t.size(); i++) {
                sql =  (i==t.size()-1)? 
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getSupplierNumber()+ "', "
                        + "'" + t.get(i).getSupplierName()+ "',"
                        + "" + t.get(i).getPrimarySupplier()+ ", "
                        + ""  + t.get(i).getPreference()+ ", "
                        + ""  + t.get(i).getLeadTime()+ ", "
                        + ""  + t.get(i).getReviewTime()+ ", "
                        + ""  + t.get(i).getBuyingPrice()+ ", "
                        //+ "'"  + t.get(i).getCurrencyCode()+ "', "
                        + ""  + null + ", "
                        + ""  + t.get(i).getMOQ()+ ", "
                        + ""  + t.get(i).getIOQ()+ ", "
                        + ""  + t.get(i).getEOQ()+ ", "
                        + ""  + t.get(i).getSupplierReliability()+ ", "
                        + "'"  + t.get(i).getSupplierArticleCode()+ "', "
                        + ""  + t.get(i).getAvailableInventory()+ ", "
                        + ""  + t.get(i).getDesiredSplit()+ ", "
                        + ""  + t.get(i).getSuppliedQuantity()+ ", "
                        + "'"  + t.get(i).getOrderFromDate()+ "', "
                        + "'"  + t.get(i).getOrderToDate()+ "', "
                        + ""  + t.get(i).getLogisticUnit1()+ ", "
                        + ""  + t.get(i).getLogisticUnit2()+ ", "
                        + ""  + t.get(i).getLogisticUnit3()+ ", "
                        + ""  + t.get(i).getLogisticUnit4()+ ", "
                        + ""  + t.get(i).getLogisticUnit5()+ ", "
                        + ""  + t.get(i).getLogisticUnit6()+ ", "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', "
                        + "'" + t.get(i).getuD5()+ "'"
                        + ")) as temporal"
                        + "("
                            + "controlId,"
                            + "warehouse,"
                            + "code,"
                            + "supplierNumber, "
                            + "supplierName,"
                            + "primarySupplier,"
                            + "preference,"
                            + "leadTime,"
                            + "reviewTime,"
                            + "buyingPrice,"
                            + "currencyCode,"
                            + "minimumOrderQuantity,"
                            + "incrementalOrderQuantity,"
                            + "economicOrderQuantity,"
                            + "supplierReliability,"
                            + "supplierArticleCode,"
                            + "availableInventory,"
                            + "desiredSplit,"
                            + "suppliedQuantity,"
                            + "orderFromDate,"
                            + "orderToDate,"
                            + "logisticUnit1,"
                            + "logisticUnit2,"
                            + "logisticUnit3,"
                            + "logisticUnit4,"
                            + "logisticUnit5,"
                            + "logisticUnit6,"
                            + "uD1,"
                            + "uD2,"
                            + "uD3,"
                            + "uD4,"
                            + "uD5"
                        + ")"
                :
                         sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getSupplierNumber()+ "', "
                        + "'" + t.get(i).getSupplierName()+ "',"
                        + "" + t.get(i).getPrimarySupplier()+ ", "
                        + ""  + t.get(i).getPreference()+ ", "
                        + ""  + t.get(i).getLeadTime()+ ", "
                        + ""  + t.get(i).getReviewTime()+ ", "
                        + ""  + t.get(i).getBuyingPrice()+ ", "
                        //+ "'"  + t.get(i).getCurrencyCode()+ "', "
                        + ""  + null + ", "
                        + ""  + t.get(i).getMOQ()+ ", "
                        + ""  + t.get(i).getIOQ()+ ", "
                        + ""  + t.get(i).getEOQ()+ ", "
                        + ""  + t.get(i).getSupplierReliability()+ ", "
                        + "'"  + t.get(i).getSupplierArticleCode()+ "', "
                        + ""  + t.get(i).getAvailableInventory()+ ", "
                        + ""  + t.get(i).getDesiredSplit()+ ", "
                        + ""  + t.get(i).getSuppliedQuantity()+ ", "
                        + "'"  + t.get(i).getOrderFromDate()+ "', "
                        + "'"  + t.get(i).getOrderToDate()+ "', "
                        + ""  + t.get(i).getLogisticUnit1()+ ", "
                        + ""  + t.get(i).getLogisticUnit2()+ ", "
                        + ""  + t.get(i).getLogisticUnit3()+ ", "
                        + ""  + t.get(i).getLogisticUnit4()+ ", "
                        + ""  + t.get(i).getLogisticUnit5()+ ", "
                        + ""  + t.get(i).getLogisticUnit6()+ ", "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', "
                        + "'" + t.get(i).getuD5()+ "'),";
                        
            }
        return stmt.executeUpdate(sql);
    } 
}
