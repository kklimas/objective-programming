package agh.ics.oop.tools;

import agh.ics.oop.enums.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionParser {
    public static ArrayList<MoveDirection> parse(List<String> strings) {
        var result = new ArrayList<MoveDirection>();
        strings.forEach(string -> {
            switch (string) {
                case "f", "forward" -> result.add(MoveDirection.FORWARD);
                case "b", "backward" -> result.add(MoveDirection.BACKWARD);
                case "r", "right" -> result.add(MoveDirection.RIGHT);
                case "l", "left" -> result.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(string + " is not legal move specification.");
            }
        });
        return result;
    }
}
