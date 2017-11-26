/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;


import SQLiteBanco.Connect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;


public class TelaLoginServidorController implements Initializable {
 Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private TextField txt_nomeserver;

    @FXML
    private TextField txt_senhaserver;

    @FXML
    private void EntrarLoginServer(ActionEvent event) throws SQLException, ClassNotFoundException {

        String user; // variavel que vai guardar oq o usuario digitar na tela login
        user = txt_nomeserver.getText();// guardando texto digitado

        conn = Connect.ConnectDB(); // classe connecttDB da classe SQLiteBanco
        String sql = "SELECT *FROM tableUSER  WHERE Nome=? and Senha=?"; // selecionando todo campo username e passoword 

        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, txt_nomeserver.getText());  
            pst.setString(2, txt_senhaserver.getText());
            rs = pst.executeQuery(); 
            
            if (rs.next()) { 
                JOptionPane.showMessageDialog(null, "connect");// se o usuario e a senha estiverem certo, é exibido uma mensagem de conectado
                

            } else {
                /**Um alerta caso o usuario ou a senha forem digitado errado* */
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("ERRO");
              alert.setHeaderText("Erro no login");
              alert.setContentText("Usuario ou Senha invalido"); 
              alert.show();
            }
            }catch(SQLException e){
                 JOptionPane.showMessageDialog(null, e);
        }

    }
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
