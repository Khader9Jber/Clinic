package clinic.viewsHandler;

import javafx.fxml.Initializable;

public interface Resizable extends Initializable{
    abstract void resize(Number old, Number newVal);
}
