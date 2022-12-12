package agh.ics.oop.gui;

import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.models.GrassField;
import agh.ics.oop.models.MapBoundary;
import agh.ics.oop.models.Vector2d;
import agh.ics.oop.tools.SimulationEngine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static agh.ics.oop.config.GuiConfig.*;
import static agh.ics.oop.tools.OptionParser.parse;

public class App extends Application {

    private IEngine engine;

    private IWorldMap map;
    private MapBoundary boundary;
    private GridPane grid;
    private HBox hBox;

    Button button1;
    Button button2;
    TextField textField;

    @Override
    public void start(Stage primaryStage) {
        draw();
        Scene scene = new Scene(hBox, WINDOW_WIDTH, WINDOW_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init() {
        // app logic
        hBox = new HBox();
        grid = new GridPane();
        hBox.getChildren().add(vBox());
        boundary = new MapBoundary();
        map = new GrassField(boundary, GRASS_COUNT);
    }
    public void draw(){
        Platform.runLater(() -> {
            hBox.getChildren().remove(grid);
            updateMap();
            hBox.getChildren().add(grid);
        });
    }
    private void updateMap() {
        grid.getChildren().clear();

        var boundaries = boundary.getBoundaries();
        grid.add(new Label("y / x"), 0, 0);

        var leftBottom = boundaries.get(0);
        var topRight = boundaries.get(1);

        var rows = topRight.y - leftBottom.y + 2;
        var cols = topRight.x - leftBottom.x + 2;
        var rowConstr = new RowConstraints(TILE_SIZE);
        rowConstr.setValignment(VPos.CENTER);
        var colConstr = new ColumnConstraints(TILE_SIZE);
        colConstr.setHalignment(HPos.CENTER);
        grid.setGridLinesVisible(true);
        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(TILE_SIZE);
            row.setValignment(VPos.CENTER);
            grid.getRowConstraints().add(row);

        }
        for (int i = 0; i < cols; i++) {
            ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
            column.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(column);
        }
        for (int i = leftBottom.x; i < topRight.x + 1; i++) {
            grid.add(new Label("%d".formatted(i)), i - leftBottom.x + 1, 0);
        }

        for (int i = leftBottom.y; i < topRight.y + 1; i++) {
            grid.add(new Label("%d".formatted(i)), 0, topRight.y - i + 1);
        }

        for (int x = leftBottom.x; x < topRight.x + 1; x++) {
            for (int y = leftBottom.y; y < topRight.y + 1; y++) {
                var entity = map.objectAt(new Vector2d(x, y));
                if (entity != null) {
                    GuiElementBox element = new GuiElementBox((IMapElement) entity);
                    try {
                        grid.add(element.createGUIElement(), (x + 1) - leftBottom.x, (topRight.y - y + 1));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }


    public void restart() {
        button1.setDisable(false);
        button1.setText("Start simulation");
        textField.clear();
        textField.setDisable(false);
        grid = new GridPane();
        grid.getChildren().clear();
        hBox.getChildren().clear();
        hBox.getChildren().add(vBox());
        boundary = new MapBoundary();
        map = new GrassField(boundary, GRASS_COUNT);
        draw();
    }

    private void run() throws InterruptedException {
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(1, 1)};
        engine = new SimulationEngine(map, boundary, positions);
        engine.setGUIObserver(this);
        engine.setDirections(parse(Arrays.stream(textField.getText().trim().split(" ")).toList()));
        Thread engineThread = new Thread((Runnable) engine);
        engineThread.start();
        button2.setDisable(false);
    }

    private VBox vBox() {
        prepareButtons();
        prepareTextField();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(button1, button2, textField);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(10, 50, 50, 50));
        return vBox;
    }

    private void prepareButtons() {
        Button button = new Button();
        button.setMinWidth(150);
        button.setMinHeight(30);
        button.setText("Start simulation");
        button.setOnAction(event -> {
            button.setText("Started...");
            button.setDisable(true);
            textField.setDisable(true);
            try {
                run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        this.button1 = button;

        button2 = new Button();
        button2.setText("Restart");
        button2.setMinWidth(150);
        button2.setMinHeight(30);
        button2.setDisable(true);
        button2.setOnAction(event ->
            restart()
        );
    }

    private void prepareTextField() {
        TextField textField = new TextField();
        textField.setMinWidth(400);
        this.textField = textField;
    }
}
