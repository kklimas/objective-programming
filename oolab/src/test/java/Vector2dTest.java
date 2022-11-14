import agh.ics.oop.models.Vector2d;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    static Random random = new Random();
    static Vector2d v1;
    static Vector2d v2;
    static Vector2d v3;

    private static int randomInt() {
        // random integer in range [-100, 100]
        return random.nextInt(201) - 100;
    }

    @BeforeAll
    public static void initVectors() {
        v1 = new Vector2d(0, 0);
        v2 = new Vector2d(randomInt(), randomInt());
        v3 = new Vector2d(randomInt(), randomInt());
    }

    @Test
    public void equals() {
        // method Vector2d.equals(Object object) will be called
        // during the comparison
        v2 = new Vector2d(2, 9);
        v3 = new Vector2d(2, 9);

        assertEquals(v1, v1);
        assertEquals(v2, v3);
        assertNotEquals(v1, v2);
    }

    @Test
    public void toStringTest() {
        var result1 = "(%d, %d)".formatted(v1.x, v1.y);
        var result2 = "(%d, %d)".formatted(v2.x, v2.y);
        var result3 = "(%d, %d)".formatted(v3.x, v3.y);

        assertEquals(result1, v1.toString());
        assertEquals(result2, v2.toString());
        assertEquals(result3, v3.toString());
    }

    @Test
    public void precedes() {
        var result1 = v1.x <= v2.x && v1.y <= v2.y;
        var result2 = v2.x <= v3.x && v2.y <= v3.y;
        var result3 = v3.x <= v1.x && v3.y <= v1.y;

        assertEquals(result1, v1.precedes(v2));
        assertEquals(result2, v2.precedes(v3));
        assertEquals(result3, v3.precedes(v1));
    }

    @Test
    public void follows() {
        var result1 = v1.x >= v2.x && v1.y >= v2.y;
        var result2 = v2.x >= v3.x && v2.y >= v3.y;
        var result3 = v3.x >= v1.x && v3.y >= v1.y;

        assertEquals(result1, v1.follows(v2));
        assertEquals(result2, v2.follows(v3));
        assertEquals(result3, v3.follows(v1));
    }

    @Test
    public void upperRight() {
        var result1 = new Vector2d(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y));
        var result2 = new Vector2d(Math.max(v2.x, v3.x), Math.max(v2.y, v3.y));
        var result3 = new Vector2d(Math.max(v3.x, v1.x), Math.max(v3.y, v1.y));

        assertEquals(result1, v1.upperRight(v2));
        assertEquals(result2, v2.upperRight(v3));
        assertEquals(result3, v3.upperRight(v1));
    }

    @Test
    public void lowerLeft() {
        var result1 = new Vector2d(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y));
        var result2 = new Vector2d(Math.min(v2.x, v3.x), Math.min(v2.y, v3.y));
        var result3 = new Vector2d(Math.min(v3.x, v1.x), Math.min(v3.y, v1.y));

        assertEquals(result1, v1.lowerLeft(v2));
        assertEquals(result2, v2.lowerLeft(v3));
        assertEquals(result3, v3.lowerLeft(v1));
    }

    @Test
    public void add() {
        var result1 = new Vector2d(v1.x + v2.x, v1.y + v2.y);
        var result2 = new Vector2d(v2.x + v3.x, v2.y + v3.y);
        var result3 = new Vector2d(v3.x + v1.x, v3.y + v1.y);

        assertEquals(result1, v1.add(v2));
        assertEquals(result2, v2.add(v3));
        assertEquals(result3, v3.add(v1));
    }

    @Test
    public void subtract() {
        var result1 = new Vector2d(v1.x - v2.x, v1.y - v2.y);
        var result2 = new Vector2d(v2.x - v3.x, v2.y - v3.y);
        var result3 = new Vector2d(v3.x - v1.x, v3.y - v1.y);

        assertEquals(result1, v1.subtract(v2));
        assertEquals(result2, v2.subtract(v3));
        assertEquals(result3, v3.subtract(v1));
    }

    @Test
    public void opposite() {
        assertEquals(v1, v1.opposite());
        assertEquals(new Vector2d(-v2.x, -v2.y), v2.opposite());
        assertEquals(new Vector2d(-v3.x, -v3.y), v3.opposite());
    }
}
