package clinic;

import clinic.models.Section;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AddSection implements Initializable {

    @FXML
    JFXButton save;

    @FXML
    JFXButton cancle;

    @FXML
    JFXTextField name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save.setOnMouseClicked((event) -> {
            try {
                new Section(name.getText()).save();
                HomeController.showAlert("Alert", 426, 500);
            } catch (SQLException ex) {
                Logger.getLogger(AddSection.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cancle.setOnMouseClicked((event) -> {
            HomeController.hideAlert();
        });
    }

}
