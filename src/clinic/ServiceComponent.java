package clinic;

import clinic.models.Service;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class ServiceComponent implements Initializable {

    @FXML
    JFXButton details;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void bind(Service service, EventHandler<? super MouseEvent> handler) {
        details.setText(service.getName());
        details.setOnMouseClicked((EventHandler<? super MouseEvent>) handler);
    }

}
