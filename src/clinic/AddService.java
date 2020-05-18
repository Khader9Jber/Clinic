package clinic;

import clinic.models.Section;
import clinic.models.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;

public class AddService implements Initializable {

    @FXML
    JFXButton save;

    @FXML
    JFXButton cancle;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField cost;

    @FXML
    JFXComboBox<Section> section;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save.setOnMouseClicked((event) -> {
            try {
                new Service(name.getText(), section.getValue(), Double.parseDouble(cost.getText())).save();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            HomeController.showAlert("Alert", 426, 500);
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
            Logger.getLogger(AddService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
