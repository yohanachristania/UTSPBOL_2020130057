/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol_2020130057;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author A409
 */
public class DBRute {
    private RuteModel dt=new RuteModel();
    
    public RuteModel getRuteModel(){ 
        return(dt);
    }
    public void setRuteModel(RuteModel s){ 
        dt=s;
    } 

    public ObservableList<RuteModel>  Load() {
        try {   ObservableList<RuteModel> TableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select tujuan, tanggal, wktberangkat from rute");
            int i = 1;
            while (rs.next()) {
                RuteModel d=new RuteModel();
                d.setTujuan(rs.getString("tujuan")); 
                d.setTanggal(rs.getString("tanggal"));
                d.setWktberangkat(rs.getString("wktberangkat"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int validasi(String nomor) {
        int val = 0;
        try {  
            Koneksi con = new Koneksi();     
            con.bukaKoneksi();   
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from rute where tujuan = '" + nomor + "'");
            while (rs.next()) {   
                val = rs.getInt("jml");            
            }
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }        

    public boolean insert() {
        boolean berhasil = false;    
        Koneksi con = new Koneksi();
        try {         
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into rute (tujuan, tanggal, wktberangkat) values (?,?,?)");
            con.preparedStatement.setString(1, getRuteModel().getTujuan());
            con.preparedStatement.setString(2, getRuteModel().getTanggal());
            con.preparedStatement.setString(2, getRuteModel().getWktberangkat());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from rute where Tujuan  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update rute set Tanggal = ? where  Tujuan = ? ; ");
            con.preparedStatement.setString(1, getRuteModel().getTujuan());
            con.preparedStatement.setString(2, getRuteModel().getTanggal());
            con.preparedStatement.setString(3, getRuteModel().getWktberangkat());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
}
