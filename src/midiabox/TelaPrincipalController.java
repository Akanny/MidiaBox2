package midiabox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Luana
 */
public class TelaPrincipalController implements Initializable {

    private MidiaBox main;
    private MediaPlayer mediaplayer;

    public void setMain(MidiaBox main) {
        this.main = main;
    }

    private String filePath;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select a file (.mp3;.mp4)", "*.mp3;*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        
        if (filePath != null) {
            Client client = new Client();
            client.gravarArquivo(file);
            client.close();
            JOptionPane.showMessageDialog(null, "Arquivo carregado com sucesso!");
        }
    }

    @FXML
    private void reproduzirVideo1() {
        String codigoVideo = "";
        try {
            main.abrirReprodutor(codigoVideo);
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reproduzirVideo2() {
        String codigoVideo = "";
        try {
            main.abrirReprodutor(codigoVideo);
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reproduzirVideo3() {
        String codigoVideo = "";
        try {
            main.abrirReprodutor(codigoVideo);
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
