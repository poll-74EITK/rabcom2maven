/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pavel Papsh
 */
public class SqlConnection {
    public static Connection DbConnector(){
       try{
        Connection conn = null;
        
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:D:\\MyDocuments\\Documents\\NetBeansProjects\\rabcom2maven\\src\\main\\resources\\lib\\rabcom.sqlite");
        return conn;
       }catch(ClassNotFoundException | SQLException e){
           System.out.println(e);
       } 
       return null;
    }    
}
