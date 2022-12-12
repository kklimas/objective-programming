package agh.ics.oop.models;

public class Grass extends AbstractMapElement{
    private final Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getImagePath() {
        return SRC_PATH.formatted("grass");
    }

    @Override
    public String toString() {
        return super.toString("*");
    }
}
