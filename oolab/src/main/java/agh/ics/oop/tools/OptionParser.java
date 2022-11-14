package agh.ics.oop.tools;

import agh.ics.oop.enums.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionParser {
    public static ArrayList<MoveDirection> parse(String[] strings) {
        var result = new ArrayList<MoveDirection>();
        for (String el: strings) {
            switch (el) {
                case "f", "forward" -> result.add(MoveDirection.FORWARD);
                case "b", "backward" -> result.add(MoveDirection.BACKWARD);
                case "r", "right" -> result.add(MoveDirection.RIGHT);
                case "l", "left" -> result.add(MoveDirection.LEFT);
            }
        }
        return result;
    }
}
