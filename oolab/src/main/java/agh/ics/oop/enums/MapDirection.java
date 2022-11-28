package agh.ics.oop.enums;

import agh.ics.oop.models.Vector2d;

public enum MapDirection {
    NORTH("^"),
    SOUTH("v"),
    WEST("<"),
    EAST(">");

    private final String value;

    MapDirection(String value) {
        this.value = value;
    }

    public MapDirection next() {
        return switch (this) {
            case NORTH -> MapDirection.EAST;
            case EAST -> MapDirection.SOUTH;
            case SOUTH -> MapDirection.WEST;
            default -> MapDirection.NORTH;
        };
    }

    public MapDirection previous() {
        return switch (this) {
            case NORTH -> MapDirection.WEST;
            case WEST -> MapDirection.SOUTH;
            case SOUTH -> MapDirection.EAST;
            default -> MapDirection.NORTH;
        };
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2d(0, 1);
            case WEST -> new Vector2d(-1, 0);
            case SOUTH -> new Vector2d(0, -1);
            default -> new Vector2d(1, 0);
        };
    }

    @Override
    public String toString(){
        return value;
    }
}
