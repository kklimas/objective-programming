package agh.ics.oop.models;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private boolean positionInBounds(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width - 1, height - 1));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && positionInBounds(position);
    }

    @Override
    public String toString() {
        return super.toString(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }
}
