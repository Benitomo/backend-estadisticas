
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.Transactions;
import com.backend.slim4.service.TransactionsService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceImp implements TransactionsService {
    @Override
    public ResponseEntity transactionsSelect() {
        String tituloResp  = "";
        String mensajeResp = "";
    ArrayList<Transactions> transaction = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT first 70000 * from transactions";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Transactions t = new Transactions();
                t.setControlId(rs.getInt("controlid"));
                t.setTransactionNumber(rs.getString("transactionnumber"));
                t.setTransactionType(rs.getString("transactiontype"));
                t.setTransactionName(rs.getString("transactionname"));
                t.setWarehouse(rs.getString("warehouse"));
                t.setCode(rs.getString("code"));
                t.setIssueDate(rs.getDate("issuedate"));
                t.setConfirmedDate(rs.getDate("confirmeddate"));
                t.setRequestedDate(rs.getDate("requestdate"));
                t.setIssueQuantity(rs.getBigDecimal("issuequantity"));
                t.setLineNumber(rs.getString("linenumber"));
                t.setConfirmedQuantity(rs.getBigDecimal("confirmedquantity"));
                t.setRequestedQuantity(rs.getBigDecimal("requestquantity"));
                t.setCustomerNumber(rs.getString("customernumber"));
                t.setCustomerType(rs.getString("customertype"));
                t.setCustomerName(rs.getString("customername"));
                t.setSalesPrice(rs.getBigDecimal("salesprice"));
                t.setDeliveryLocation(rs.getString("deliverylocation"));
                t.setSupplierNumber(rs.getString("suppliernumber"));
                t.setSupplierType(rs.getString("suppliertype"));
                t.setSupplierName(rs.getString("suppliername"));
                t.setBuyingPrice(rs.getBigDecimal("buyingprice"));
                t.setSupplyingLocation(rs.getString("supplyinglocation"));
                t.setConversionFactor(rs.getBigDecimal("conversionfactor"));
                t.setuD1(rs.getString("ud1"));
                t.setuD1(rs.getString("ud2"));
                t.setuD1(rs.getString("ud3"));
                t.setuD1(rs.getString("ud4"));
                t.setTransactionIssueTime(rs.getString("transactionissuetime"));
                transaction.add(t);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(transaction.size()>0){
            try {
                return transactionsInsert(transaction);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PurchaseOrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            tituloResp = "Error";
            mensajeResp = "La tabla transactions está vacía o hay inconvenientes de columnas";
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }
    
    
    public ResponseEntity transactionsInsert(ArrayList<Transactions> p) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,p);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface Transactions correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_Transactions previo a realizar la inserción.";
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
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_Transactions]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
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
                        + "'"+  t.get(i).getTransactionNumber()+"', "
                        + "'" + t.get(i).getTransactionType() + "', "
                        + "'" + t.get(i).getTransactionName()+ "', "
                        + "'" + t.get(i).getTransactionStatus()+ "', "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getIssueDate()+ "', "
                        + "'" + t.get(i).getConfirmedDate()+ "', "
                        + "'" + t.get(i).getRequestedDate()+ "', "
                        + "'" + t.get(i).getIssueQuantity()+ "', "
                        + "'" + t.get(i).getLineNumber()+ "', "
                        + "'" + t.get(i).getConfirmedQuantity()+ "', "
                        + "'" + t.get(i).getRequestedQuantity()+ "', "
                        + "'" + t.get(i).getCustomerNumber()+ "', "
                        + "'" + t.get(i).getCustomerType() + "', "
                        + "'" + t.get(i).getCustomerName()+ "', "
                        + "'" + t.get(i).getSalesPrice()+ "', "
                        + "'" + t.get(i).getDeliveryLocation()+ "', "
                        + "'" + t.get(i).getSupplierNumber()+ "', "
                        + "'" + t.get(i).getSupplierType()+ "', "
                        + "'" + t.get(i).getSupplierName()+ "'))"
                        + "'" + t.get(i).getBuyingPrice()+ "', "
                        + "'" + t.get(i).getSupplyingLocation()+ "', "
                        + "'" + t.get(i).getConversionFactor()+ "', "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', "
                        + "'" + t.get(i).getTransactionIssueTime()+ "', "
                        + " as temporal"
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
                        + "'" + t.get(i).getIssueQuantity()+ "', "
                        + "'" + t.get(i).getLineNumber()+ "', "
                        + "'" + t.get(i).getConfirmedQuantity()+ "', "
                        + "'" + t.get(i).getRequestedQuantity()+ "', "
                        + "'" + t.get(i).getCustomerNumber()+ "', "
                        + "'" + t.get(i).getCustomerType() + "', "
                        + "'" + t.get(i).getCustomerName()+ "', "
                        + "'" + t.get(i).getSalesPrice()+ "', "
                        + "'" + t.get(i).getDeliveryLocation()+ "', "
                        + "'" + t.get(i).getSupplierNumber()+ "', "
                        + "'" + t.get(i).getSupplierType()+ "', "
                        + "'" + t.get(i).getSupplierName()+ "'))"
                        + "'" + t.get(i).getBuyingPrice()+ "', "
                        + "'" + t.get(i).getSupplyingLocation()+ "', "
                        + "'" + t.get(i).getConversionFactor()+ "', "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', "
                        + "'" + t.get(i).getTransactionIssueTime()+ "', "
                        + " as temporal"
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
                            + "),";
            }
        return stmt.executeUpdate(sql);
    } 
}
