package clinic;

import clinic.models.Section;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class NonPersonComponent implements Initializable {

    @FXML
    Label name;
    @FXML
    Label name1;
    @FXML
    Label name2;
    @FXML
    Label name3;
    @FXML
    Label num1;
    @FXML
    Label num2;
    @FXML
    Label num3;
    @FXML
    JFXButton edit;
    @FXML
    JFXButton delete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void bind(Section section) {
        name.setText(section.getName());
        num1.setText(section.getId() + "");
        try {
            num2.setText(section.findAdmins().size() + "");
            num3.setText(section.findServices().size() + "");
        } catch (SQLException ex) {
        }
        delete.setOnMouseClicked((event) -> {
            try {
                section.delete();
                HomeController.showAlert("Alert", 426, 500);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }

}
