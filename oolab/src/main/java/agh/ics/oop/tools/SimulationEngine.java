package agh.ics.oop.tools;

import agh.ics.oop.enums.MoveDirection;
import agh.ics.oop.gui.App;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.models.*;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.config.GuiConfig.MOVE_DELAY;

public class SimulationEngine implements IEngine, Runnable {
    private List<MoveDirection> directions;
    private final IWorldMap map;
    private final Vector2d[] positions;
    private App gui;
    private final List<Animal> animals = new ArrayList<>();
    private MapBoundary boundary;

    public SimulationEngine(IWorldMap map, MapBoundary boundary, Vector2d[] positions) {
        this.map = map;
        this.boundary = boundary;
        this.positions = positions;
    }

    @Override
    public void run() {
        // place animals on map
        place();
        // move animals
        for (int i = 0; i < directions.size(); i++) {
            try {
                animals.get(i % animals.size()).move(directions.get(i));
                if (gui != null) {
                    gui.draw();
                    Thread.sleep(MOVE_DELAY);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void place() {
        for (Vector2d position: positions) {
            var animal = new Animal(position, map);
            animal.addObserver((IPositionChangeObserver) map);
            if (!map.getClass().equals(RectangularMap.class)) animal.addObserver(boundary);
            animals.add(animal);
            map.place(new Animal(position, map));
        }
    }

    public void setDirections(List<MoveDirection> directions) {
        this.directions = directions;
    }

    @Override
    public void setGUIObserver(App app) {
        this.gui = app;
    }
}
