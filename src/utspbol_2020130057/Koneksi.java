/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol_2020130057;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author A409
 */
public class Koneksi {
    public Connection dbKoneksi;
    public Statement statement;
    public PreparedStatement preparedStatement;
    
    public Koneksi() {        
        this.dbKoneksi = null;    
    }
    public void bukaKoneksi() {
        try {    
            Class.forName("com.mysql.jdbc.Driver");
            dbKoneksi = DriverManager.getConnection("jdbc:derby://localhost:1527/Pesawat_2020130057 [ on APP]");
        } catch (Exception e) {
            e.printStackTrace();        
        }    
    }
    public void tutupKoneksi() {
        try { 
            if (statement != null) {    
                statement.close();           
            }
            if (preparedStatement != null) {     
                preparedStatement.close();            
            }
            if (dbKoneksi != null) {            
                dbKoneksi.close();            
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());        
        }    
    }
}
