package clinic;

import clinic.viewsHandler.Resizable;
import com.dukescript.layouts.jfxflexbox.FlexBoxPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;

public class FXMLDocumentController implements Resizable {

    @FXML
    BorderPane frame;

    static void onChange(Number oldValue, Number newValue) {

    }
    @FXML
    private FlexBoxPane flex;
   
    @FXML
    private FlowPane flow;

    @FXML
    private Circle image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            frame.setLeft(new Router().route("navigation/NavigationDrawer").load());
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        flex.setAlignContent(FlexboxLayout.AlignContent.CENTER);
//        flex.setFlexDirection(FlexboxLayout.FlexDirection.ROW);
//        flex.setAlignItems(FlexboxLayout.AlignItems.FLEX_START);
//        flex.setJustifyContent(FlexboxLayout.JustifyContent.SPACE_AROUND);
//        flex.setFlexWrap(FlexboxLayout.FlexWrap.WRAP);
//        FlexBoxPane.setMargin(flex.getChildren().get(0),new Insets(5));
//        FlexBoxPane.setGrow(flex.getChildren().get(0),100);
//        FlexBoxPane.setFlexBasisPercent(flex.getChildren().get(0),100);
//        FlexBoxPane.setMargin(flex.getChildren().get(1),new Insets(5));
//        FlexBoxPane.setMargin(flex.getChildren().get(2),new Insets(5));
//        FlexBoxPane.setMargin(flex.getChildren().get(3),new Insets(5));
//        FlexBoxPane.setMargin(flex.getChildren().get(4),new Insets(5));
//        FlexBoxPane.setMargin(flex.getChildren().get(5),new Insets(5));
//        FlexBox f = new FlexBox();
//        AnchorPane p = new AnchorPane();
//        p.setLayoutY(100);
//        f.getChildren().add(p);
//        p.setBorder(new Border(new BorderImage(new Image("file:/C:/Users/PC/Desktop/3255469.jpg"), BorderWidths.FULL, Insets.EMPTY, BorderWidths.FULL, true, BorderRepeat.REPEAT, BorderRepeat.REPEAT)));
//        f.getChildren().add(new AnchorPane(new Label("longlonglonglonglonglonglonglonglonglonglonglonglonglong")));
//        f.getChildren().add(new AnchorPane(new Label("longlonglonglonglonglonglonglonglonglonglonglonglonglong")));
//        p.getChildren().add(new ImageView("file:/C:/Users/PC/Desktop/3255469.jpg"));
//        frame.setCenter(f);
//        frame.getScene().setOnSwipeRight((event) -> {
//            System.out.println("S");
//        });
        frame.setOnScrollStarted((event) -> {
        });
        System.out.println(frame.getChildren().get(0).getScene());
        System.out.println(frame.getHeight());
        frame.setOnMouseDragged((event) -> {
        });
//        flow.
    }

    public BorderPane getFrame() {
        System.out.println(frame.getHeight());
        System.out.println(flex.getHeight());
        System.out.println(image.getId());
        return frame;
    }

    @Override
    public void resize(Number old, Number newVal) {

    }

}
