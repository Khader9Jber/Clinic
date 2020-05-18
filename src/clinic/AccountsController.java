package clinic;

import clinic.models.Patient;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class AccountsController implements Initializable {

    private final double duaration = 1;
    private final boolean startOpened = true;
    private final int maxWidth = 200;

    @FXML
    AnchorPane pane;
    @FXML
    private ResponsiveFrame content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addAccount();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ResponsiveFrame frame = new ResponsiveFrame(pane) {
            @Override
            void onResize(Number oldValue, Number newValue) {
            }
        };
        content = frame;
        frame.cols(700, 2, 180, 50);
        frame.cols(1000, 3, 180, 50);
    }

    public void addAccount() throws IOException, SQLException {
        for (Patient patient : Patient.findAll()) {
        FXMLLoader loader = Clinic.getResourse("PatientComponent");
        Parent root = loader.load();
        PatientComponent controller = (PatientComponent) loader.getController();
            controller.bind(patient);
            pane.getChildren().add(root);
        }
    }

    public void fill(Node... nodes) {
        pane.getChildren().setAll(nodes);
    }
}
