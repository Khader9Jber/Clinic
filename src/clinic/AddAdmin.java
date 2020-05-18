package clinic;

import clinic.FilesHandlers.FilesHandler;
import clinic.models.Admin;
import clinic.models.Section;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

public class AddAdmin implements Initializable {

    String imageName;
    @FXML
    JFXButton save;

    @FXML
    JFXButton cancle;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField address;

    @FXML
    JFXDatePicker date;

    @FXML
    JFXTextField phone;

    @FXML
    JFXTextField email;

    @FXML
    JFXPasswordField password;

    @FXML
    JFXTextField room;

    @FXML
    JFXTextField title;

    @FXML
    JFXComboBox<Section> section;

    @FXML
    JFXButton addImg;

    @FXML
    Rectangle img;
    
    private int counter; 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler<? super MouseEvent> handler = (event) -> {
            try {
                String temp = counter++ + "a" + Admin.generateId();
                String name = FilesHandler.getImage(temp);
                if (!temp.equals(name)) {
                    System.out.println(FilesHandler.get(name).toURI().toString());
                    img.setFill(new ImagePattern(new Image(FilesHandler.get(name).toURI().toString())));
                    this.imageName = name;
                }
            } catch (SQLException ex) {
            }
        };
        img.setOnMouseClicked(handler);
        addImg.setOnMouseClicked(handler);
        save.setOnMouseClicked((event) -> {
            try {
                Admin temp = new Admin(name.getText(), email.getText(), password.getText(), imageName, phone.getText(), section.getSelectionModel().getSelectedItem(), room.getText(), title.getText(), address.getText(), date.getValue().toString());
                temp.save();
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
            Logger.getLogger(AddAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
