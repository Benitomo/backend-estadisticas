
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.Suppliers;
import com.backend.slim4.service.SuppliersService;
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
public class SuppliersServiceImp implements SuppliersService{
    @Override
    public ResponseEntity suppliersSelect() {
    String tituloResp  = "";
    String mensajeResp = "";
    ArrayList<Suppliers> suppliers = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT "
                    + "controlid,"
                    + "TRIM(warehousecode) as warehousecode,"
                    + "TRIM(articlecode) as articlecode,"
                    + "suppliernumber,"
                    + "suppliername,"
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
                    + "supplierarticlecode,"
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
                    + "ud1,"
                    + "ud2,"
                    + "ud3,"
                    + "ud4,"
                    + "ud5 "
                    + "from suppliers";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Suppliers t = new Suppliers();
                t.setControlId(rs.getInt("controlid"));
                t.setWarehouse(rs.getString("warehousecode"));
                t.setCode(rs.getString("articlecode"));
                t.setSupplierNumber(rs.getString("suppliernumber"));
                t.setSupplierName(rs.getString("suppliername"));
                t.setPrimarySupplier(rs.getInt("primarysupplier"));
                t.setPreference(rs.getInt("preference"));
                t.setLeadTime(rs.getBigDecimal("leadtime"));
                t.setReviewTime(rs.getBigDecimal("reviewtime"));
                t.setBuyingPrice(rs.getBigDecimal("buyingprice"));
                t.setCurrencyCode(rs.getString("currencycode"));
                t.setMOQ(rs.getInt("moq"));
                t.setIOQ(rs.getInt("ioq"));
                t.setEOQ(rs.getInt("eoq"));
                t.setSupplierReliability(rs.getBigDecimal("supplierreliability"));
                t.setSupplierRealibilityType(rs.getInt("supplierreliabilitytype"));
                t.setSupplierArticleCode(rs.getString("supplierarticlecode"));
                t.setAvailableInventory(rs.getInt("availableinventory"));
                t.setDesiredSplit(rs.getBigDecimal("desiredsplit"));
                t.setSuppliedQuantity(rs.getInt("suppliedquantity"));
                t.setOrderFromDate(rs.getDate("orderfromdate"));
                t.setOrderFromDate(rs.getDate("ordertodate"));
                t.setLogisticUnit1(rs.getInt("logisticUnit1"));
                t.setLogisticUnit2(rs.getInt("logisticUnit2"));
                t.setLogisticUnit3(rs.getInt("logisticUnit3"));
                t.setLogisticUnit4(rs.getInt("logisticUnit4"));
                t.setLogisticUnit5(rs.getInt("logisticUnit5"));
                t.setLogisticUnit6(rs.getInt("logisticUnit6"));
                t.setuD1(rs.getString("ud1"));
                t.setuD2(rs.getString("ud2"));
                t.setuD3(rs.getString("ud3"));
                t.setuD4(rs.getString("ud4"));
                t.setuD5(rs.getString("ud5"));
                suppliers.add(t);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(suppliers.size()>0){
            try {
                return suppliersInsert(suppliers);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            tituloResp = "Error";
            mensajeResp = "La tabla suppliers está vacía o hay inconvenientes de columnas";
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
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
    
    public int emptyTable(Statement stmt){
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_Suppliers]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SuppliersServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
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
