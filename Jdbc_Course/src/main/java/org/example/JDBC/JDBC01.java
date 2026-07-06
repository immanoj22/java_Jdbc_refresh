package org.example.JDBC;
import java.sql.*;
public class JDBC01 {
    public static void main(String[] args) throws SQLException {
//        StateInsert();
//        UpdateStatement();
//        retriveDate();
//        deleteData();
        CRUD_Operation();
    }

    public static void StateInsert() throws SQLException {

        Connection conect=null;
        Statement statment=null;
        try{
            //register driver
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user="postgres";
            String password="Test@123";

            //establish connection
            conect=DriverManager.getConnection(url,user,password);

            //create statement
            statment=conect.createStatement();

            //execute query
            String sql="INSERT INTO studentInfo(id,name,age,city) VALUES (2,'ani',1,'mdu')";
            int result=statment.executeUpdate(sql);
            //process result
            if(result==0){
                System.out.println("no rows affected");
            }else{
                System.out.println("process success");
            }


        }catch(Exception E){
            E.getStackTrace();
        }finally {
            //close connection
            statment.close();
            conect.close();
        }

    }

    public static void  UpdateStatement ()throws SQLException{
        Connection connect=null;
        Statement statement=null;
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user="postgres";
            String password="Test@123";

            connect=DriverManager.getConnection(url,user,password);

            statement=connect.createStatement();

            String sql="UPDATE studentinfo set age=10,id=3 WHERE name='ani' ";
            int affectedRows=statement.executeUpdate(sql);

            if(affectedRows==0){
                System.out.println("no rows affected");
            }else{
                System.out.println("proccess success");
            }
        }catch (Exception E){
            E.printStackTrace();
        }finally {
            connect.close();
            statement.close();
        }
    }

    public static void retriveDate() throws SQLException{
        Connection connection=null;
        Statement statement=null;
        try{
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user="postgres";
            String password="Test@123";

            connection=DriverManager.getConnection(url,user,password);

            statement=connection.createStatement();

            String sql="SELECT * FROM studentInfo";
            ResultSet result=statement.executeQuery(sql);

            while (result.next()){
                System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getInt(3)+" "+result.getString(4));
            }
            result.close();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            statement.close();
            connection.close();
        }
    }

    public static void deleteData() throws SQLException{
        Connection connection=null;
        Statement statement=null;
        try{
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user="postgres";
            String password="Test@123";

            connection=DriverManager.getConnection(url,user,password);

            statement=connection.createStatement();

            String sql="DELETE FROM studentinfo WHERE id=2";
            int success=statement.executeUpdate(sql);
            if(success!=0){
                System.out.println("process success");
            }else{
                System.out.println("proccess failed");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            statement.close();
            connection.close();
        }
    }

    public static void CRUD_Operation() throws SQLException{
        Connection connect=null;

        try{
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user="postgres";
            String password="Test@123";

            connect=DriverManager.getConnection(url,user,password);
            Statement statement=connect.createStatement();

            String sql="select * from studentinfo";
            boolean result=statement.execute(sql);

            if(result){
                ResultSet resultSet=statement.getResultSet();

                while (resultSet.next()){
                    int id=resultSet.getInt(1);

                    if(id%2==0){
                        int updated=statement.executeUpdate("update studentinfo set name='anisha smith' where id=2");

                        if(updated==0){
                            System.out.println("failed updating");
                        }else{
                            System.out.println("updatated success");
                        }
                    }
                }
            }else{
                System.out.println("empty");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
