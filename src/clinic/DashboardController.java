package clinic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {

    @FXML
    AnchorPane pane;
    @FXML
    private ResponsiveFrame content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResponsiveFrame frame = new ResponsiveFrame(pane) {
            @Override
            void onResize(Number oldValue, Number newValue) {
            }
        };
        content = frame;
        frame.cols(0, 1, 200, 50);
        frame.cols(700, 1, 200, 50);
        frame.cols(800, 2, 200, 50);
        frame.cols(1100, 3, 200, 50);
        frame.cols(1250, 4, 200, 50);
    }

    public void fill(Node... nodes) {
        pane.getChildren().setAll(nodes);
    }
}
