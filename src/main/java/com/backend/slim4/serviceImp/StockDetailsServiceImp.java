
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ImportControl;
import com.backend.slim4.model.StockDetails;
import com.backend.slim4.service.ImportControlService;
import com.backend.slim4.service.StockDetailsService;
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
public class StockDetailsServiceImp implements StockDetailsService {
    @Autowired
    ImportControlService control_service;
    // Variable límite de registros
    private static final int REGISTROS_BATCH = 1000;
    // ID de la interface
    private static final int importType = 0;
    @Override
    public ResponseEntity stockDetailsSelect() {
    // Mensaje de respuesta
        String tituloResp  = "";
        String mensajeResp = "";
        // Prepare Stament para inserción en Sql Server, se insertará por bloques.
        String sqlPrepare = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_StockDetails]"
                + "("
                + "controlId,"
                + "warehouse,"
                + "code,"
                + "stockOnHand,"
                + "stockID,"
                + "stockType,"
                + "excludeSetting,"
                + "excludeTillDate,"
                + "excludeFromDate,"
                + "initialShelfLife,"
                + "remainingShelfLife,"
                + "uD1,"
                + "uD2,"
                + "uD3,"
                + "uD4"
                + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                    + "stockonhand,"
                    + "TRIM(stockid) as stockid,"
                    + "TRIM(stocktype) as stocktype,"
                    + "excludesetting,"
                    + "excludetilldate,"
                    + "excludefromdate,"
                    + "initialshelflife,"
                    + "remainingshelflife,"
                    + "TRIM(ud1) as ud1,"
                    + "TRIM(ud2) as ud2,"
                    + "TRIM(ud3) as ud3,"
                    + "ud4 "
                    + "from stockdetails";
            System.out.print("\n ------------------------------STOCKDETAILS------------------------------ \n");
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
                        pstmt.setInt(4, rs.getInt("stockonhand"));
                        pstmt.setString(5, rs.getString("stockid"));
                        pstmt.setString(6, rs.getString("stocktype"));
                        pstmt.setInt(7, rs.getInt("excludesetting"));
                        pstmt.setString(8, getFormatoFecha(rs.getString("excludetilldate")));
                        pstmt.setString(9, getFormatoFecha(rs.getString("excludefromdate")));
                        pstmt.setBigDecimal(10, rs.getBigDecimal("initialshelflife"));
                        pstmt.setBigDecimal(11, rs.getBigDecimal("remainingshelflife"));
                        pstmt.setString(12, rs.getString("ud1"));
                        pstmt.setString(13, rs.getString("ud2"));
                        pstmt.setString(14, rs.getString("ud3"));
                        pstmt.setString(15, rs.getString("ud4"));
                        
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
                        System.out.print("\n ------------------------------STOCKDETAILS------------------------------ \n");
                        tituloResp = "Éxito";
                        mensajeResp = "se ejecutó la interface StockDetails correctamente!";
                        }else{
                            tituloResp = "Error";
                            mensajeResp = "Hubo problemas al eliminar la información de Sql Server previo a la inserción";
                        }
               
            } catch (ParseException ex) {
                Logger.getLogger(StockDetailsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(StockDetailsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockDetailsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(StockDetailsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
    
    public int emptyTable(Statement stmt){
        System.out.print("\n-Eliminando registros de Sql Server" + "\n");
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_StockDetails]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StockDetailsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
     public int insertDataTable(Statement stmt, ArrayList<StockDetails> t) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_StockDetails]"
                + "("
                + "controlId,"
                + "warehouse,"
                + "code,"
                + "stockOnHand,"
                + "stockID,"
                + "stockType,"
                + "excludeSetting,"
                + "excludeTillDate,"
                + "excludeFromDate,"
                + "initialShelfLife,"
                + "remainingShelfLife,"
                + "uD1,"
                + "uD2,"
                + "uD3,"
                + "uD4"
                
                + ") "
                + " SELECT * FROM  (VALUES";
            for (int i = 0; i < t.size(); i++) {
                sql =  (i==t.size()-1)? 
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getStockOnHand()+ "', "
                        + "'" + t.get(i).getStockID()+ "', "
                        + "'" + t.get(i).getStockType()+ "', "
                        + "'" + t.get(i).getExcludeSetting()+ "', "
                        + "'" + t.get(i).getExcludeTillDate()+ "', "
                        + "'" + t.get(i).getExcludeFromDate()+ "', "
                        + ""  + t.get(i).getInitialShelfLife()+ ", "
                        + ""  + t.get(i).getRemainingShelfLife()+ ", "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "' "
                        + ")) as temporal"
                        + "("
                            + "controlId,"
                            + "warehouse,"
                            + "code,"
                            + "stockOnHand,"
                            + "stockID,"
                            + "stockType,"
                            + "excludeSetting,"
                            + "excludeTillDate,"
                            + "excludeFromDate,"
                            + "initialShelfLife,"
                            + "remainingShelfLife,"
                            + "uD1,"
                            + "uD2,"
                            + "uD3,"
                            + "uD4"
                            + ")"
                :
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getStockOnHand()+ "', "
                        + "'" + t.get(i).getStockID()+ "', "
                        + "'" + t.get(i).getStockType()+ "', "
                        + "'" + t.get(i).getExcludeSetting()+ "', "
                        + "'" + t.get(i).getExcludeTillDate()+ "', "
                        + "'" + t.get(i).getExcludeFromDate()+ "', "
                        + ""  + t.get(i).getInitialShelfLife()+ ", "
                        + ""  + t.get(i).getRemainingShelfLife()+ ", "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "'), ";
                       
                        
            }
        return stmt.executeUpdate(sql);
    } 
}
