package agh.ics.oop.models;

import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.tools.CoordinatesComparator;

import java.util.List;
import java.util.TreeMap;

public class MapBoundary implements IPositionChangeObserver {

    TreeMap<Vector2d, IMapElement> xCords = new TreeMap<>(new CoordinatesComparator(true));
    TreeMap<Vector2d, IMapElement> yCords = new TreeMap<>(new CoordinatesComparator(false));

    @Override
    public void positionChanged(Vector2d oldPos, Vector2d newPos) {
        var entity1 = xCords.remove(oldPos);
        var entity2 = yCords.remove(oldPos);
        if (!entity1.equals(entity2)) {
            throw new IllegalArgumentException("Entities error in boundary map!");
        }
        entity1.setPosition(newPos);
        entity2.setPosition(newPos);
        xCords.put(newPos, entity1);
        yCords.put(newPos, entity2);
    }

    @Override
    public void stateChanged(IMapElement entity) {}

    public void place(IMapElement entity) {
        xCords.put(entity.getPosition(), entity);
        yCords.put(entity.getPosition(), entity);
    }

    public List<Vector2d> getBoundaries() {
        var x1 = xCords.firstKey().x;
        var x2 = xCords.lastKey().x;

        var y1 = yCords.firstKey().y;
        var y2 = yCords.lastKey().y;
        return List.of(new Vector2d(x1, y1), new Vector2d(x2, y2));
    }
}
