/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midiabox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;

/**
 *
 * @author Luana
 */
public class TelaReproducaoController implements Initializable {
    
    @FXML
    private MediaView mv;
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_stop;
    
    MediaPlayer mediaplayer;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String VUrl = "file:/C:/Users/Luana/Documents/PIVA_MIDIABOX/midia/demi.mp3";
        Media media = new Media(VUrl);
        mediaplayer = new MediaPlayer(media);
        mv.setFitHeight(800);
        mv.setFitWidth(600);
        mv.setMediaPlayer(mediaplayer);
       
          
    }

    @FXML
    private void onClick_btn_play() {
        if (mediaplayer.getStatus() == PLAYING) {
            mediaplayer.stop();
            mediaplayer.play();

        } else {
            mediaplayer.play();
        }
    }

    @FXML
    private void onClick_btn_stop() {
        mediaplayer.stop();
    }

}