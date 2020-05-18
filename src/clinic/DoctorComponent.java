package clinic;

import clinic.models.Admin;
import clinic.models.Bill;
import clinic.models.Patient;
import clinic.viewsHandler.ImageViewer;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class DoctorComponent implements Initializable {

    @FXML
    JFXButton details;
    @FXML
    Circle image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void bind(Admin admin, EventHandler<? super MouseEvent> handler) {
        details.setText(admin.getName());
        details.setOnMouseClicked(handler);
        ImageViewer.view(image, admin.getImg());
    }

    void bind(Admin admin, Patient patient) {
        ImageViewer.view(image, admin.getImg());
        details.setText(admin.getName());
        details.setOnMouseClicked((event) -> {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                new Bill(formatter.format(date), patient, admin, Bill.WAITING).save();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            HomeController.showAlert("Alert", 426, 500);
        });
    }

}
