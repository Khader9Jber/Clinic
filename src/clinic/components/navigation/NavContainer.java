package clinic.components.navigation;

import clinic.viewsHandler.Resizable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class NavContainer implements Resizable{
    @FXML
    Circle image;
    @Override
    public void resize(Number old, Number newVal) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        image.setFill(new ImagePattern(new Image("File:\\C:\\Users\\PC\\Desktop\\3255469.jpg")));
    }
    
}
