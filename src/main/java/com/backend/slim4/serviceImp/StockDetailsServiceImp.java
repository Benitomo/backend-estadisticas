
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.StockDetails;
import com.backend.slim4.service.StockDetailsService;
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
public class StockDetailsServiceImp implements StockDetailsService {
    @Override
    public ResponseEntity stockDetailsSelect() {
    String tituloResp  = "";
    String mensajeResp = "";
    ArrayList<StockDetails> stock = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT first 1000"
                    + "controlid,"
                    + "TRIM(warehouse) as warehouse,"
                    + "TRIM(articlecode) as articlecode,"
                    + "ud1,"
                    + "ud2,"
                    + "ud3,"
                    + "ud4,"
                    + "from stockdetails";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                StockDetails t = new StockDetails();
                t.setControlId(rs.getInt("controlid"));
                
                t.setWarehouse(rs.getString("warehouse"));
                t.setCode(rs.getString("articlecode"));
               
                t.setuD1(rs.getString("ud1"));
                t.setuD1(rs.getString("ud2"));
                t.setuD1(rs.getString("ud3"));
                t.setuD1(rs.getString("ud4"));
                stock.add(t);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(stock.size()>0){
            try {
                return stockDetailsInsert(stock);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            tituloResp = "Error";
            mensajeResp = "La tabla stockdetails está vacía o hay inconvenientes de columnas";
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }
    
    public ResponseEntity stockDetailsInsert(ArrayList<StockDetails> t) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,t);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface StockDetails correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_StockDetails previo a realizar la inserción.";
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
    
    public int emptyTable(Statement stmt){
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_StockDetails]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
     public int insertDataTable(Statement stmt, ArrayList<StockDetails> t) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_StockDetails]"
                + "("
                + "controlId,"
               
                + "warehouse,"
                + "code,"
               
                + "uD1,"
                + "uD2,"
                + "uD3,"
                + "uD4,"
                
                + ")"
                + "SELECT * FROM  (VALUES";
            for (int i = 0; i < t.size(); i++) {
                sql =  (i==t.size()-1)? 
                        sql + "(" + t.get(i).getControlId() + ", "
                        
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', "
                        + ")) as temporal"
                        + "("
                            + "controlId,"
                           
                            + "warehouse,"
                            + "code,"
                            
                            + "uD1,"
                            + "uD2,"
                            + "uD3,"
                            + "uD4,"
                           
                            + ")"
                :
                        sql + "(" + t.get(i).getControlId() + ", "
                        
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                       
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', ";
                       
                        
            }
        return stmt.executeUpdate(sql);
    } 
}
