package clinic;

import clinic.models.Section;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NonPersonList implements Initializable {

    @FXML
    Label title;
    @FXML
    JFXButton add;
    @FXML
    JFXButton search;
    @FXML
    VBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void bind(List<Section> sections) {
        title.setText("الأقسام في العيادة");
        for (Section section : sections) {
            try {
                FXMLLoader loader = Clinic.getResourse("NonPersonComponent");
                Parent root = loader.load();
                NonPersonComponent nonPerson = (NonPersonComponent) loader.getController();
                nonPerson.bind(section);
                container.getChildren().add(root);
                VBox.setMargin(root, new Insets(0, 0, 50, 0));
                add.setOnMouseClicked((event) -> {
                    HomeController.showAlert("AddSection", 400, 500);
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
