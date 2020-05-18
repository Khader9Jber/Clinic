package clinic;

import clinic.FilesHandlers.FilesHandler;
import clinic.models.Admin;
import clinic.models.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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

public class AddPatient implements Initializable {
    
    private String imageName;
    
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
    JFXButton addImg;

    @FXML
    Rectangle img;
    
    private int counter; 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler<? super MouseEvent> handler = (event) -> {
            try {
                String temp = counter++ + "p" + Admin.generateId();
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
                new Patient(name.getText(), date.getValue().toString(), address.getText(), phone.getText(), imageName).save();
                HomeController.showAlert("Alert", 426, 500);
            } catch (SQLException ex) {
                Logger.getLogger(AddPatient.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cancle.setOnMouseClicked((event) -> {
            HomeController.hideAlert();
        });
    }

}
