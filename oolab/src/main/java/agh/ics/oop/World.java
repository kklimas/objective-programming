package agh.ics.oop;
import agh.ics.oop.enums.MoveDirection;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.models.GrassField;
import agh.ics.oop.models.Vector2d;
import agh.ics.oop.tools.SimulationEngine;

import java.util.List;

import static agh.ics.oop.tools.OptionParser.parse;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(0,0)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
    }
}