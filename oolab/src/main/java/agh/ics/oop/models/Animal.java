package agh.ics.oop.models;

import agh.ics.oop.enums.MapDirection;
import agh.ics.oop.enums.MoveDirection;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMapElement{

    private final List<IPositionChangeObserver> observers = new ArrayList<>();
    private MapDirection mapDirection = MapDirection.NORTH;

    private final IWorldMap map;

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

    @Override
    public String getImagePath() {

        return switch (mapDirection) {
            case NORTH -> SRC_PATH.formatted("up");
            case EAST -> SRC_PATH.formatted("right");
            case WEST -> SRC_PATH.formatted("left");
            default -> SRC_PATH.formatted("down");
        };
    }

    public void setMapDirection(MapDirection mapDirection) {
        this.mapDirection = mapDirection;
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPos, Vector2d newPos) {
        observers.forEach(observer -> observer.positionChanged(oldPos, newPos));
    }

    public void stateChanged() {
        observers.forEach(observer -> observer.stateChanged(this));
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> {
                setMapDirection(mapDirection.next());
                stateChanged();
            }
            case LEFT -> {
                setMapDirection(mapDirection.previous());
                stateChanged();
            }
            case FORWARD -> {
                var newPosition = position.add(mapDirection.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    positionChanged(position, newPosition);
                    setPosition(newPosition);
                }
            }
            case BACKWARD -> {
                var newPosition = position.subtract(mapDirection.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    positionChanged(position, newPosition);
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
