
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ImportControl;
import com.backend.slim4.model.Transactions;
import com.backend.slim4.service.ImportControlService;
import com.backend.slim4.service.TransactionsService;
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
public class TransactionsServiceImp implements TransactionsService {
    @Autowired
    ImportControlService control_service;
    // Variable límite de registros
    private static final int REGISTROS_BATCH = 1000;
    // ID de la interface
    private static final int importType = 11;
    @Override
    public ResponseEntity transactionsSelect() {
    // Mensaje de respuesta
        String tituloResp  = "";
        String mensajeResp = "";
         // Prepare Stament para inserción en Sql Server, se insertará por bloques.
        String sqlPrepare = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_Transactions]"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            // Informix
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            // Sql Server
            Connection cnt2 = GetConnection.sqlServer();
            Statement stmt2 = cnt2.createStatement();
            // Query que trae la información de Informix
            System.out.print("\n ------------------------------TRANSACTIONS------------------------------ \n");
            System.out.print("\n Entré a ejecutar query select en informix \n");
            String sql = "SELECT "
                    + "controlid,"
                    + "TRIM(transactionnumber) as transactionnumber,"
                    + "TRIM(transactiontype) as transactiontype,"
                    + "TRIM(transactionname) as transactionname,"
                    + "transactionstatus,"
                    + "TRIM(warehouse) as warehouse,"
                    + "TRIM(articlecode) as articlecode,"
                    + "issuedate,"
                    + "confirmeddate,"
                    + "requesteddate,"
                    + "issuequantity,"
                    + "TRIM(linenumber) as linenumber,"
                    + "confirmedquantity,"
                    + "requestedquantity,"
                    + "TRIM(customernumber) as customernumber,"
                    + "customertype,"
                    + "TRIM(customername) as customername,"
                    + "salesprice,"
                    + "deliverylocation,"
                    + "suppliernumber,"
                    + "suppliertype,"
                    + "suppliername,"
                    + "buyingprice,"
                    + "supplyinglocation,"
                    + "conversionfactor,"
                    + "TRIM(ud1) as ud1,"
                    + "TRIM(ud2) as ud2,"
                    + "TRIM(ud3) as ud3,"
                    + "TRIM(ud4) as ud4,"
                    + "transactionissuetime "
                    + "from transactions";
           try (PreparedStatement pstmt = cnt2.prepareStatement(sqlPrepare)) {
            // Ejecutamos el query que trae la información de Informix    
            ResultSet rs = stmt.executeQuery(sql);
            int counter = 0;
            int r = emptyTable(stmt2);
            
            System.out.print("\n Registros eliminados en Sql Server: " + r + "\n");
            if(r>=0){
                System.out.print("\n Entré a insertar la info " + "\n");
                while (rs.next()) {
                pstmt.setInt(1, rs.getInt("controlid"));
                pstmt.setString(2, rs.getString("transactionnumber"));
                pstmt.setString(3, rs.getString("transactiontype"));
                pstmt.setString(4, rs.getString("transactionname"));
                pstmt.setString(5, rs.getString("transactionstatus"));
                pstmt.setString(6, rs.getString("warehouse"));
                pstmt.setString(7, rs.getString("articlecode"));
                pstmt.setDate(8, rs.getDate("issuedate"));
                pstmt.setDate(9, rs.getDate("confirmeddate"));
                pstmt.setDate(10, rs.getDate("requesteddate"));
                pstmt.setBigDecimal(11, rs.getBigDecimal("issuequantity"));
                pstmt.setString(12, rs.getString("linenumber"));
                pstmt.setBigDecimal(13, rs.getBigDecimal("confirmedquantity"));
                pstmt.setBigDecimal(14, rs.getBigDecimal("requestedquantity"));
                pstmt.setString(15, rs.getString("customernumber"));
                pstmt.setString(16, rs.getString("customertype"));
                pstmt.setString(17, rs.getString("customername"));
                pstmt.setBigDecimal(18, rs.getBigDecimal("salesprice"));
                pstmt.setString(19, rs.getString("deliverylocation"));
                pstmt.setString(20, rs.getString("suppliernumber"));
                pstmt.setString(21, rs.getString("suppliertype"));
                pstmt.setString(22, rs.getString("suppliername"));
                pstmt.setBigDecimal(23, rs.getBigDecimal("buyingprice"));
                pstmt.setString(24, rs.getString("supplyinglocation"));
                pstmt.setBigDecimal(25, rs.getBigDecimal("conversionfactor"));
                pstmt.setString(26, rs.getString("ud1"));
                pstmt.setString(27, rs.getString("ud2"));
                pstmt.setString(28, rs.getString("ud3"));
                pstmt.setString(29, rs.getString("ud4"));
                pstmt.setString(30, rs.getString("transactionissuetime"));
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
                        System.out.print("\n ------------------------------TRANSACTIONS------------------------------ \n");
                        tituloResp = "Éxito";
                        mensajeResp = "se ejecutó la interface Transactions correctamente!";
                        }else{
                            tituloResp = "Error";
                            mensajeResp = "Hubo problemas al eliminar la información de Sql Server previo a la inserción";
                        }
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
        }
    
    public int emptyTable(Statement stmt){
        System.out.print("\n Entré a ejecutar query delete en Sql Server \n");
        
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_Transactions]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
    public ResponseEntity transactionsInsert(ArrayList<Transactions> t) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,t);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface Transactions correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_Transactions previo a realizar la inserción.";
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
    
     
    public int insertDataTable(Statement stmt, ArrayList<Transactions> t) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_Transactions]"
                + "("
                + "controlId,"
                + "transactionNumber,"
                + "transactionType,"
                + "transactionName,"
                + "transactionStatus,"
                + "warehouse,"
                + "code,"
                + "issueDate,"
                + "confirmedDate,"
                + "requestedDate,"
                + "issueQuantity,"
                + "lineNumber,"
                + "confirmedQuantity,"
                + "requestedQuantity,"
                + "customerNumber,"
                + "customerType,"
                + "customerName, "
                + "salesPrice, "
                + "deliveryLocation, "
                + "supplierNumber, "
                + "supplierType,"
                + "supplierName,"
                + "buyingPrice,"
                + "supplyingLocation,"
                + "conversionFactor,"
                + "uD1,"
                + "uD2,"
                + "uD3,"
                + "uD4,"
                + "issueTime"
                + ")"
                + "SELECT * FROM  (VALUES";
            for (int i = 0; i < t.size(); i++) {
                sql =  (i==t.size()-1)? 
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getTransactionNumber()+"', "
                        + "'" + t.get(i).getTransactionType() + "', "
                        + "'" + t.get(i).getTransactionName()+ "', "
                        + "'" + t.get(i).getTransactionStatus()+ "', "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getIssueDate()+ "', "
                        + "'" + t.get(i).getConfirmedDate()+ "', "
                        + "'" + t.get(i).getRequestedDate()+ "', "
                        + ""  + t.get(i).getIssueQuantity()+ ", "
                        + "'" + t.get(i).getLineNumber()+ "', "
                        + ""  + t.get(i).getConfirmedQuantity()+ ", "
                        + ""  + t.get(i).getRequestedQuantity()+ ", "
                        + "'" + t.get(i).getCustomerNumber()+ "', "
                        + "'" + t.get(i).getCustomerType() + "', "
                        + "'" + t.get(i).getCustomerName()+ "', "
                        + ""  + t.get(i).getSalesPrice()+ ", "
                        + "'" + t.get(i).getDeliveryLocation()+ "', "
                        + "'" + t.get(i).getSupplierNumber()+ "', "
                        + "'" + t.get(i).getSupplierType()+ "', "
                        + "'" + t.get(i).getSupplierName()+ "',"
                        + ""  + t.get(i).getBuyingPrice()+ ", "
                        + "'" + t.get(i).getSupplyingLocation()+ "', "
                        + ""  + t.get(i).getConversionFactor()+ ", "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', "
                        + "'" + t.get(i).getTransactionIssueTime()+ "'"
                        + ")) as temporal"
                        + "("
                            + "controlId,"
                            + "transactionNumber,"
                            + "transactionType,"
                            + "transactionName,"
                            + "transactionStatus,"
                            + "warehouse,"
                            + "code,"
                            + "issueDate,"
                            + "confirmedDate,"
                            + "requestedDate,"
                            + "issueQuantity,"
                            + "lineNumber,"
                            + "confirmedQuantity,"
                            + "requestedQuantity,"
                            + "customerNumber,"
                            + "customerType,"
                            + "customerName, "
                            + "salesPrice, "
                            + "deliveryLocation, "
                            + "supplierNumber, "
                            + "supplierType,"
                            + "supplierName,"
                            + "buyingPrice,"
                            + "supplyingLocation,"
                            + "conversionFactor,"
                            + "uD1,"
                            + "uD2,"
                            + "uD3,"
                            + "uD4,"
                            + "issueTime"
                            + ")"
                :
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'"+  t.get(i).getTransactionNumber()+"', "
                        + "'" + t.get(i).getTransactionType() + "', "
                        + "'" + t.get(i).getTransactionName()+ "', "
                        + "'" + t.get(i).getTransactionStatus()+ "', "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getIssueDate()+ "', "
                        + "'" + t.get(i).getConfirmedDate()+ "', "
                        + "'" + t.get(i).getRequestedDate()+ "', "
                        + ""  + t.get(i).getIssueQuantity()+ ", "
                        + "'" + t.get(i).getLineNumber()+ "', "
                        + ""  + t.get(i).getConfirmedQuantity()+ ", "
                        + ""  + t.get(i).getRequestedQuantity()+ ", "
                        + "'" + t.get(i).getCustomerNumber()+ "', "
                        + "'" + t.get(i).getCustomerType() + "', "
                        + "'" + t.get(i).getCustomerName()+ "', "
                        + ""  + t.get(i).getSalesPrice()+ ", "
                        + "'" + t.get(i).getDeliveryLocation()+ "', "
                        + "'" + t.get(i).getSupplierNumber()+ "', "
                        + "'" + t.get(i).getSupplierType()+ "', "
                        + "'" + t.get(i).getSupplierName()+ "',"
                        + ""  + t.get(i).getBuyingPrice()+ ", "
                        + "'" + t.get(i).getSupplyingLocation()+ "', "
                        + "" +  t.get(i).getConversionFactor()+ ", "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', "
                        + "'" + t.get(i).getTransactionIssueTime()+ "'), ";
                        
            }
        return stmt.executeUpdate(sql);
    } 
}
