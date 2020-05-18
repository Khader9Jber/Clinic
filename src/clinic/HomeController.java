package clinic;

import clinic.models.Section;
import clinic.viewsHandler.CustomTransition;
import clinic.viewsHandler.ImageViewer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class HomeController implements Initializable {

    private static HomeController refernce;
    private final double duaration = 1;
    private final boolean startOpened = true;
    private final int maxWidth = 200;

    @FXML
    AnchorPane universe;
    @FXML
    ScrollPane menu;
    @FXML
    JFXHamburger ham;
    @FXML
    HBox hBox;
    @FXML
    AnchorPane alertPane;
    @FXML
    VBox notification;
    @FXML
    AnchorPane notesContainer;
    @FXML
    JFXButton notes;
    @FXML
    JFXButton settings;
    @FXML
    JFXButton myAccount;
    @FXML
    JFXButton search;
    @FXML
    JFXButton close;
    @FXML
    Circle image;
    @FXML
    AnchorPane front;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = Clinic.getAdmin().getImg();
        ImageViewer.view(image, path);
        universe.setOnMouseClicked((event) -> {
            if (notesContainer.isVisible()) {
                toggleNotes();
            }
        });
        close.setOnMouseClicked((event) -> {
            System.exit(0);
        });
        notes.setOnMouseClicked((event) -> {
            toggleNotes();
        });
        myAccount.setOnMouseClicked((event) -> {
            accountLink.getOnMouseClicked().handle(event);
        });
        search.setOnMouseClicked((event) -> {
            System.out.println("search");
        });
        settings.setOnMouseClicked((event) -> {
            System.out.println("settings");
        });
        refernce = this;
        initNav();
        if (startOpened) {
            menu.setPrefWidth(maxWidth);
            menu.setMaxWidth(maxWidth);
            menu.setMinWidth(maxWidth);
            menu.setTranslateX(0);
        } else {
            menu.setPrefWidth(0);
            menu.setMinWidth(0);
            menu.setMaxWidth(0);
            menu.setTranslateX(- 1 * maxWidth);
        }
        HamburgerNextArrowBasicTransition t = new HamburgerNextArrowBasicTransition(ham);
        t.setRate(startOpened ? -1 : 1);
        t.setDelay(Duration.ONE);
        ham.setOnMouseClicked((event) -> {
            t.setRate(t.getRate() * -1);
            t.play();
            CustomTransition transition;
            if (t.getRate() > 0) {
                transition = CustomTransition.getTransition((frac) -> {
                    menu.setTranslateX((0 - frac) * maxWidth);
                    menu.setPrefWidth((1 - frac) * maxWidth);
                    menu.setMinWidth((1 - frac) * maxWidth);
                    menu.setMaxWidth((1 - frac) * maxWidth);

                });
            } else {
                transition = CustomTransition.getTransition((frac) -> {
                    menu.setTranslateX(((frac) - 1) * maxWidth);
                    menu.setPrefWidth((frac) * maxWidth);
                    menu.setMinWidth((frac) * maxWidth);
                    menu.setMaxWidth((frac) * maxWidth);

                });
            }
            transition.setCycleDuration(duaration * 1000);
            transition.play();

        });
        notification();
    }

    private void ref(JFXButton btn, String path) {
        btn.setOnMouseClicked((event) -> {
            Parent parent = Clinic.view(path);
            route(parent);
            select(btn);
        });
    }

    private void ref(JFXButton btn, Node item) {
        btn.setOnMouseClicked((event) -> {
            route(item);
            select(btn);
        });
    }

    private void ref(JFXButton btn, String path, EventHandler<? super MouseEvent>... handlers) {
        ref(btn, path);
        appendHandler(btn, handlers[0]);
    }

    private void ref(JFXButton btn, Node item, EventHandler<? super MouseEvent>... handlers) {
        btn.setOnMouseClicked((event) -> {
            route(item);
        });
        select(btn);
        appendHandler(btn, handlers[0]);
    }

    private void select(JFXButton btn) {
        btn.setStyle("-fx-background-color: #ffac41;-fx-border-color: #323232;-fx-border-radius:  5;-fx-background-radius:  5;");
        btn.setTextFill(Color.rgb(50, 50, 50, 1.0));
        FontAwesomeIconView icon = map.get(btn);
        icon.setFill(Color.rgb(50, 50, 50, 1.0));
    }

    private void refAlert(JFXButton btn, String path, double width, double hieght) {
        btn.setOnMouseClicked((event) -> {
            Parent parent = Clinic.view(path);
            refAlert(btn, parent, width, hieght);
        });

    }

    private void refAlert(JFXButton btn, Node node, double width, double hieght) {
        btn.setOnMouseClicked((event) -> {
            showAlert(node, width, hieght);
            map.forEach((button, icon) -> {
                button.setStyle("-fx-background-color: #323232;-fx-border-color:  #ffac41;-fx-border-radius:  5;-fx-background-radius:  5;");
                button.setTextFill(Color.rgb(255, 170, 66, 1.0));
                icon.setFill(Color.rgb(255, 170, 66, 1.0));
            });
            select(btn);
        });
    }

    private void refAlert(JFXButton btn, Node node, double width, double hieght, EventHandler<? super MouseEvent> handler) {
        refAlert(btn, node, width, hieght);
        appendHandler(btn, handler);
    }

    private void refAlert(JFXButton btn, String node, double width, double hieght, EventHandler<? super MouseEvent> handler) {
        Node item = Clinic.view(node);
        refAlert(btn, item, width, hieght);
    }

    private void ref(JFXButton btn, EventHandler<? super MouseEvent> handler) {
        btn.setOnMouseClicked((event) -> {
            handler.handle(event);
            select(btn);
        });
    }

    public static void route(Node root) {
        ObservableList<Node> nodes = refernce.hBox.getChildren();

        if (nodes.size() > 1) {
            Node temp = nodes.get(0);
            nodes.clear();
            nodes.add(temp);
        }
        nodes.addAll(root);
        HBox.setHgrow(root, Priority.ALWAYS);
        refernce.map.forEach((button, icon) -> {
            button.setStyle("-fx-background-color: #323232;-fx-border-color:  #ffac41;-fx-border-radius:  5;-fx-background-radius:  5;");
            button.setTextFill(Color.rgb(255, 170, 66, 1.0));
            icon.setFill(Color.rgb(255, 170, 66, 1.0));
        });
    }

    @FXML
    JFXButton sectionsLink;
    @FXML
    FontAwesomeIconView sectionsIcon;
    @FXML
    JFXButton exit;
    @FXML
    FontAwesomeIconView exitIcon;
    @FXML
    JFXButton accountLink;
    @FXML
    FontAwesomeIconView accountIcon;
    @FXML
    JFXButton accountSettingsLink;
    @FXML
    FontAwesomeIconView accountSettingsIcon;
    @FXML
    JFXButton searchLink;
    @FXML
    FontAwesomeIconView searchIcon;
    @FXML
    JFXButton statisticsLink;
    @FXML
    FontAwesomeIconView statisticsIcon;
    @FXML
    JFXButton patientsLink;
    @FXML
    FontAwesomeIconView patientsIcon;
    @FXML
    JFXButton servicesLink;
    @FXML
    FontAwesomeIconView servicesIcon;
    @FXML
    JFXButton doctorsLink;
    @FXML
    FontAwesomeIconView doctorsIcon;

