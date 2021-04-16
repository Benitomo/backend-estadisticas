
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.PurchaseOrder;
import com.backend.slim4.service.PurchaseOrderService;
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
public class PurchaseOrderServiceImp implements PurchaseOrderService{
    @Override
    public ResponseEntity purchaseOrderSelect() {
        String tituloResp  = "";
        String mensajeResp = "";
    ArrayList<PurchaseOrder> purchase = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT first 1000 * from purchaseorder";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PurchaseOrder po = new PurchaseOrder();
                po.setControlId(rs.getInt("controlid"));
                po.setWarehousecode(rs.getString("warehousecode"));
                po.setCode(rs.getString("articlecode"));
                po.setNumber(rs.getString("numbers"));
                po.setDeliveryDate(rs.getDate("deliverydate"));
                po.setOpenQuantity(rs.getInt("openquantity"));
                po.setSupplier(rs.getString("supplier"));
                po.setComment(rs.getString("comments"));
                po.setOriginalQuantity(rs.getInt("originalquantity"));
                po.setSuppliedQuantity(rs.getInt("suppliedquantity"));
                po.setFreeText1(rs.getString("freetext1"));
                po.setFreeText2(rs.getString("freetext2"));
                po.setFreeNumber1(rs.getBigDecimal("freenumber1"));
                po.setFreeNumber2(rs.getBigDecimal("freenumber2"));
                po.setOrderTypeNumber(rs.getInt("ordertypenumber"));
                po.setLine(rs.getInt("line"));
                po.setExcludeSetting(rs.getInt("excludesetting"));
                po.setExcludeDate(rs.getDate("excludedate"));
                po.setExcludeFromAM(rs.getInt("excludefromam"));
                po.setSupplierNumber(rs.getString("suppliernumber"));
                po.setSupplierName(rs.getString("suppliername"));
                purchase.add(po);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(purchase.size()>0){
            try {
                return purchaseOrderInsert(purchase);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PurchaseOrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            tituloResp = "Error";
            mensajeResp = "La tabla purchaseOrder está vacía o hay inconvenientes de columnas";
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
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
    
    public int emptyTable(Statement stmt){
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_PurchaseOrder]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ImportLogisticsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
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
