package org.example.JDBC;
import org.example.JDBCConnection.ConnectionClass;

import java.sql.*;
import java.util.Scanner;

public class JDBC02 {
    public static void main(String[] args) {
//        insertUsingPreoairedStatement();
        updatingUsingPreparedStatement();
    }
    public static void insertUsingPreoairedStatement(){
        try{
            Class.forName("org.postgresql.Driver");
            PreparedStatement statement=null;
            Connection connect=null;
            try{
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String user="postgres";
                String password="Test@123";

                connect=DriverManager.getConnection(url,user,password);

                String sql="insert into studentInfo values (?,?,?,?)";
                statement=connect.prepareStatement(sql);

                statement.setInt(1,2);
                statement.setString(2,"example");
                statement.setInt(3,4);
                statement.setString(4,"city");

                int rowaffected=statement.executeUpdate();

                if(rowaffected==0){
                    System.out.println("failled inserting");
                }else{
                    System.out.println("inserted successfully");
                }

            }catch (SQLException E){
                E.printStackTrace();
            }
            finally {
                statement.close();
                connect.close();
            }

        }catch (ClassNotFoundException | SQLException E){
            E.printStackTrace();
        }finally {
            System.out.println("proccess finshed");
        }
    }

    public static void updatingUsingPreparedStatement(){
        try(Connection connection= ConnectionClass.getDriverConnection()){
            ResultSet usersResult=getUserDetails();
            Scanner scanner=new Scanner(System.in);
            while (usersResult.next()){
                System.out.println("name "+usersResult.getString("name"));
                int id=usersResult.getInt(1);
                String name=usersResult.getString(2);

                System.out.print("Enter the  updating name");
                String updatename=scanner.nextLine();

                if(name.equals(updatename)){
                    System.out.println("Cannot update same name exiting");
                }else{
                    String sql="update studentInfo set name=(?) where id=(?)";
                    PreparedStatement state=connection.prepareStatement(sql);

                    state.setString(1,updatename);
                    state.setInt(2,id);
                    int affecctedRow=state.executeUpdate();

                    if(affecctedRow==0){
                        System.out.println("failled to update");
                    }else{
                        System.out.println("updated success");
                    }
                }

            }
        }catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static ResultSet getUserDetails(){
        try(Connection connection= ConnectionClass.getDriverConnection()){
            String sql="select * from studentInfo";
            PreparedStatement state=connection.prepareStatement(sql);

            return state.executeQuery();
        }catch(Exception E){
            return null;
        }
    }
}
