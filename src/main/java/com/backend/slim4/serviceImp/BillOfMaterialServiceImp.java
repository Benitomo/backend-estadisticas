
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.BillOfMaterial;
import com.backend.slim4.service.BillOfMaterialService;
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
public class BillOfMaterialServiceImp implements BillOfMaterialService {
    @Override
    public ResponseEntity billOfMaterialSelect() {
    String tituloResp  = "";
    String mensajeResp = "";
    ArrayList<BillOfMaterial> bill = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT first 1000 "
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
                    + "exceptionlevel "
                    + "from billofmaterial";
            System.out.print("Entre a traer la info de Informix" + "\n" + "Query: \n" + sql + "\n");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BillOfMaterial b = new BillOfMaterial();
                b.setControlId(rs.getInt("controlid"));
                b.setCode(rs.getString("articlecode"));
                b.setComponentArticleCode(rs.getString("componentarticlecode"));
                b.setQuantity(rs.getBigDecimal("quantity"));
                b.setBomID(rs.getString("bomid"));
                b.setLineNumber(rs.getString("linenumber"));
                b.setLeadTime(rs.getBigDecimal("leadtime"));
                b.setFromDate(rs.getDate("fromdate"));
                b.setToDate(rs.getDate("todate"));
                b.setBomType(rs.getString("bomtype"));
                b.setExceptionLevel(rs.getInt("exceptionlevel"));
                bill.add(b);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(BillOfMaterialServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(bill.size()>0){
            try {
                return billOfMaterialInsert(bill);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            tituloResp = "Error";
            mensajeResp = "La tabla billofmaterial está vacía o hay inconvenientes de columnas";
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
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
        System.out.print("Entre a eliminar la info de Sql Server" + "\n");
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
