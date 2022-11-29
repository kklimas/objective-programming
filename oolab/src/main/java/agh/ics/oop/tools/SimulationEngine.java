package agh.ics.oop.tools;

import agh.ics.oop.enums.MoveDirection;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.models.Animal;
import agh.ics.oop.models.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    private final List<MoveDirection> directions;
    private final IWorldMap map;
    private final Vector2d[] positions;
    private final List<Animal> animals = new ArrayList<>();

    public SimulationEngine(List<MoveDirection> directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }

    @Override
    public void run() {
        // place animals on map
        place();
        // move animals
        for (int i = 0; i < directions.size(); i++) {
            System.out.println(map);
            animals.get(i % animals.size()).move(directions.get(i));
        }
    }

    private void place() {
        for (Vector2d position: positions) {
            var animal = new Animal(position, map);
            animal.addObserver((IPositionChangeObserver) map);
            animals.add(animal);
            map.place(new Animal(position, map));
        }
    }
}
