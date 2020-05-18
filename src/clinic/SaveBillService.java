package clinic;

import clinic.models.Admin;
import clinic.models.Bill;
import clinic.models.BillService;
import clinic.models.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SaveBillService implements Initializable {

    private Service service;
    private Admin admin;
    private Bill bill;

    @FXML
    JFXButton save;
    @FXML
    JFXButton cancle;
    @FXML
    JFXTextArea note;
    @FXML
    JFXTextField count;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancle.setOnMouseClicked((event) -> {
            HomeController.hideAlert();
        });
        save.setOnMouseClicked((event) -> {
            if (admin != null) {
                bill.setAdmin(admin);
                count.setText(0+ "");
            }
            BillService billService = new BillService(bill, service, Integer.parseInt(count.getText()), admin, note.getText());
            try {
                billService.save();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    public void bind(Bill bill, Service service) {
        this.bill = bill;
        this.service = service;
    }

    public void bind(Bill bill, Admin admin) {
        this.bill = bill;
        this.admin = admin;
        this.count.setVisible(false);
    }

}
