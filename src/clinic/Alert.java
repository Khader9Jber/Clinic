package clinic;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Alert implements Initializable{
    
    @FXML
    ImageView icon;
    @FXML
    JFXButton ok;
    @FXML
    JFXButton cancle;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler<? super MouseEvent> event = (Event event1) -> {
            HomeController.hideAlert();
        };
        ok.setOnMouseClicked(event);
        cancle.setOnMouseClicked(event);
        
    }
    
}
