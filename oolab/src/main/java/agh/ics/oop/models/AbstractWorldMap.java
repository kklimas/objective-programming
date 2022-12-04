package agh.ics.oop.models;

import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.tools.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected Map<Vector2d, IMapElement> entities = new HashMap<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || objectAt(position).getClass().equals(Grass.class);
    }

    @Override
    public boolean place(IMapElement mapElement) {
        if (!isOccupied(mapElement.getPosition())
                || objectAt(mapElement.getPosition()).getClass().equals(Grass.class)) {
            entities.put(mapElement.getPosition(), mapElement);
            return true;
        }
        throw new IllegalArgumentException("Animal cannot be placed at " + mapElement.getPosition().toString());
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
    public void positionChanged(Vector2d oldPos, Vector2d newPos) {
        var entity = entities.remove(oldPos);
        entity.setPosition(newPos);
        entities.put(newPos, entity);
    }

    @Override
    public void stateChanged(IMapElement entity) {
        entities.remove(entity.getPosition());
        entities.put(entity.getPosition(), entity);
    }

    public String toString(Vector2d v1, Vector2d v2) {
        return visualizer.draw(v1, v2);
    }

}
