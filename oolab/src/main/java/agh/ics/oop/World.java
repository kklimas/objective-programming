package agh.ics.oop;

import java.util.Arrays;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start\n");

        // lab1
        run(directionMapper(args));

        // lab2
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println("\nStop");
    }

    public static void run(List<MoveDirection> directions) {
        directions.forEach(direction -> System.out.println(direction.toString()));
    }

    public static List<MoveDirection> directionMapper(String[] stringDirections) {
        return Arrays.stream(stringDirections).map(direction ->
                switch (direction) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    default -> MoveDirection.LEFT;
                }
        ).toList();
    }
}