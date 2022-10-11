package agh.ics.oop;

import java.util.Arrays;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start\n");
        var directions = directionMapper(args);
        run(directions);
        System.out.println("\nStop");
    }

    public static void run(List<Direction> directions) {
        directions.forEach(direction -> System.out.println(direction.toString()));
    }

    public static List<Direction> directionMapper(String[] stringDirections) {
        return Arrays.stream(stringDirections).map(direction ->
                switch (direction) {
                    case "f" -> Direction.FORWARD;
                    case "b" -> Direction.BACKWARD;
                    case "r" -> Direction.RIGHT;
                    default -> Direction.LEFT;
                }
        ).toList();
    }
}