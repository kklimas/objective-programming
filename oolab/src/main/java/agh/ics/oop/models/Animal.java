package agh.ics.oop.models;

import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.enums.MoveDirection;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.List;

public class Animal extends AbstractMapElement{
    private MapDirection mapDirection = MapDirection.NORTH;

    private final IWorldMap map;

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(Vector2d position, IWorldMap map) {
        this.position = position;
        this.map = map;
    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setMapDirection(MapDirection mapDirection) {
        this.mapDirection = mapDirection;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> {
                setMapDirection(mapDirection.next());
                map.update(this);
            }
            case LEFT -> {
                setMapDirection(mapDirection.previous());
                map.update(this);
            }
            case FORWARD -> {
                var newPosition = position.add(mapDirection.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    map.update(position, newPosition);
                    setPosition(newPosition);
                }
            }
            case BACKWARD -> {
                var newPosition = position.subtract(mapDirection.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    map.update(position, newPosition);
                    setPosition(newPosition);
                }
            }
        }
    }

    public void move(List<MoveDirection> directions) {
        directions.forEach(this::move);
    }

    @Override
    public String toString() {
        return super.toString(mapDirection.toString());
    }
}
