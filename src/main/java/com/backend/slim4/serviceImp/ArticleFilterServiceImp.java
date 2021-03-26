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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class ArticleFilterServiceImp implements ArticleFilterService{
    
    
    @Override
    public ArrayList<ArticleFilter> articleFilterSelect() {
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
        return articles;
        
    }
    
     public ArrayList<ArticleFilter> articleFilterInsert(ArrayList<ArticleFilter> a) throws ClassNotFoundException {
        ArrayList<ArticleFilter> articles = new ArrayList<>();
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_ArticleFilter](controlId, warehouse, code) SELECT * FROM  (VALUES";
            for (int i = 0; i < a.size(); i++) {
                sql =  (i==a.size()-1)? 
                            sql + "(" + a.get(i).getControlId() + ", '"+ a.get(i).getWarehousecode() +"', '" + a.get(i).getCode() + "')) AS temporal(controlId, warehouse, code)"
                            :
                            sql + "(" + a.get(i).getControlId() + ", '"+ a.get(i).getWarehousecode() +"', '" + a.get(i).getCode() + "'),";
            }
            
            ResultSet rs = stmt.executeQuery("select * from [slim4interface_test].[dbo].[S4Import_ArticleFilter]");
            while (rs.next()) {
                ArticleFilter ar = new ArticleFilter();
                ar.setControlId(rs.getInt("controlId"));
                ar.setWarehousecode(rs.getString("warehouse"));
                ar.setCode(rs.getString("code"));
                articles.add(ar);
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ArticleFilterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articles;
        
    }
    
    
}