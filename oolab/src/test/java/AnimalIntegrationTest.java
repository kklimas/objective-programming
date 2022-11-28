import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.models.Animal;
import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.enums.MoveDirection;
import agh.ics.oop.models.RectangularMap;
import agh.ics.oop.models.Vector2d;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static agh.ics.oop.tools.OptionParser.parse;

public class AnimalIntegrationTest {

    private Animal sheepDolly;
    private static List<MoveDirection> d1;
    private static List<MoveDirection> d2;
    private static List<MoveDirection> d3;
    private static IWorldMap map;

    @BeforeAll
    public static void initializeDirections() {
        map = new RectangularMap(4, 4);
        d1 = List.of(
                MoveDirection.LEFT,
                MoveDirection.LEFT,
                MoveDirection.LEFT,
                MoveDirection.LEFT
        );
        d2 = List.of(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT
        );
        d3 = List.of(
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        );
    }

    @BeforeEach
    public void setup() {
        initializeAnimal();
    }
    public void initializeAnimal() {
        sheepDolly = new Animal(new Vector2d(0, 0), map);
    }

    @Test
    public void orientationTest() {
        sheepDolly.move(d1);
        assertEquals(MapDirection.NORTH, sheepDolly.getMapDirection());

        sheepDolly.move(d2);
        assertEquals(MapDirection.SOUTH, sheepDolly.getMapDirection());

        sheepDolly.move(d3);
        assertEquals(MapDirection.EAST, sheepDolly.getMapDirection());
    }

    @Test
    public void positioningTest() {
        sheepDolly.move(d1);
        System.out.println(sheepDolly.getPosition());
        assertTrue(sheepDolly.isAt(new Vector2d(0, 0)));

        sheepDolly.move(d2);
        assertTrue(sheepDolly.isAt(new Vector2d(0, 1)));

        sheepDolly.move(d3);
        assertTrue(sheepDolly.isAt(new Vector2d(0, 2)));
    }

    @Test
    public void mapBorderTest() {
        var directions1 = List.of(
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD
        );
        var directions2 = List.of(
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD
        );
        var directions3 = List.of(
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD
        );
        sheepDolly.move(directions1);
        assertFalse(isOutOfMap(sheepDolly.getPosition()));

//        sheepDolly.move(directions2);
//        assertFalse(isOutOfMap(sheepDolly.getPosition()));
//
//        sheepDolly.move(directions3);
//        assertFalse(isOutOfMap(sheepDolly.getPosition()));
    }

    @Test
    public void parseToolTest() {
        String[] data1 = new String[0];
        String[] data2 = new String[]{"forward", "b", "ri", "eft", "e", "f"};
        String[] data3 = new String[]{"a", "bac", "c", "d", "e", "backward"};

        var res1 = parse(data1);
        var res2 = parse(data2);
        var res3 = parse(data3);

        assertEquals(0, res1.size());
        assertEquals(3, res2.size());
        assertEquals(1, res3.size());

        res2.forEach(direction -> assertEquals(MoveDirection.class, direction.getClass()));
        res3.forEach(direction -> assertEquals(MoveDirection.class, direction.getClass()));
    }
    private boolean isOutOfMap(Vector2d position) {
        var leftBottom = new Vector2d(0, 0);
        var topRight = new Vector2d(4, 4);
        return !position.follows(leftBottom) || !position.precedes(topRight);
    }
}
