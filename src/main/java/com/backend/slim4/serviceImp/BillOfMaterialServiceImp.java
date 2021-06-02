
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.BillOfMaterial;
import com.backend.slim4.model.ImportControl;
import com.backend.slim4.service.BillOfMaterialService;
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
public class BillOfMaterialServiceImp implements BillOfMaterialService {
    // Servicio de ImportControl para notificar cuando la interface ha sido importada con éxito
    @Autowired
    ImportControlService control_service;
    // Variable límite de registros
    private static final int REGISTROS_BATCH = 1000;
    // ID de la interface
    private static final int importType = 7;
    @Override
    public ResponseEntity billOfMaterialSelect() {
        // Mensaje de respuesta
        String tituloResp  = "";
        String mensajeResp = "";
        // Prepare Stament para inserción en Sql Server, se insertará por bloques.
        String sqlPrepare = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_BillOfMaterial]"
                + "("
                    + "controlId,"
                    + "articleCode,"
                    + "componentArticleCode,"
                    + "quantity,"
                    + "bomID,"
                    + "lineNumber,"
                    + "leadTime,"
                    + "fromDate,"
                    + "toDate,"
                    + "bomType,"
                    + "exceptionLevel,"
                    + "warehouse,"
                    + "componentWarehouse"
                + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                    + "TRIM(articlecode) as articlecode,"
                    + "TRIM(componentarticlecode) as componentarticlecode,"
                    + "quantity,"
                    + "bomid,"
                    + "TRIM(linenumber) as linenumber,"
                    + "leadtime,"
                    + "fromdate,"
                    + "todate,"
                    + "bomtype,"
                    + "exceptionlevel,"
                    + "warehouse,"
                    + "componentwarehouse "
                    + "from billofmaterial";
            System.out.print("\n ------------------------------BILLOFMATERIAL------------------------------ \n");
            System.out.print("\n-Trayendo información de la base de datos Informix \n");
            boolean test = false;
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
                        pstmt.setString(2, rs.getString("articlecode"));
                        pstmt.setString(3, rs.getString("componentarticlecode"));
                        pstmt.setBigDecimal(4, rs.getBigDecimal("quantity"));
                        pstmt.setString(5, rs.getString("bomid"));
                        pstmt.setString(6, rs.getString("linenumber"));
                        pstmt.setBigDecimal(7, rs.getBigDecimal("leadtime"));
                        pstmt.setString(8, rs.getString("fromdate"));
                        pstmt.setString(9, rs.getString("todate"));
                        pstmt.setString(10, rs.getString("bomtype"));
                        pstmt.setInt(11, rs.getInt("exceptionlevel"));
                        pstmt.setString(12, rs.getString("warehouse"));
                        pstmt.setString(13, rs.getString("componentwarehouse"));
                        
                        pstmt.addBatch();
                        counter++;
                         if (counter == REGISTROS_BATCH) {
                             pstmt.executeBatch();
                             test = true;
                             counter = 0;
                         }
                         

                         }
                         //revisamos si todavía hay sentencias pendientes de ejecutar
                         if (counter > 0) {
                             pstmt.executeBatch();
                         }
                            ImportControl control = new ImportControl();
                            control.setControlID(1);
                            control.setControlStatus(1);
                            control.setImportType(importType);
                            control_service.insert(stmt2, importType, control);
                         System.out.print("\n Proceso finalizado! \n");
                         System.out.print("\n ------------------------------BILLOFMATERIAL------------------------------ \n");
                         tituloResp = "Éxito";
                         mensajeResp = "se ejecutó la interface BillOfMaterial correctamente!";
                }else{
                    tituloResp = "Error";
                    mensajeResp = "Hubo problemas al eliminar la información de Sql Server previo a la inserción";
                   }
               
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(BillOfMaterialServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BillOfMaterialServiceImp.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ResponseEntity billOfMaterialInsert(ArrayList<BillOfMaterial> b) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,b);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface BillOfMaterial correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_BillOfMaterial previo a realizar la inserción.";
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BillOfMaterialServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
    
    public int emptyTable(Statement stmt){
        System.out.print("\n-Eliminando registros de Sql Server" + "\n");
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_BillOfMaterial]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BillOfMaterialServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
     public int insertDataTable(Statement stmt, ArrayList<BillOfMaterial> b) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_BillOfMaterial]"
                + "("
                    + "controlId,"
                    + "articleCode,"
                    + "componentArticleCode,"
                    + "quantity,"
                    + "bomID,"
                    + "lineNumber,"
                    + "leadTime,"
                    + "fromDate,"
                    + "toDate,"
                    + "bomType,"
                    + "exceptionLevel"
                + ") "
                + " SELECT * FROM  (VALUES";
            for (int i = 0; i < b.size(); i++) {
                sql =  (i==b.size()-1)? 
                        sql + "(" + b.get(i).getControlId() + ", "
                        + "'" + b.get(i).getCode()+ "', "
                        + "'" + b.get(i).getComponentArticleCode()+ "', "
                        + " " + b.get(i).getQuantity()+ ", "
                        + "'" + b.get(i).getBomID()+ "', "
                        + "'" + b.get(i).getLineNumber()+ "', "
                        + " " + b.get(i).getLeadTime()+ ","
                        + "'" + b.get(i).getFromDate()+ "',"
                        + "'" + b.get(i).getToDate()+ "',"
                        + "'" + b.get(i).getBomType()+ "',"
                        + "'" + b.get(i).getExceptionLevel()+ "'"
                        + ")) as temporal"
                + "("
                    + "controlId,"
                    + "articleCode,"
                    + "componentArticleCode,"
                    + "quantity,"
                    + "bomID,"
                    + "lineNumber,"
                    + "leadTime,"
                    + "fromDate,"
                    + "toDate,"
                    + "bomType,"
                    + "exceptionLevel"
                            + ")"
                :
                        sql + "(" + b.get(i).getControlId() + ", "
                        + "'" + b.get(i).getCode()+ "', "
                        + "'" + b.get(i).getComponentArticleCode()+ "', "
                        + " " + b.get(i).getQuantity()+ ", "
                        + "'" + b.get(i).getBomID()+ "', "
                        + "'" + b.get(i).getLineNumber()+ "', "
                        + " " + b.get(i).getLeadTime()+ ","
                        + "'" + b.get(i).getFromDate()+ "',"
                        + "'" + b.get(i).getToDate()+ "',"
                        + "'" + b.get(i).getBomType()+ "',"
                        + "'" + b.get(i).getExceptionLevel()+ "'), ";
                       
                        
            }
        System.out.print("Entre a insertar la info en Sql Server" + "\n" + "Query: \n" + sql + "\n");    
        return stmt.executeUpdate(sql);
    } 
}
