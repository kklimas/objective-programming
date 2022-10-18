package agh.ics.oop;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;

    public String toString(){
        return switch(this) {
            case NORTH -> "North";
            case SOUTH -> "South";
            case WEST -> "West";
            default -> "East";
        };
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
}
