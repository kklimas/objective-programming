package agh.ics.oop.gui;

import agh.ics.oop.enums.MoveDirection;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.models.GrassField;
import agh.ics.oop.models.MapBoundary;
import agh.ics.oop.models.Vector2d;
import agh.ics.oop.tools.SimulationEngine;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

import static agh.ics.oop.tools.OptionParser.parse;

public class App extends Application {

    private IWorldMap map;
    private MapBoundary boundary;

    private int WIDTH = 800;
    private int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Zwierzak");
        var gridPane = draw();


        Scene scene = new Scene(gridPane, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init() {
        // app logic
        try {
            var args = getParameters().getRaw();
            List<MoveDirection> directions = parse(args);
            boundary = new MapBoundary();
            map = new GrassField(boundary, 10);
            Vector2d[] positions = { new Vector2d(0,0), new Vector2d(1, 1)};
            IEngine engine = new SimulationEngine(directions, map, boundary, positions);
            System.out.println(map.toString());
            engine.run();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private GridPane draw() {
        var grid = new GridPane();
        var boundaries = boundary.getBoundaries();
        grid.setGridLinesVisible(true);
        grid.add(new Label("y / x"), 0, 0);

        var leftBottom = boundaries.get(0);
        var topRight = boundaries.get(1);

        var rows = topRight.y - leftBottom.y + 2;
        var cols = topRight.x - leftBottom.x + 2;

        var rowConstr = new RowConstraints(HEIGHT / rows);
        rowConstr.setValignment(VPos.CENTER);
        var colConstr = new ColumnConstraints(WIDTH / cols);
        colConstr.setHalignment(HPos.CENTER);


        for (int i = leftBottom.x; i < topRight.x + 1; i++) {
            grid.add(new Label("%d".formatted(i)), i - leftBottom.x + 1, 0);
            grid.getRowConstraints().add(rowConstr);
            grid.getColumnConstraints().add(colConstr);
        }

        for (int i = leftBottom.y; i < topRight.y + 1; i++) {
            grid.add(new Label("%d".formatted(i)), 0, topRight.y - i + 1);
            grid.getRowConstraints().add(rowConstr);
            grid.getColumnConstraints().add(colConstr);
        }

        for (int x = leftBottom.x; x < topRight.x + 1; x++) {
            for (int y = leftBottom.y; y < topRight.y + 1; y++) {
                var entity = map.objectAt(new Vector2d(x, y));
                if (entity != null) {
                    grid.add(new Label(entity.toString()),x + 1, topRight.y - y + 1 );
                }
            }
        }

        return grid;
    }
}
