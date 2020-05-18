package clinic;

import clinic.models.Patient;
import clinic.viewsHandler.ImageViewer;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class PatientComponent implements Initializable {

    @FXML
    JFXButton add;
    @FXML
    JFXButton details;
    @FXML
    Circle image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void bind(Patient patient) {
        ImageViewer.view(image, patient.getImg());
        add.setOnMouseClicked((event) -> {
            FXMLLoader loader = Clinic.getResourse("AddBill");
            try {
                Node root = (Node) loader.load();
                AddBill controller = (AddBill) loader.getController();
                controller.bind(patient);
                HomeController.showAlert(root, 500, 700);
            } catch (IOException ex) {
                Logger.getLogger(PatientComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        details.setText(patient.getName());
        details.setOnMouseClicked((event) -> {
        });
    }

}
