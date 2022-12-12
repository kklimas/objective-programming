package agh.ics.oop.gui;

import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.models.Animal;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static agh.ics.oop.config.GuiConfig.*;

public class GuiElementBox {
    private final IMapElement iMapElement;

    public GuiElementBox(IMapElement iMapElement) {
        this.iMapElement = iMapElement;
    }

    public VBox createGUIElement() throws FileNotFoundException {

        VBox vbox = new VBox();

        if (iMapElement.getClass().equals(Animal.class)) {
            Image image = new Image(new FileInputStream(iMapElement.getImagePath()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(IMAGE_WIDTH);
            imageView.setFitHeight(IMAGE_HEIGHT);
            vbox.getChildren().add(imageView);
            vbox.getChildren().add(new Label(iMapElement.getPosition().toString()));
            vbox.setAlignment(Pos.CENTER);
        } else {
            vbox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        return vbox;
    }
}
