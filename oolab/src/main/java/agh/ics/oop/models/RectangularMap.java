package agh.ics.oop.models;

import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.tools.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;
    public final Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer visualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && isPositionValid(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public void update(Vector2d oldPos, Vector2d newPos) {
        var animal = animals.remove(oldPos);
        animals.put(newPos, animal);
    }

    @Override
    public void update(Animal animal) {
        animals.put(animal.getPosition(), animal);
    }



    private boolean isPositionValid(Vector2d position) {
        var leftBottom = new Vector2d(0, 0);
        var topRight = new Vector2d(width - 1, height - 1);

        return position.follows(leftBottom) && position.precedes(topRight);
    }

    public Map<Vector2d, Animal> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }
}
