import agh.ics.oop.enums.MoveDirection;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.models.RectangularMap;
import agh.ics.oop.models.Vector2d;
import agh.ics.oop.tools.MapVisualizer;
import agh.ics.oop.tools.SimulationEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import static agh.ics.oop.tools.OptionParser.parse;

public class EngineIntegrationTest {

    private IEngine engine;
    private IWorldMap map;
    private Vector2d[] positions;
    private String[] directions;
    private List<MoveDirection> parsedDirections;

    @Test
    void case1() {
        var a1 = new Vector2d(0, 0);
        directions = new String[]{"f", "b", "f", "a", "l", "l", "f", "f"};
        parsedDirections = parse(directions);
        map = new RectangularMap(10, 5);
        positions = new Vector2d[] { a1 };
        engine = new SimulationEngine(parsedDirections, map, positions);
        engine.run();
        assertNotNull(map.objectAt(a1));
    }

    @Test
    void case2() {
        var a1 = new Vector2d(0, 0);
        var a2 = new Vector2d(1, 0);
        directions = new String[]{"r", "l", "f"};
        parsedDirections = parse(directions);
        map = new RectangularMap(2, 2);
        positions = new Vector2d[] { a1, a2 };
        engine = new SimulationEngine(parsedDirections, map, positions);
        engine.run();

        var visualizer = new MapVisualizer(map);
        visualizer.draw(new Vector2d(0, 0), new Vector2d(10, 5));

        assertNotNull(map.objectAt(a1));
        assertNotNull(map.objectAt(a2));
    }
}
