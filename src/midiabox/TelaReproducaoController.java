package midiabox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Luana
 */
public class TelaReproducaoController implements Initializable {

    MidiaBox main;
    String codigoVideo;

    public void setMain(MidiaBox main) {
        this.main = main;
    }

    @FXML
    private MediaView mv;

    @FXML
    private Button btn_play;
    @FXML
    private Button btn_stop;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider progressoSlider;

    MediaPlayer mediaplayer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }
    
    public void reproduzir(String codigoVideo){
        
        //String VUrl = "file:/C:/Users/Jess/Desktop/videos/teste.mp4"; // caminho da midia
        String VUrl = "file:/"+new Client().getUrlVideo(codigoVideo); // caminho da midia
        Media media = new Media(VUrl);
        mediaplayer = new MediaPlayer(media);

        /**
         * ajusta a tela ao tamanho da midia
         */
        mv.setMediaPlayer(mediaplayer);
        DoubleProperty width = mv.fitWidthProperty();
        DoubleProperty hight = mv.fitHeightProperty();
        width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
        hight.bind(Bindings.selectDouble(mv.sceneProperty(), "hight"));
        mv.setMediaPlayer(mediaplayer);

        /**
         * Volume e progresso da midia
         */
        volumeSlider.setValue(mediaplayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mediaplayer.setVolume(volumeSlider.getValue() / 100);
            }
        });

        mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                progressoSlider.setValue(newValue.toSeconds());
            }
        });

        progressoSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mediaplayer.seek(Duration.seconds(progressoSlider.getValue()));
            }
        });
    }
    
    /**
     * Botao Play e Pause
     */
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
    public void onClick_btn_stop() {
        mediaplayer.stop();
    }

    void setCodigoVideo(String codigoVideo) {
        this.codigoVideo = codigoVideo;
    }

}
