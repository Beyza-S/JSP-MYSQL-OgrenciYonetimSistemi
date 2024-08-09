
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Veritabani {
   
    
    private static final String URL="jdbc:mysql://localhost:3306/harikalarKoleji_db";
    private static final String USER="root";
    private static final String PASSWORD ="root";
    
  
    
    public static Connection getConnection() throws SQLException{
        try{
              Class.forName("com.mysql.jdbc.Driver");
              return DriverManager.getConnection(URL,USER,PASSWORD);
              
        }catch (Exception e) {
                e.printStackTrace();
         }
        return null;

    }}