package org.example.JDBC;
import java.sql.*;
public class JDBC01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //register driver
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user="postgres";
        String password="Test@123";

        //establish connection
        Connection conect=DriverManager.getConnection(url,user,password);

        Statement statment=conect.createStatement();
    }
}
