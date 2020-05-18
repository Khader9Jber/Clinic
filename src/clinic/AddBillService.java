package clinic;

import clinic.models.Admin;
import clinic.models.Bill;
import clinic.models.Section;
import clinic.models.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class AddBillService implements Initializable {

    private Bill bill;
    @FXML
    JFXButton cancle;

    @FXML
    JFXRadioButton doctor;

    @FXML
    JFXRadioButton service;

    @FXML
    VBox flow;

    @FXML
    JFXComboBox<Section> section;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            doctors();
        } catch (IOException | SQLException ex) {
        }
        section.valueProperty().addListener((observable) -> {
            if (doctor.isSelected()) {
                try {
                    doctors();
                } catch (IOException | SQLException ex) {
                }
            }
            if (service.isSelected()) {
                try {
                    services();
                } catch (IOException | SQLException ex) {
                }
            }
        });
        doctor.setSelected(true);
        doctor.setOnMouseClicked((event) -> {
            try {
                doctors();
            } catch (IOException | SQLException ex) {
            }

        });
        service.setOnMouseClicked((event) -> {
            try {
                services();
            } catch (IOException | SQLException ex) {
            }

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
            Logger.getLogger(AddBillService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doctors() throws IOException, SQLException {
        flow.getChildren().clear();
        List<Admin> admins;
        if (section.getValue() == null) {
            admins = Admin.findAll();
        } else {
            admins = section.getValue().findAdmins();
        }
        for (Admin admin : admins) {
            FXMLLoader loader = Clinic.getResourse("DoctorComponent");
            Parent root = loader.load();
            DoctorComponent controller = (DoctorComponent) loader.getController();
            controller.bind(admin, (event) -> {
                try {
                    FXMLLoader aloader = Clinic.getResourse("SaveBillService");
                    Parent aroot = aloader.load();
                    SaveBillService acontroller = (SaveBillService) aloader.getController();
                    acontroller.bind(bill, admin);
                    HomeController.showAlert(aroot, 600, 700);
                } catch (IOException ex) {
                }
            });
            flow.getChildren().add(root);
        }
    }

    public void services() throws IOException, SQLException {
        flow.getChildren().clear();
        List<Service> services;
        if (section.getValue() == null) {
            services = Service.findAll();
        } else {
            services = section.getValue().findServices();
        }
        for (Service aservice : services) {
            FXMLLoader loader = Clinic.getResourse("ServiceComponent");
            Parent root = loader.load();
            ServiceComponent controller = (ServiceComponent) loader.getController();
            controller.bind(aservice, (event) -> {
                try {
                    FXMLLoader aloader = Clinic.getResourse("SaveBillService");
                    Parent aroot = aloader.load();
                    SaveBillService acontroller = (SaveBillService) aloader.getController();
                    acontroller.bind(bill, aservice);
                    HomeController.showAlert(aroot, 600, 700);
                } catch (IOException ex) {
                }
            });
            flow.getChildren().add(root);
        }
    }

    public void bind(Bill bill) {
        this.bill = bill;
    }

}
