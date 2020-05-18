package clinic;

import java.util.Collections;
import java.util.LinkedList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public abstract class ResponsiveFrame {

    private AnchorPane pane;
    private LinkedList<Listener> listeners;
    private boolean isSorted;

    public ResponsiveFrame(AnchorPane pane) {
        this.pane = pane;
        listeners = new LinkedList();
        pane.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (oldValue.doubleValue() != 0) {
                updateWidthConstaints(oldValue.doubleValue(), newValue.doubleValue());
            } else {
                updateWidthConstaints(0, newValue.doubleValue());
            }
            onResize(oldValue, newValue);
            if (!isSorted) {
                Collections.sort(listeners);
            }
            Listener temp = null;
            for (Listener listener : listeners) {
                if (newValue.doubleValue() > listener.value) {
                    temp = listener;
                }
            }
            if (temp != null) {
                temp.listener.changed(observable, oldValue, newValue);
            }
        });
    }

    public void updateWidthConstaints(double before, double after) {
        double rate = after / before;
        pane.getChildren().stream().map((node) -> {
            if (AnchorPane.getRightAnchor(node) != null) {
                AnchorPane.setRightAnchor(node, AnchorPane.getRightAnchor(node) * rate);
            }
            return node;
        }).forEachOrdered((node) -> {
            if (AnchorPane.getLeftAnchor(node) != null) {
                AnchorPane.setLeftAnchor(node, AnchorPane.getLeftAnchor(node) * rate);
            }
        });
    }

    abstract void onResize(Number oldValue, Number newValue);

    public void UpTo(double val, ChangeListener<Number> listener) {
        listeners.add(new Listener(val, listener));
    }

    public static class Listener implements Comparable {

        private final double value;
        private final ChangeListener<Number> listener;

        public Listener(double value, ChangeListener<Number> listener) {
            this.listener = listener;
            this.value = value;

        }

        @Override
        public int compareTo(Object o) {
            return (int) ((this.value - ((Listener) o).value) * 100);
        }
    }

    public ChangeListener cols(double width, int numCols, double hieght, double margin) {
        ObservableList<Node> o = this.pane.getChildren();
        ChangeListener<Number> toReturn = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double nVal = ((Number) newValue).doubleValue();
                int numRow = 0;
                double slice = nVal / numCols;
                int count = 0;
                while (count < o.size()) {
                    double currHieght = hieght * numRow;
                    for (int i = 0; i < numCols && (numCols * numRow + i) < o.size();) {
                        count++;

                        ((VBox) (pane.getParent().getParent())).setPrefHeight(Math.max(670, 250 + currHieght));
                        ((VBox) (pane.getParent().getParent())).setMaxHeight(Math.max(670, 250 + currHieght));
                        ((VBox) (pane.getParent().getParent())).setMinHeight(Math.max(670, 250 + currHieght));
                        ((AnchorPane) (pane.getParent())).setPrefHeight(((AnchorPane) (pane.getParent())).getHeight() + currHieght + hieght);
                        ((AnchorPane) (pane.getParent())).setMinHeight(((AnchorPane) (pane.getParent())).getHeight() + currHieght + hieght);
                        ((AnchorPane) (pane.getParent())).setMaxHeight(((AnchorPane) (pane.getParent())).getHeight() + currHieght + hieght);
                        pane.setMinHeight(currHieght + hieght);
                        pane.setMaxHeight(currHieght + hieght);

                        AnchorPane.setLeftAnchor(o.get(numCols * numRow + i), slice * i + margin / 2);
                        AnchorPane.setTopAnchor(o.get(numCols * numRow + i), currHieght);
                        AnchorPane.setRightAnchor(o.get(numCols * numRow + i), slice * (numCols - ++i) + margin / 2);
                        pane.setPrefHeight(currHieght + hieght);
                    }
                    numRow++;
                }
            }
        };
        UpTo(width, toReturn);
        return toReturn;
    }
}
