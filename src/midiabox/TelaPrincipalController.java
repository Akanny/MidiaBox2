package midiabox;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Luana
 */


public class TelaPrincipalController implements Initializable {

    private MidiaBox main;
    @FXML
    private TextField searchTermTextField;
    @FXML
    private Label statusLabel;
    @FXML
    private TableView<Map<String, Object>> resultsTableView;

    private ResourceBundle resources = null;

    public static final int LIMIT = 100;

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
    private void play() {
        Map<String, Object> selectedResult = 
                      resultsTableView.getSelectionModel().getSelectedItem();
        Midia midia = (Midia) selectedResult.get("Midia");
        try {
            main.abrirReprodutor(String.valueOf(midia.getId_codigo()));
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        this.resources = resources;

        handleSearchAction(null);
    }

    @FXML
    protected void handleSearchAction(ActionEvent event) {

        List<Object> results = (List<Object>) new Client().getListaArquivos();

        // Update the table data
        ObservableList<?> items
                = FXCollections.observableList(results);
        resultsTableView.setItems(
                (ObservableList<Map<String, Object>>) items);
        statusLabel.setText(String.format(resources
                .getString("resultCountFormat"), results.size()));

    }

}
