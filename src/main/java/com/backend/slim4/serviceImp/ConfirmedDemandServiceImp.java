
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ConfirmedDemand;
import com.backend.slim4.service.ConfirmedDemandService;
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
public class ConfirmedDemandServiceImp implements ConfirmedDemandService{
    @Override
    public ResponseEntity confirmedDemandSelect() {
    String tituloResp  = "";
    String mensajeResp = "";
    ArrayList<ConfirmedDemand> confirmed = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT * from confirmeddemand";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ConfirmedDemand po = new ConfirmedDemand();
                po.setControlId(rs.getInt("controlid"));
                po.setWarehousecode(rs.getString("warehousecode"));
                po.setCode(rs.getString("articlecode"));
                po.setNumber(rs.getString("numbers"));
                po.setRequiredDate(rs.getDate("requereddate"));
                po.setRequiredQuantity(rs.getInt("requiredquantity"));
                po.setExceptionLevel(rs.getInt("exceptionlevel"));
                po.setOrderType(rs.getString("ordertype"));
                po.setCustomerDetails(rs.getString("customerdetails"));
                po.setProductionDetails(rs.getString("productiondetails"));
                po.setComment(rs.getString("comments"));
                po.setOriginalQuantity(rs.getInt("originalquantity"));
                po.setSuppliedQuantity(rs.getInt("suppliedquantity"));
                po.setFreeText1(rs.getString("freetext1"));
                po.setFreeText2(rs.getString("freetext2"));
                po.setFreeNumber1(rs.getBigDecimal("freenumber1"));
                po.setFreeNumber2(rs.getBigDecimal("freenumber2"));
                po.setType(rs.getInt("types"));
                po.setLine(rs.getInt("line"));
                confirmed.add(po);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(confirmed.size()>0){
            try {
                return confirmedInsert(confirmed);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            tituloResp = "Error";
            mensajeResp = "La tabla confirmeddemand está vacía o hay inconvenientes de columnas";
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }
    
     public ResponseEntity confirmedInsert(ArrayList<ConfirmedDemand> t) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,t);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface ConfirmedDemand correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_ConfirmedDemand previo a realizar la inserción.";
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
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_ConfirmedDemand]";
        System.out.print("\n Entré a eliminar info de Sql Server previo a insertar");
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
    public int insertDataTable(Statement stmt, ArrayList<ConfirmedDemand> t) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_ConfirmedDemand]"
                + "("
                + "controlId,"
                + "warehouse,"
                + "code,"
                + "number, "
                + "requiredDate,"
                + "requiredQuantity,"
                + "exceptionLevel,"
                + "orderType,"
                + "customerDetails,"
                + "productionDetails,"
                + "comment,"
                + "originalQuantity,"
                + "suppliedQuantity,"
                + "freeText1,"
                + "freeText2,"
                + "freeNumber1,"
                + "freeNumber2,"
                + "type,"
                + "line"
                + ")"
                + "SELECT * FROM  (VALUES";
            for (int i = 0; i < t.size(); i++) {
                sql =  (i==t.size()-1)? 
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getWarehousecode()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getNumber()+ "', "
                        + "'" + t.get(i).getRequiredDate()+ "', "
                        + ""  + t.get(i).getRequiredQuantity()+ ", "
                        + ""  + t.get(i).getExceptionLevel()+ ", "
                        + "'" + t.get(i).getOrderType()+ "', "
                        + "'" + t.get(i).getCustomerDetails()+ "', "
                        + "'" + t.get(i).getProductionDetails()+ "', "
                        + "'" + t.get(i).getComment()+ "', "
                        + ""  + t.get(i).getOriginalQuantity()+ ", "
                        + ""  + t.get(i).getSuppliedQuantity()+ ", "
                        + "'" + t.get(i).getFreeText1()+ "', "
                        + "'" + t.get(i).getFreeText2()+ "', "
                        + ""  + t.get(i).getFreeNumber1()+ ", "
                        + ""  + t.get(i).getFreeNumber2()+ ", "
                        + "" + t.get(i).getType()+ ", "
                        + ""  + t.get(i).getLine()+ ""
                        + ")) as temporal"
                        + "("
                            + "controlId,"
                            + "warehouse,"
                            + "code,"
                            + "number, "
                            + "requiredDate,"
                            + "requiredQuantity,"
                            + "exceptionLevel,"
                            + "orderType,"
                            + "customerDetails,"
                            + "productionDetails,"
                            + "comment,"
                            + "originalQuantity,"
                            + "suppliedQuantity,"
                            + "freeText1,"
                            + "freeText2,"
                            + "freeNumber1,"
                            + "freeNumber2,"
                            + "type,"
                            + "line"
                        + ")"
                :
                         sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getWarehousecode()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getNumber()+ "', "
                        + "'" + t.get(i).getRequiredDate()+ "', "
                        + ""  + t.get(i).getRequiredQuantity()+ ", "
                        + ""  + t.get(i).getExceptionLevel()+ ", "
                        + "'" + t.get(i).getOrderType()+ "', "
                        + "'" + t.get(i).getCustomerDetails()+ "', "
                        + "'" + t.get(i).getProductionDetails()+ "', "
                        + "'" + t.get(i).getComment()+ "', "
                        + ""  + t.get(i).getOriginalQuantity()+ ", "
                        + ""  + t.get(i).getSuppliedQuantity()+ ", "
                        + "'" + t.get(i).getFreeText1()+ "', "
                        + "'" + t.get(i).getFreeText2()+ "', "
                        + ""  + t.get(i).getFreeNumber1()+ ", "
                        + ""  + t.get(i).getFreeNumber2()+ ", "
                        + "" + t.get(i).getType()+ ", "
                        + ""  + t.get(i).getLine()+ "),";
                        
            }
            
        System.out.print("\n Entré a insertar la info de Informix a Sql Server \n Query: \n" + sql + "\n");
        return stmt.executeUpdate(sql);
    } 
}
