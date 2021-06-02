
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ImportControl;
import com.backend.slim4.model.PurchaseOrder;
import com.backend.slim4.service.ImportControlService;
import com.backend.slim4.service.PurchaseOrderService;
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
public class PurchaseOrderServiceImp implements PurchaseOrderService{
    @Autowired
    ImportControlService control_service;
    // Variable límite de registros
    private static final int REGISTROS_BATCH = 1000;
    // ID de la interface
    private static final int importType = 5;
    @Override
    public ResponseEntity purchaseOrderSelect() {
        
       // Mensaje de respuesta
        String tituloResp  = "";
        String mensajeResp = "";
        
        // Prepare Stament para inserción en Sql Server, se insertará por bloques.
        String sqlPrepare = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_PurchaseOrder]"
                + "("
                + "controlId,"
                + "warehouse,"
                + "code,"
                + "number,"
                + "deliveryDate,"
                + "openQuantity,"
                + "supplier,"
                + "comment,"
                + "originalQuantity,"
                + "suppliedQuantity,"
                + "freeText1,"
                + "freeText2,"
                + "freeNumber1,"
                + "freeNumber2,"
                + "type,"
                + "line,"
                + "excludeSetting, "
                + "excludeDate, "
                + "excludeFromAM, "
                + "supplierNumber, "
                + "supplierName"
                + ")"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                    + "TRIM(numbers) as numbers,"
                    + "deliverydate,"
                    + "openquantity,"
                    + "supplier,"
                    + "TRIM(comments) as comments,"
                    + "originalquantity,"
                    + "suppliedquantity,"
                    + "TRIM(freetext1) as freetext1,"
                    + "TRIM(freetext2) as freetext2,"
                    + "freenumber1,"
                    + "freenumber2,"
                    + "ordertypenumber,"
                    + "line,"
                    + "excludesetting, "
                    + "excludedate, "
                    + "excludefromam, "
                    + "TRIM(suppliernumber) as suppliernumber, "
                    + "TRIM(suppliername) as suppliername"
                    + " from purchaseorder";
            System.out.print("\n ------------------------------PURCHASEORDER------------------------------ \n");
            System.out.print("\n-Trayendo información de la base de datos Informix \n");
            try (PreparedStatement pstmt = cnt2.prepareStatement(sqlPrepare)) {
            // Ejecutamos el query que trae la información de Informix    
            ResultSet rs = stmt.executeQuery(sql);
            int counter = 0;
            int r = emptyTable(stmt2);
            
            if(r>=0){
                 System.out.print("\n-Realizando inserción de datos en bloque en Sql Server mediante lotes de 1000 registros \n");
                while (rs.next()) {
                    
                    pstmt.setInt(1, rs.getInt("controlid"));
                    pstmt.setString(2, rs.getString("warehousecode"));
                    pstmt.setString(3, rs.getString("articlecode"));
                    pstmt.setString(4, rs.getString("numbers"));
                    pstmt.setString(5, rs.getString("deliverydate"));
                    pstmt.setInt(6, rs.getInt("openquantity"));
                    pstmt.setString(7, rs.getString("supplier"));
                    pstmt.setString(8, rs.getString("comments"));
                    pstmt.setInt(9, rs.getInt("originalquantity"));
                    pstmt.setInt(10, rs.getInt("suppliedquantity"));
                    pstmt.setString(11, rs.getString("freetext1"));
                    pstmt.setString(12, rs.getString("freetext2"));
                    pstmt.setBigDecimal(13, rs.getBigDecimal("freenumber1"));
                    pstmt.setBigDecimal(14, rs.getBigDecimal("freenumber2"));
                    pstmt.setInt(15, rs.getInt("ordertypenumber"));
                    pstmt.setInt(16, rs.getInt("line"));
                    pstmt.setInt(17, rs.getInt("excludesetting"));
                    pstmt.setString(18, rs.getString("excludedate"));
                    pstmt.setInt(19, rs.getInt("excludefromam"));
                    pstmt.setString(20, rs.getString("suppliernumber"));
                    pstmt.setString(21, rs.getString("suppliername"));

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
                    control_service.insert(stmt2, importType, control);
                    System.out.print("\n Proceso finalizado! \n");
                    System.out.print("\n ------------------------------PURCHASEORDER------------------------------ \n");
                    tituloResp = "Éxito";
                    mensajeResp = "se ejecutó la interface PurchaseOrder correctamente!";
                    }else{
                        tituloResp = "Error";
                        mensajeResp = "Hubo problemas al eliminar la información de Sql Server previo a la inserción";
                    }
            }
            cnt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PurchaseOrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public int emptyTable(Statement stmt){
        System.out.print("\n-Eliminando registros de Sql Server" + "\n");
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_PurchaseOrder]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
    public ResponseEntity purchaseOrderInsert(ArrayList<PurchaseOrder> p) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,p);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface PurchaseOrder correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_PurchaseOrder previo a realizar la inserción.";
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
    
    
    
