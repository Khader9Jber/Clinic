package clinic;

import com.dukescript.layouts.jfxflexbox.FlexBoxPane;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class FlexBox extends FlexBoxPane {

    @Override
    public void resize(double width, double height) {
        super.resize(width, height); //To change body of generated methods, choose Tools | Templates.
        this.getChildren().stream().map((pane) -> {
            System.out.println(((AnchorPane) pane).getHeight());
            return pane;
        }).map((pane) -> {
            System.out.println(((AnchorPane) pane).getLayoutX());
            return pane;
        }).forEachOrdered((pane) -> {
            System.out.println(((AnchorPane) pane).getLayoutY());
        });
    }

    @Override
    public void resizeRelocate(double x, double y, double width, double height) {
        super.resizeRelocate(x, y, width, height); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y); 
    }

}
