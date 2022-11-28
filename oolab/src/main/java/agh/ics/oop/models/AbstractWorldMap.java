package agh.ics.oop.models;

import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.tools.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap {

    protected Map<Vector2d, IMapElement> entities = new HashMap<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || objectAt(position).getClass().equals(Grass.class);
    }
    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            entities.put(animal.getPosition(), animal);
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
        return entities.get(position);
    }

    @Override
    public void update(Vector2d oldPos, Vector2d newPos) {
        var entity = entities.remove(oldPos);
        entities.put(newPos, entity);
    }

    @Override
    public void update(Animal animal) {
        entities.put(animal.getPosition(), animal);
    }

    public String toString(Vector2d v1, Vector2d v2) {
        return visualizer.draw(v1, v2);
    }

}
