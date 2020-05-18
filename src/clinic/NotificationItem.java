package clinic;

import clinic.models.Bill;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class NotificationItem implements Initializable {

    @FXML
    JFXButton main;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void bind(Bill bill) {
        main.setOnMouseClicked((event) -> {
            HomeController.toggleNotes();
            try {
                FXMLLoader loader = Clinic.getResourse("BillDetails");
                Parent root = loader.load();
                BillDetailsController controller = (BillDetailsController) loader.getController();
                controller.bind(bill);
                HomeController.route(root);
            } catch (IOException ex) {
                Logger.getLogger(NotificationItem.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        main.setText(bill.getPatient().getName());
    }

}
