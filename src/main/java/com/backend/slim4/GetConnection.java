/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.slim4;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class GetConnection {
    public static Connection informix(String bd) {
        return getIfxConnection(bd);
    }
    public static Connection sqlServer() throws ClassNotFoundException {
        return getSqlServerConnection();
    }

    public static Connection getIfxConnection(String bd) {
        try {

            Class.forName("com.informix.jdbc.IfxDriver");
            Connection cnt = (Connection) DriverManager.getConnection("jdbc:informix-sqli://10.142.0.2:1527/" + bd + ":" +
                    "INFORMIXSERVER=btomo_tcp;user=informix;password=todayic");

            //cnt.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            //cnt.setAutoCommit(false);

            return cnt;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static Connection getSqlServerConnection() throws ClassNotFoundException{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=slim4;user=sa;password=todayic.2021;encrypt=true;trustServerCertificate=true"; 
            Connection cnt = DriverManager.getConnection(connectionUrl);
            return cnt;
        } 
        catch (SQLException ex) 
          {
            System.out.println("Error." + ex.getMessage());
          }
        
        return null;
    }

}
