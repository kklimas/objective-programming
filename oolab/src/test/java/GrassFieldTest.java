import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.models.Animal;
import agh.ics.oop.models.GrassField;
import agh.ics.oop.models.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {

    private IWorldMap map;
    private Vector2d position;
    private Animal sheepDolly;

    @BeforeEach
    public void setup() {
        map = new GrassField(10);
        position = new Vector2d(0, 0);
        sheepDolly = new Animal(position, map);
        map.place(sheepDolly);
    }

    @Test
    void canMoveTo() {
        assertFalse(map.canMoveTo(position));
    }

    @Test
    void place() {
        assertEquals(map.objectAt(position), sheepDolly);
    }

    @Test
    void isOccupied() {
        assertTrue(map.isOccupied(position));
    }

    @Test
    void objectAt() {
        assertEquals(Animal.class, map.objectAt(position).getClass());
        assertEquals(sheepDolly, map.objectAt(position));
    }

    @Test
    void update() {
        var v = new Vector2d(1, 1);
        sheepDolly.setPosition(v);
        map.update(sheepDolly);
        assertEquals(sheepDolly, map.objectAt(v));
    }
}
