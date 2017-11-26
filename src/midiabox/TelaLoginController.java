package midiabox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Luana
 */
public class TelaLoginController implements Initializable {

    private MidiaBox main;

    public void setMain(MidiaBox main) {
        this.main = main;
    }
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private void EntrarLogin(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {

        String nome; // variavel que vai guardar oq o usuario digitar na tela login
        nome = txtNome.getText();// guardando texto digitado

        if (main.getClient().logar(txtNome.getText(), txtSenha.getText())) {
            main.abrirMenuPrincipal();
        } else {
            /**
             * Um alerta caso o usuario ou a senha forem digitado errado*
             */
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro no login");
            alert.setContentText("Usuario ou Senha invalido");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
