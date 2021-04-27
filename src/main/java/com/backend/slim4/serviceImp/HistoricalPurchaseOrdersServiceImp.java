
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.HistoricalPurchaseOrders;
import com.backend.slim4.service.HistoricalPurchaseOrdersService;
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
public class HistoricalPurchaseOrdersServiceImp implements HistoricalPurchaseOrdersService{
    @Override
    public ResponseEntity historicalPurchaseOrdersSelect() {
    String tituloResp  = "";
    String mensajeResp = "";
    ArrayList<HistoricalPurchaseOrders> historical = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT "
                    + "controlid,"
                    + "TRIM(warehousecode) as warehousecode,"
                    + "TRIM(articlecode) as articlecode,"
                    + "ordernumber,"
                    + "line,"
                    + "ordertypenumber,"
                    + "delivereddate,"
                    + "deliverdquantity,"
                    + "supplier,"
                    + "comments,"
                    + "freetext1,"
                    + "freetext2,"
                    + "freenumber1,"
                    + "freenumber2,"
                    + "ordereddate,"
                    + "requesteddate,"
                    + "orderedquantity,"
                    + "requestedquantity,"
                    + "confirmedquantity,"
                    + "confirmeddate,"
                    + "suppliernumber,"
                    + "suppliername "
                    + "from historicalpo";
            System.out.print("\n Entré a traer info de Informix \n Query: \n" + sql + "\n");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HistoricalPurchaseOrders t = new HistoricalPurchaseOrders();
                t.setControlId(rs.getInt("controlid"));
                t.setWarehouse(rs.getString("warehousecode"));
                t.setCode(rs.getString("articlecode"));
                t.setNumber(rs.getString("ordernumber"));
                t.setLine(rs.getInt("line"));
                t.setOrderTypeNumber(rs.getInt("ordertypenumber"));
                t.setDeliveredDate(rs.getDate("deliverddate"));
                t.setSupplier(rs.getString("supplier"));
                t.setComment(rs.getString("coments"));
                t.setFreeText1(rs.getString("freetext1"));
                t.setFreeText2(rs.getString("freetext2"));
                t.setFreeNumber1(rs.getBigDecimal("freenumber1"));
                t.setFreeNumber2(rs.getBigDecimal("freenumber2"));
                t.setOrderedDate(rs.getDate("ordereddate"));
                t.setRequestedDate(rs.getDate("requesteddate"));
                t.setOrderedQuantity(rs.getInt("orderedquantity"));
                t.setRequestedQuantity(rs.getInt("requestedquantity"));
                t.setConfirmedQuantity(rs.getInt("confirmedquantity"));
                t.setConfirmedDate(rs.getDate("confirmeddate"));
                t.setSupplierNumber(rs.getString("suppliernumber"));
                t.setSupplierName(rs.getString("suppliername"));
                historical.add(t);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(historical.size()>0){
            try {
                return historicalPurchaseOrdersInsert(historical);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            tituloResp = "Error";
            mensajeResp = "La tabla historicalpo está vacía o hay inconvenientes de columnas";
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }
    
    public ResponseEntity historicalPurchaseOrdersInsert(ArrayList<HistoricalPurchaseOrders> t) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,t);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface HistoricalPurchaseOrdes correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_HistoricalPurchaseOrders previo a realizar la inserción.";
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
    
    public int emptyTable(Statement stmt){
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_HistoricalPurchaseOrders]";
        System.out.print("\n Entré a eliminar info de Sql Server previo a insertar");
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
     public int insertDataTable(Statement stmt, ArrayList<HistoricalPurchaseOrders> t) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_HistoricalPurchaseOrders]"
                + "("
                + "controlId,"
                + "warehouse,"
                + "code,"
                + "poNumber, "
                + "lineNumber,"
                + "orderTypeNumber,"
                + "deliveredDate,"
                + "deliveredQuantity,"
                + "supplierDetails,"
                + "poComment,"
                + "freeText1,"
                + "freeText2,"
                + "freeNumber1,"
                + "freeNumber2,"
                + "orderedDate,"
                + "requestedDate,"
                + "orderedQuantity,"
                + "requestedQuantity,"
                + "confirmedQuantity,"
                + "confirmedDate,"
                + "supplierNumber,"
                + "supplierName"
                + ")"
                + "SELECT * FROM  (VALUES";
            for (int i = 0; i < t.size(); i++) {
                sql =  (i==t.size()-1)? 
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getNumber()+ "', "
                        + ""  + t.get(i).getLine()+ ", "
                        + ""  + t.get(i).getOrderTypeNumber()+ ", "
                        + "'" + t.get(i).getDeliveredDate()+ "', "
                        + ""  + t.get(i).getDeliveredQuantity()+ ", "
                        + "'" + t.get(i).getSupplier()+ "', "
                        + "'" + t.get(i).getComment()+ "', "
                        + "'" + t.get(i).getFreeText1()+ "', "
                        + "'" + t.get(i).getFreeText2()+ "', "
                        + ""  + t.get(i).getFreeNumber1()+ ", "
                        + ""  + t.get(i).getFreeNumber2()+ ", "
                        + "'" + t.get(i).getOrderedDate()+ "', "
                        + "'" + t.get(i).getRequestedDate()+ "', "
                        + ""  + t.get(i).getOrderedQuantity()+ ", "
                        + ""  + t.get(i).getRequestedQuantity()+ ", "
                        + ""  + t.get(i).getConfirmedQuantity()+ ", "
                        + "'" + t.get(i).getConfirmedDate()+ "', "
                        + "'" + t.get(i).getSupplierNumber()+ "', "
                        + "'" + t.get(i).getSupplierName()+ "'"
                        + ")) as temporal"
                        + "("
                            + "controlId,"
                            + "warehouse,"
                            + "code,"
                            + "poNumber, "
                            + "lineNumber,"
                            + "orderTypeNumber,"
                            + "deliveredDate,"
                            + "deliveredQuantity,"
                            + "supplierDetails,"
                            + "poComment,"
                            + "freeText1,"
                            + "freeText2,"
                            + "freeNumber1,"
                            + "freeNumber2,"
                            + "orderedDate,"
                            + "requestedDate,"
                            + "orderedQuantity,"
                            + "requestedQuantity,"
                            + "confirmedQuantity,"
                            + "confirmedDate,"
                            + "supplierNumber,"
                            + "supplierName"
                        + ")"
                :
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getNumber()+ "', "
                        + ""  + t.get(i).getLine()+ ", "
                        + ""  + t.get(i).getOrderTypeNumber()+ ", "
                        + "'" + t.get(i).getDeliveredDate()+ "', "
                        + ""  + t.get(i).getDeliveredQuantity()+ ", "
                        + "'" + t.get(i).getSupplier()+ "', "
                        + "'" + t.get(i).getComment()+ "', "
                        + "'" + t.get(i).getFreeText1()+ "', "
                        + "'" + t.get(i).getFreeText2()+ "', "
                        + ""  + t.get(i).getFreeNumber1()+ ", "
                        + ""  + t.get(i).getFreeNumber2()+ ", "
                        + "'" + t.get(i).getOrderedDate()+ "', "
                        + "'" + t.get(i).getRequestedDate()+ "', "
                        + ""  + t.get(i).getOrderedQuantity()+ ", "
                        + ""  + t.get(i).getRequestedQuantity()+ ", "
                        + ""  + t.get(i).getConfirmedQuantity()+ ", "
                        + "'" + t.get(i).getConfirmedDate()+ "', "
                        + "'" + t.get(i).getSupplierNumber()+ "', "
                        + "'" + t.get(i).getSupplierName()+ "'),";
                        
            }
            
        System.out.print("\n Entré a insertar la info de Informix a Sql Server \n Query: \n" + sql + "\n");
        return stmt.executeUpdate(sql);
    } 
}