////////////////////////////////////////////////////add///////////////////////////////////////////////
    @FXML
    JFXButton addDoctorLink;
    @FXML
    FontAwesomeIconView addDoctorIcon;
    @FXML
    JFXButton addPatientLink;
    @FXML
    FontAwesomeIconView addPatientIcon;
    @FXML
    JFXButton addBillLink;
    @FXML
    FontAwesomeIconView addBillIcon;
    @FXML
    JFXButton addSectionLink;
    @FXML
    FontAwesomeIconView addSectionIcon;
    @FXML
    JFXButton addServiceLink;
    @FXML
    FontAwesomeIconView addServiceIcon;
////////////////////////////////////////////////////add///////////////////////////////////////////////

    private final HashMap<JFXButton, FontAwesomeIconView> map = new HashMap<>();

    private void initNav() {
        map.put(exit, exitIcon);
        map.put(searchLink, searchIcon);
        map.put(accountLink, accountIcon);
        map.put(statisticsLink, statisticsIcon);
        map.put(sectionsLink, sectionsIcon);
        map.put(accountSettingsLink, accountSettingsIcon);
        map.put(patientsLink, patientsIcon);
        map.put(servicesLink, servicesIcon);
        map.put(doctorsLink, doctorsIcon);
        map.put(addBillLink, addBillIcon);
        map.put(addDoctorLink, addDoctorIcon);
        map.put(addServiceLink, addServiceIcon);
        map.put(addSectionLink, addSectionIcon);
        map.put(addPatientLink, addPatientIcon);
        ref(addBillLink, "Patients");
        refAlert(addSectionLink, "AddSection", 400, 500);
        refAlert(addDoctorLink, "AddAdmin", 1000, 1000);
        refAlert(addPatientLink, "AddPatient", 550, 620);
        refAlert(addServiceLink, "AddService", 350, 500);
        refAlert(accountSettingsLink, "AddAdmin", 1000, 1000);

        ref(doctorsLink, "doctors");
        ref(sectionsLink, (event) -> {
            try {
                List<Section> sections = Section.findAll();
                FXMLLoader loader = Clinic.getResourse("NonPersonList");
                Parent root = loader.load();
                clinic.NonPersonList listc = (NonPersonList) loader.getController();
                listc.bind(sections);
                route(root);
            } catch (SQLException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
//        ref(sectionsLink, "NonPersonList");
        ref(servicesLink, "services");
        ref(patientsLink, "doctors");
        ref(accountLink, Clinic.component("account/Account"));
        ref(searchLink, "Search");
        ref(statisticsLink, "Dashboard2");
//        appendHandler(searchLink, (event) -> {
//            showAlert("AddAdmin", 1000, 800);
//        });
        exit.setOnMouseClicked((event) -> {
            System.exit(0);
        });
        defualt(statisticsLink);
    }

    private void defualt(JFXButton button) {
        button.getOnMouseClicked().handle(null);
    }

    public static void showAlert(String path, double width, double hieght) {
        AnchorPane alertPane = refernce.alertPane;
        alertPane.getChildren().clear();
        try {
            FXMLLoader loader = (new Router().view(path));
            Parent root = loader.load();
            showAlert(root, width, hieght);
        } catch (IOException ex) {
        }
    }

    static void showAlert(Node root, double width, double hieght) {
        AnchorPane alertPane = refernce.alertPane;
        alertPane.getChildren().clear();
        AnchorPane.setBottomAnchor(alertPane, 0d);
        AnchorPane.setRightAnchor(alertPane, 0d);
        AnchorPane.setLeftAnchor(alertPane, 0d);
        AnchorPane.setTopAnchor(alertPane, 0d);
        AnchorPane transparent = new AnchorPane();
        transparent.setStyle("-fx-background-color: #000;");
        transparent.setOpacity(0.5);
        alertPane.getChildren().add(transparent);
        AnchorPane.setBottomAnchor(transparent, 0d);
        AnchorPane.setRightAnchor(transparent, 0d);
        AnchorPane.setLeftAnchor(transparent, 0d);
        AnchorPane.setTopAnchor(transparent, 0d);
        double right = Math.max(50, (refernce.universe.getWidth() - width) / 2.0);
        double left = Math.max(50, (refernce.universe.getWidth() - width) / 2.0);
        double top = Math.max(70, (refernce.universe.getHeight() - hieght) * 2 / 3.0);
        double bottom = Math.max(50, (refernce.universe.getHeight() - hieght) / 3.0);
        alertPane.getChildren().add(root);
        AnchorPane.setBottomAnchor(root, bottom);
        AnchorPane.setRightAnchor(root, right);
        AnchorPane.setLeftAnchor(root, left);
        AnchorPane.setTopAnchor(root, top);
    }

    public static void hideAlert() {
        AnchorPane alertPane = refernce.alertPane;
        AnchorPane.clearConstraints(alertPane);
        alertPane.getChildren().clear();
    }

    public static void notification() {
        VBox notification = refernce.notification;
        notification.getChildren().clear();
        Clinic.getAdmin().getBills().forEach((bill) -> {
            try {
                FXMLLoader loader = Clinic.getResourse("NotificationItem");
                Parent root = loader.load();
                NotificationItem item = (NotificationItem) loader.getController();
                item.bind(bill);
                notification.getChildren().add(root);
            } catch (IOException ex) {
            }
        });
    }

    public static void toggleNotes() {
        AnchorPane notesContainer = refernce.notesContainer;
        if (notesContainer.isVisible()) {
            notesContainer.setVisible(false);
            return;
        }
        notification();
        notesContainer.setVisible(true);
    }

    public static void appendHandler(Node node, EventHandler<? super MouseEvent> anEvent) {
        EventHandler handler = node.getOnMouseClicked();
        node.setOnMouseClicked((event) -> {
            handler.handle(event);
            anEvent.handle(event);
        });
    }
}
