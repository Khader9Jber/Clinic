package clinic.components.navigation;

import clinic.FXMLDocumentController;
import clinic.Router;
import clinic.viewsHandler.CustomTransition;
import clinic.viewsHandler.Resizable;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class NavigationDrawer implements Resizable {

    @FXML
    private JFXHamburger burger;

    @FXML
    private Circle image;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane pane;

    @Override
    public void resize(Number old, Number newVal) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            drawer.setSidePane((Node)(new Router().route("navigation/Pane").load()));
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        HamburgerNextArrowBasicTransition back = new HamburgerNextArrowBasicTransition(burger);
        back.setRate(back.getRate() * -1);
        burger.setOnMouseClicked((MouseEvent event) -> {
            back.setRate(back.getRate() * -1);
            back.play();
            if (back.getRate() > 0) {
                CustomTransition.play((double frac) -> {
                    pane.setPrefWidth(50 + frac * 150);
                });
                drawer.open();
            } else {
                CustomTransition.play((double frac) -> {
                    pane.setPrefWidth(200 - frac * 150);
                });
                drawer.close();
            }
        });
    }

}
