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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArticleFilterServiceImp implements ArticleFilterService{
    
    
    @Override
    public ResponseEntity articleFilterSelect() {
        ArrayList<ArticleFilter> articles = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT controlid, TRIM(warehousecode) as warehousecode, TRIM(articlecode) as articlecode from articlefilter";
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
            
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put("Error", "No se pudo traer información de la base de datos Informix");
        return new ResponseEntity(map, HttpStatus.CONFLICT);
        
    }
    
     public ResponseEntity articleFilterInsert(ArrayList<ArticleFilter> a) throws ClassNotFoundException {
        ArrayList<ArticleFilter> articles = new ArrayList<>();
        Gson g = new Gson();
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            emptyTable(stmt);
            insertDataTable(stmt,a);
            articles = getArticleFilterInfo(stmt);
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put("Informix:", " se obtuvo la información correctamente!");
        map.put("Sql Server:", " se insertó la interface correctamente!");
        map.put("Datos de S4Import_ArticleFilter en Sql Server:", g.toJson(articles).replaceAll("/", ""));
        return new ResponseEntity(map, HttpStatus.OK);
        
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
                            sql + "(" + a.get(i).getControlId() + ", '"+ a.get(i).getWarehousecode() +"', '" + a.get(i).getCode() + "')) AS temporal(controlId, warehouse, code)"
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