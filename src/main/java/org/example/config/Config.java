package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
       public final static String url="jdbc:postgresql://localhost:5432/JDBC.Project2";
       public final static String name="postgres";
       public final static String password="1234";

       public static Connection getConnection(){
           Connection connection = null;
           try{
               connection = DriverManager.getConnection(url,name,password);
               System.out.println("Data base is connected!");
           }catch (Exception e){
               System.out.println(e.getMessage());
           }return connection;
       }
}
