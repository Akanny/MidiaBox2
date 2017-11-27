/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midiabox;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Luana
 */
public class MidiaBox extends Application {

    Stage stage;
    public String codigoVideo;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));

        Parent root = (Parent) loader.load();
        TelaLoginController controller = (TelaLoginController) loader.getController();
        controller.setMain(this);
        //Parent root = FXMLLoader.load(getClass().getResource("TelaUpload.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("MidiaBox");

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent doubleClicked) {
                if (doubleClicked.getClickCount() == 2) { //se 2 clicks = tela cheia
                    stage.setFullScreen(true);
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public void abrirMenuPrincipal() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPrincipal2.fxml"));
        Parent root = (Parent) loader.load();
        TelaPrincipalController controller = (TelaPrincipalController) loader.getController();
        controller.setMain(this);
        Scene scene = new Scene(root);
        stage.close();
        stage.setTitle("MidiaBox");
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent doubleClicked) {
                if (doubleClicked.getClickCount() == 2) { //se 2 clicks = tela cheia
                    stage.setFullScreen(true);
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void abrirReprodutor(String codigoVideo) throws Exception {
        this.codigoVideo = codigoVideo;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaReproducao.fxml"));
        Parent root = (Parent) loader.load();
        TelaReproducaoController controller = (TelaReproducaoController) loader.getController();
        controller.setMain(this);
        controller.reproduzir(codigoVideo);
        Scene scene = new Scene(root);
        Stage stage2 = new Stage();
        
        //stage.close();
        stage2.setTitle("MidiaBox");

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent doubleClicked) {
                if (doubleClicked.getClickCount() == 2) { //se 2 clicks = tela cheia
                    stage2.setFullScreen(true);
                }
            }
        });
        
        stage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                t.consume();

               controller.onClick_btn_stop();
                
                
                stage2.close();
               
            }
        });

        stage2.setScene(scene);
        stage2.show();
    }

}
