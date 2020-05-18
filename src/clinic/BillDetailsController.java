package clinic;

import clinic.models.Admin;
import clinic.models.Bill;
import clinic.models.Patient;
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
import javafx.scene.control.Label;

public class BillDetailsController implements Initializable {

    @FXML
    JFXButton pass;
    @FXML
    Label name;
    @FXML
    Label birthdate;
    @FXML
    Label state;
    @FXML
    Label admin;
    @FXML
    Label time;
    @FXML
    Label address;
    @FXML
    Label phone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void bind(Bill bill) {
        Patient patient = bill.getPatient();
        Admin anAdmin = bill.getAdmin();
        this.name.setText(patient.getName());
        this.birthdate.setText(patient.getBirthdate());
        this.address.setText(patient.getAddress());
        this.phone.setText(patient.getPhone());
        this.admin.setText(anAdmin.getName());
        this.time.setText(bill.getDate());
        String aState;
        switch (bill.getState()) {
            case Bill.ACTIVE:
                aState = "جارية";
                break;
            case Bill.FINISHED:
                aState = "منتهية";
                break;
            case Bill.WAITING:
                aState = "معلقة";
                break;
            default:
                aState = "مراجعة";
        }
        this.state.setText(aState);
        
        pass.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = Clinic.getResourse("AddBillService");
                Parent root = loader.load();
                AddBillService controller = (AddBillService) loader.getController();
                controller.bind(bill);
                HomeController.showAlert(root, 700, 800);
            } catch (IOException ex) {
            }
        });
    }

}
