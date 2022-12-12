package agh.ics.oop.interfaces;

import agh.ics.oop.models.Vector2d;
import javafx.scene.layout.VBox;

public interface IMapElement {
    String SRC_PATH = "src/main/resources/%s.png";
    Vector2d getPosition();
    void setPosition(Vector2d pos);
    String getImagePath();
}
