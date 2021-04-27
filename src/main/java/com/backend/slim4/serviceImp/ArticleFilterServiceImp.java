package com.backend.slim4.serviceImp;

import com.backend.slim4.model.ArticleFilter;
import com.backend.slim4.service.ArticleFilterService;
import com.google.gson.Gson;
import com.backend.slim4.GetConnection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArticleFilterServiceImp implements ArticleFilterService{
    
    private static final int REGISTROS_BATCH = 1000;
    @Override
    public ResponseEntity articleFilterSelect() {
        // Mensaje de respuesta
        String tituloResp  = "";
        String mensajeResp = "";
        
        // Prepare Stament para inserción en Sql Server, se insertará por bloques.
        String sqlPrepare = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_ArticleFilter](controlId, warehouse, code) VALUES (?, ?, ?)";
        
        try {
            // Informix
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            // Sql Server
            Connection cnt2 = GetConnection.sqlServer();
            Statement stmt2 = cnt2.createStatement();
            // Query que trae la información de Informix
            String sql = "SELECT first 183517 controlid, TRIM(warehousecode) as warehousecode, TRIM(articlecode) as articlecode from articlefilter";
            
            System.out.print("\n Entré a ejecutar query select en informix \n");
            
            try (PreparedStatement pstmt = cnt2.prepareStatement(sqlPrepare)) {
            // Ejecutamos el query que trae la información de Informix    
            ResultSet rs = stmt.executeQuery(sql);
            //String sql2 = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_ArticleFilter](controlId, warehouse, code) SELECT * FROM  (VALUES";
            
            // Contador que nos permite saber cuando llegamos al límite de inserción
            int counter = 0;
            while (rs.next()) {
                
                ArticleFilter article = new ArticleFilter();
                
                article.setControlId(rs.getInt(1));
                article.setWarehousecode(rs.getString(2));
                article.setCode(rs.getString(3));
                
                pstmt.setInt(1, article.getControlId());
                pstmt.setString(2, article.getWarehousecode());
                pstmt.setString(3, article.getCode());
                
                pstmt.addBatch();
                counter++;
                if (counter == REGISTROS_BATCH) {
                    pstmt.executeBatch();
                    counter = 0;
                }
                //sql2 = sql2.substring(0, sql2.length()-1);
                //sql2 += ") as temporal(controlId,warehouse,code)";
                //escritura(sql2);
                //int r = emptyTable(stmt2);
                //insertData(stmt2,sql2);
            }
            
            //revisamos si todavía hay sentencias pendientes de ejecutar
            if (counter > 0) {
                pstmt.executeBatch();
            }
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface ArticleFilter correctamente!";
                //sql2 +=  "(" + rs.getInt("controlid") + ", " + getStringOrNull(rs.getString("warehousecode")) + ", " + getStringOrNull(rs.getString("articlecode")) + "),";
            }
           
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
        
    }
    
    public String getStringOrNull(String palabra){
        if(palabra == null)
            return null;
        else return "'"+palabra+"'";
        
    }
    
     public ResponseEntity articleFilterInsert(ArrayList<ArticleFilter> a) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = ""; 
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,a);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface ArticleFilter correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_ArticleFilter previo a realizar la inserción.";
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
     
      public void escritura (String sql){
          System.out.print("\n Entré a guardar el sql en el archivo txt \n");
        try {
            String ruta = "C:/Users/USUARIO/Desktop/sql.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sql);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public int emptyTable(Statement stmt){
        System.out.print("\n Entré a ejecutar query delete en Sql Server \n");
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_ArticleFilter]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
    public void insertData(Statement stmt, String sqlInsert){
        try {
            System.out.print("\n Entré a ejecutar query inserción en Sql Server \n");
            stmt.executeUpdate(sqlInsert);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public int insertDataTable(Statement stmt, ArrayList<ArticleFilter> a) throws SQLException{
        System.out.print("\n Entré a insertar articlefilter en Sql Server \n");
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_ArticleFilter](controlId, warehouse, code) SELECT * FROM  (VALUES";
            for (int i = 0; i < a.size(); i++) {
                sql =  (i==a.size()-1)? 
                            sql + "(" + a.get(i).getControlId() + ", '"+ a.get(i).getWarehousecode() +"', '" + a.get(i).getCode() + "')) AS article(controlId, warehouse, code)"
                            :
                            sql + "(" + a.get(i).getControlId() + ", '"+ a.get(i).getWarehousecode() +"', '" + a.get(i).getCode() + "'),";
            }
        return stmt.executeUpdate(sql);
    } 
    
    public ArrayList<ArticleFilter> getArticleFilterInfo(Statement stmt) throws SQLException{
        ArrayList<ArticleFilter> articles = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select * from [slim4interface_test].[dbo].[S4Import_ArticleFilter]");
            while (rs.next()) {
                ArticleFilter ar = new ArticleFilter();
                ar.setControlId(rs.getInt("controlId"));
                ar.setWarehousecode(rs.getString("warehouse"));
                ar.setCode(rs.getString("code"));
                articles.add(ar);
            }
        return articles;    
    }
            
    
    
}