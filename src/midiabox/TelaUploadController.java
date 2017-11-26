/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templateshttps://www.youtube.com/watch?v=fqqSYbP0aRA
 * and open the template in the editor.https://www.youtube.com/watch?v=sjiS4mhb0gQ
 */
package midiabox;

import SQLiteBanco.Connect;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Luana
 */
public class TelaUploadController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private TextField txt_url;

    @FXML
    private TextField txt_nome;

    @FXML
    private TextField txt_responsavel;

    @FXML
    private TextField txt_desc;

    @FXML
    private TextField txt_tipo;

    private String filePath;

    @FXML
    private void handleButtonActionAdd(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select a file (*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();

        if (filePath != null) {
            Media media = new Media(filePath);
            MediaPlayer mediaplayer = new MediaPlayer(media);

        }

    }

    @FXML
    private void enviar(ActionEvent event) throws SQLException, ClassNotFoundException {

        conn = Connect.ConnectDB(); // classe connecttDB da classe SQLiteBanco
        String sql = "INSERT INTO tb_midia(URL,nome,responsavel,tipo,descricao) VALUES(?,?,?,?,?)";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, txt_url.getText());
            pst.setString(2, txt_nome.getText());
            pst.setString(3, txt_responsavel.getText());
            pst.setString(4, txt_tipo.getText());
            pst.setString(5, txt_desc.getText());

            int bd = pst.executeUpdate();

            if (bd == 1) {

                JOptionPane.showMessageDialog(null, "Salvo");

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Sem acesso ao banco de dados");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
