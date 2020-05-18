package clinic;

import clinic.models.Admin;
import clinic.models.Patient;
import clinic.models.Section;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class AddBill implements Initializable {

    private Patient patient;
    @FXML
    JFXButton cancle;

    @FXML
    VBox flow;

    @FXML
    JFXComboBox<Section> section;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        section.valueProperty().addListener((observable) -> {
            try {
                doctors();
            } catch (IOException | SQLException ex) {
                Logger.getLogger(AddBill.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cancle.setOnMouseClicked((event) -> {
            HomeController.hideAlert();
        });
        section.setConverter(new StringConverter<Section>() {
            @Override
            public String toString(Section object) {
                return object.getName();
            }

            @Override
            public Section fromString(String string) {
                try {
                    return Section.find(string);
                } catch (SQLException ex) {
                    return null;
                }
            }
        });
        try {
            section.getItems().addAll(Section.findAll());
        } catch (SQLException ex) {
        }
    }

    public void doctors() throws IOException, SQLException {
        flow.getChildren().removeAll(flow.getChildrenUnmodifiable());
        List<Admin> admins;
        if (section.getValue() == null) {
            admins = Admin.findAll();
        } else {
            admins = section.getValue().findAdmins();
        }
        for (Admin admin : admins) {
            FXMLLoader loader = Clinic.getResourse("DoctorComponent");
            Parent root = loader.load();
            DoctorComponent controller = (DoctorComponent) loader.getController();
            controller.bind(admin, patient);
            flow.getChildren().add(root);
        }
    }

    public void bind(Patient patient) {
        this.patient = patient;
        try {
            doctors();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(AddBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
