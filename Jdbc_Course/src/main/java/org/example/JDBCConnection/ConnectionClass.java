package org.example.JDBCConnection;
import java.sql.*;

public class ConnectionClass {
    public static Connection getDriverConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user="postgres";
            String password="Test@123";

            return DriverManager.getConnection(url,user,password);
        }catch (Exception E){
            System.out.println("Exception happened "+E.getStackTrace());
            return null;
        }
    }
}
