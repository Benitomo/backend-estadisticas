package com.backend.slim4.serviceImp;

import com.backend.slim4.model.ArticleFilter;
import com.backend.slim4.service.ArticleFilterService;
import com.google.gson.Gson;
import com.backend.slim4.GetConnection;
import java.sql.Connection;
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
    
    
    @Override
    public ResponseEntity articleFilterSelect() {
        String tituloResp  = "";
        String mensajeResp = "";
        ArrayList<ArticleFilter> articles = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT first 70000 controlid, TRIM(warehousecode) as warehousecode, TRIM(articlecode) as articlecode from articlefilter";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArticleFilter a = new ArticleFilter();
                a.setControlId(rs.getInt("controlid"));
                a.setWarehousecode(rs.getString("warehousecode"));
                a.setCode(rs.getString("articlecode"));
                articles.add(a);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(articles.size()>0){
            try {
                return articleFilterInsert(articles);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            tituloResp = "Error";
            mensajeResp = "La tabla articlefilter está vacía o hay inconvenientes de columnas";
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
        
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
     
    public int emptyTable(Statement stmt){
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_ArticleFilter]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 
    
    public int insertDataTable(Statement stmt, ArrayList<ArticleFilter> a) throws SQLException{
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