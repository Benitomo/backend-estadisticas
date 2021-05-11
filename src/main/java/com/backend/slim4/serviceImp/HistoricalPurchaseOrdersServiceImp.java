
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.HistoricalPurchaseOrders;
import com.backend.slim4.model.ImportControl;
import com.backend.slim4.service.HistoricalPurchaseOrdersService;
import com.backend.slim4.service.ImportControlService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class HistoricalPurchaseOrdersServiceImp implements HistoricalPurchaseOrdersService{
    @Autowired
    ImportControlService control_service;
    
    // Variable límite de registros
    private static final int REGISTROS_BATCH = 1000;
    // ID de la interface
    private static final int importType = 14;
    
    @Override
    public ResponseEntity historicalPurchaseOrdersSelect() {
        // Mensaje de respuesta
        String tituloResp  = "";
        String mensajeResp = "";
        // Prepare Stament para inserción en Sql Server, se insertará por bloques.
        String sqlPrepare = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_HistoricalPurchaseOrders]"
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
                + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                    + "TRIM(ordernumber) as ordernumber,"
                    + "line,"
                    + "ordertypenumber,"
                    + "delivereddate,"
                    + "deliverdquantity,"
                    + "supplier,"
                    + "TRIM(comments) as comments,"
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
                    + "TRIM(suppliernumber) as suppliernumber ,"
                    + "TRIM(suppliername) as suppliername "
                    + "from historicalpo";
            System.out.print("\n ------------------------------HISTORICALPO------------------------------ \n");
            System.out.print("\n-Trayendo información de la base de datos Informix \n");
            try (PreparedStatement pstmt = cnt2.prepareStatement(sqlPrepare)) {
               // Ejecutamos el query que trae la información de Informix    
               ResultSet rs = stmt.executeQuery(sql);
               // Contador que nos permite saber cuando llegamos al límite de inserción
               int counter = 0;
               int r = emptyTable(stmt2);
               if(r>=0){
                    System.out.print("\n-Realizando inserción de datos en bloque en Sql Server mediante lotes de 1000 registros \n");
                    while (rs.next()) {
                        pstmt.setInt(1, rs.getInt("controlid"));
                        pstmt.setString(2, rs.getString("warehousecode"));
                        pstmt.setString(3, rs.getString("articlecode"));
                        pstmt.setString(4, rs.getString("ordernumber"));
                        pstmt.setInt(5, rs.getInt("line"));
                        pstmt.setInt(6, rs.getInt("ordertypenumber"));
                        pstmt.setString(7, getFormatoFecha(rs.getString("delivereddate")));
                        pstmt.setInt(8, rs.getInt("deliverdquantity"));
                        pstmt.setString(9, rs.getString("supplier"));
                        pstmt.setString(10, rs.getString("comments"));
                        pstmt.setString(11, rs.getString("freetext1"));
                        pstmt.setString(12, rs.getString("freetext2"));
                        pstmt.setBigDecimal(13, rs.getBigDecimal("freenumber1"));
                        pstmt.setBigDecimal(14, rs.getBigDecimal("freenumber2"));
                        pstmt.setString(15, getFormatoFecha(rs.getString("ordereddate")));
                        pstmt.setString(16, getFormatoFecha(rs.getString("requesteddate")));
                        pstmt.setInt(17, rs.getInt("orderedquantity"));
                        pstmt.setInt(18, rs.getInt("requestedquantity"));
                        pstmt.setInt(19, rs.getInt("confirmedquantity"));
                        pstmt.setString(20, getFormatoFecha(rs.getString("confirmeddate")));
                        pstmt.setString(21, rs.getString("suppliernumber"));
                        pstmt.setString(22, rs.getString("suppliername"));
                        
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
                         System.out.print("\n ------------------------------HISTORICALPO------------------------------ \n");
                         tituloResp = "Éxito";
                         mensajeResp = "se ejecutó la interface HistoricalPurchaseOrders correctamente!";
                }else{
                    tituloResp = "Error";
                    mensajeResp = "Hubo problemas al eliminar la información de Sql Server previo a la inserción";
                   }
               
            } catch (ParseException ex) {
                Logger.getLogger(HistoricalPurchaseOrdersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(HistoricalPurchaseOrdersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HistoricalPurchaseOrdersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }
    
    public String getFormatoFecha(String fecha) throws ParseException{
        String convertion = null;
        if(fecha != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            convertion = sdf.format(sdf2.parse(fecha));
        } 
        
        return convertion;
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
        System.out.print("\n-Eliminando registros de Sql Server" + "\n");
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_HistoricalPurchaseOrders]";
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
