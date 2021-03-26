package com.backend.slim4.serviceImp;

import com.backend.slim4.model.ArticleFilter;
import com.backend.slim4.service.InterfacesSlim4Service;
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
public class InterfacesSlim4ServiceImp implements InterfacesSlim4Service{
    
    
    @Override
    public ArrayList<ArticleFilter> articleFilter() {
        Gson g = new Gson();
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
            Logger.getLogger(InterfacesSlim4ServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(articles.size()>0){
            try {
                return articleFilterInsert(articles);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InterfacesSlim4ServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return articles;
        
    }
    @Override
    public ArrayList<ArticleFilter> test() {
        ArrayList<ArticleFilter> articles = new ArrayList<>();
        try {
            Connection cnt = GetConnection.getSqlServerConnection();
            Statement stmt = cnt.createStatement();
            String sql = "SELECT * from articlefilter";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.print(rs);
            while (rs.next()) {
                ArticleFilter ar = new ArticleFilter();
                ar.setControlId(rs.getInt("controlId"));
                ar.setWarehousecode(rs.getString("warehousecode"));
                ar.setCode(rs.getString("code"));
                articles.add(ar);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(InterfacesSlim4ServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InterfacesSlim4ServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articles;
        
    }
    
     public ArrayList<ArticleFilter> articleFilterInsert(ArrayList<ArticleFilter> a) throws ClassNotFoundException {
       ArrayList<ArticleFilter> articles = new ArrayList<>();
        try {
            Connection cnt = GetConnection.getSqlServerConnection();
            Statement stmt = cnt.createStatement();
            String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_ArticleFilter](controlId, warehouse, code) SELECT * FROM  (VALUES";
            for (int i = 0; i < a.size(); i++) {
                sql=(i==a.size()-1)? 
                        sql + "(" + a.get(i).getControlId() + ", '"+ a.get(i).getWarehousecode() +"', '" + a.get(i).getCode() + "')) AS temp(controlId, warehouse, code)"
                        :
                        sql + "(" + a.get(i).getControlId() + ", '"+ a.get(i).getWarehousecode() +"', '" + a.get(i).getCode() + "'),";
            }
           
            int inserto = stmt.executeUpdate(sql);
            System.out.print( "\n Query inserccion:  " + sql + " \n");
            
            ResultSet rs = stmt.executeQuery("select * from [slim4interface_test].[dbo].[S4Import_ArticleFilter]");
            while (rs.next()) {
                ArticleFilter ar = new ArticleFilter();
                ar.setControlId(rs.getInt("controlId"));
                ar.setWarehousecode(rs.getString("warehouse"));
                ar.setCode(rs.getString("code"));
                articles.add(ar);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(InterfacesSlim4ServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InterfacesSlim4ServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articles;
        
    }
    
    
}