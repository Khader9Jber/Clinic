package clinic;

import clinic.FilesHandlers.FilesHandler;
import clinic.models.Admin;
import clinic.viewsHandler.ImagePicker;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class Clinic extends Application {

    private static Admin authAdmin;
    private static Stage stage;
    final private static Router ROUTER = new Router();
    final private static String COMPONENTS_PATH = "views/";

    public static void auth(Admin admin) {
        authAdmin = admin;
    }

    public static Admin getAdmin() {
        return authAdmin;
    }

    @Override
    public void start(Stage stage) throws Exception {
        DBHandler.init();
        FilesHandler.init();
        FXMLLoader loader = (new Router().view("login"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(true);
        stage.setOpacity(1);
        Clinic.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Router getRouter() {
        return ROUTER;
    }

    public static Parent view(String name) {
        try {
            return ROUTER.view(name).load();
        } catch (IOException ex) {
            Logger.getLogger(Clinic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static FXMLLoader getResourse(String path) {
        try {
            return ROUTER.view(path);
        } catch (IOException ex) {
            Logger.getLogger(Clinic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void alert(String path) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = (new Router().view(path));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setIconified(false);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void route(String path) {
        FXMLLoader loader;
        try {
            loader = (new Router().view(path));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
        } catch (IOException ex) {
            Logger.getLogger(Clinic.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static File pick() {
        return ImagePicker.pick(stage);
    }
    
    public static String component(String path) {
        path = COMPONENTS_PATH + path;
        return path;
    }
}
