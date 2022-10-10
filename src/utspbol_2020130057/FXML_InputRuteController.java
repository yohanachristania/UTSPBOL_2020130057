/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_2020130057;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author A409
 */
public class FXML_InputRuteController implements Initializable {

    @FXML
    private TextField txttujuan;
    @FXML
    private TextField txtwktberangkat;
    @FXML
    private TextField txttanggal;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    boolean editdata=false;
    public void execute(RuteModel d){
        if(!d.getTujuan().isEmpty()){
          editdata=true;
          txttujuan.setText(d.getTujuan());
          txttanggal.setText(d.getTanggal());
          txtwktberangkat.setText(d.getWktberangkat());
          txttujuan.setEditable(false);
          txttanggal.requestFocus();
        }
    }
    
    @FXML
    private void simpanklik(ActionEvent event) {
        RuteModel s=new RuteModel();
        s.setTujuan(txttujuan.getText());
        s.setTanggal(txttanggal.getText());
        s.setWktberangkat(txtwktberangkat.getText());
        FXMLDocumentController.dtrute.setRuteModel(s);
        if(editdata){
            if(FXMLDocumentController.dtrute.update()){
                Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
                a.showAndWait();   
                txttujuan.setEditable(true);          
                batalklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXMLDocumentController.dtrute.validasi(s.getTujuan())<=0){
            if(FXMLDocumentController.dtrute.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txttujuan.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txttujuan.setText("");
        txtwktberangkat.setText("");
        txttanggal.setText("");
        btnsimpan.setText("");
        btnbatal.setText("");
        btnkeluar.setText("");
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}
