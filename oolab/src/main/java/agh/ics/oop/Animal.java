package agh.ics.oop;

import java.util.List;

public class Animal {
    private MapDirection mapDirection;
    private Vector2d position;

    private final Vector2d topRight = new Vector2d(4, 4);
    private final Vector2d leftBottom = new Vector2d(0, 0);

    public Animal() {
        this.mapDirection = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
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
            case RIGHT -> setMapDirection(mapDirection.next());
            case LEFT -> setMapDirection(mapDirection.previous());
            case FORWARD -> {
                var newPosition = position.add(mapDirection.toUnitVector());
                if (canMove(newPosition)) {
                    setPosition(newPosition);
                }
            }
            case BACKWARD -> {
                var newPosition = position.subtract(mapDirection.toUnitVector());
                if (canMove(newPosition)) {
                    setPosition(newPosition);
                }
            }
        }
    }
    private boolean canMove(Vector2d newPosition) {
        return newPosition.follows(leftBottom) && newPosition.precedes(topRight);
    }

    public void move(List<MoveDirection> directions) {
        directions.forEach(this::move);
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    @Override
    public String toString() {
        return "\nMap Direction: " + mapDirection +
                "\nPosition: " + position;
    }
}