    public int insertDataTable(Statement stmt, ArrayList<PurchaseOrder> il) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_PurchaseOrder]"
                + "(controlId,warehouse,code,number,deliveryDate,openQuantity,supplier,comment,originalQuantity,suppliedQuantity,freeText1,freeText2,freeNumber1,freeNumber2,type,line,excludeSetting, excludeDate, excludeFromAM, supplierNumber, supplierName)"
                + "SELECT * FROM  (VALUES";
            for (int i = 0; i < il.size(); i++) {
                sql =  (i==il.size()-1)? 
                        sql + "(" + il.get(i).getControlId() + ", "
                        + "'"+ il.get(i).getWarehousecode() +"', "
                        + "'" + il.get(i).getCode() + "', "
                        + "'" + il.get(i).getNumber()+ "', "
                        + "'" + il.get(i).getDeliveryDate()+ "', "
                        + "'" + il.get(i).getOpenQuantity()+ "', "
                        + "'" + il.get(i).getSupplier()+ "', "
                        + "'" + il.get(i).getComment()+ "', "
                        + "'" + il.get(i).getOriginalQuantity()+ "', "
                        + "'" + il.get(i).getSuppliedQuantity()+ "', "
                        + "'"  + il.get(i).getFreeText1()+ "', "
                        + "'"  + il.get(i).getFreeText2()+ "', "
                        + ""  + il.get(i).getFreeNumber1()+ ", "
                        + ""  + il.get(i).getFreeNumber2()+ ", "
                        + "'" + il.get(i).getOrderTypeNumber()+ "', "
                        + "'" + il.get(i).getLine() + "', "
                        + "'" + il.get(i).getExcludeSetting()+ "', "
                        + "'" + il.get(i).getExcludeDate()+ "', "
                        + "'" + il.get(i).getExcludeFromAM()+ "', "
                        + "'" + il.get(i).getSupplierNumber()+ "', "
                        + "'" + il.get(i).getSupplierName()+ "'))"
                        + " as temporal(controlId,warehouse,code,number,deliveryDate,openQuantity,supplier,comment,originalQuantity,suppliedQuantity,freeText1,freeText2,freeNumber1,freeNumber2,type,line,excludeSetting, excludeDate, excludeFromAM, supplierNumber, supplierName)"
                            :
                        sql + "(" + il.get(i).getControlId() + ", "
                        + "'"+ il.get(i).getWarehousecode() +"', "
                        + "'" + il.get(i).getCode() + "', "
                        + "'" + il.get(i).getNumber()+ "', "
                        + "'" + il.get(i).getDeliveryDate()+ "', "
                        + "'" + il.get(i).getOpenQuantity()+ "', "
                        + "'" + il.get(i).getSupplier()+ "', "
                        + "'" + il.get(i).getComment()+ "', "
                        + "'" + il.get(i).getOriginalQuantity()+ "', "
                        + "'" + il.get(i).getSuppliedQuantity()+ "', "
                        + "'" + il.get(i).getFreeText1()+ "', "
                        + "'" + il.get(i).getFreeText2()+ "', "
                        + ""  + il.get(i).getFreeNumber1()+ ", "
                        + ""  + il.get(i).getFreeNumber2()+ ", "
                        + "'" + il.get(i).getOrderTypeNumber()+ "', "
                        + "'" + il.get(i).getLine() + "', "
                        + "'" + il.get(i).getExcludeSetting()+ "', "
                        + "'" + il.get(i).getExcludeDate()+ "', "
                        + "'" + il.get(i).getExcludeFromAM()+ "', "
                        + "'" + il.get(i).getSupplierNumber()+ "', "
                        + "'" + il.get(i).getSupplierName()+ "'),";
            }
        return stmt.executeUpdate(sql);
    } 
}
