package agh.ics.oop.models;

import agh.ics.oop.interfaces.IMapElement;

public class GrassField extends AbstractWorldMap {

    private final int grassCount;
    private final MapBoundary boundary;

    public GrassField(MapBoundary boundary, int grassCount) {
        this.grassCount = grassCount;
        this.boundary = boundary;
        placeGrasses();
    }

    private void placeGrasses() {
        for (int i = 0; i < grassCount; i++) {
            generateGrass();
        }
    }

    private void generateGrass() {
        var taken = true;
        var max = Math.sqrt(grassCount * 10);
        var min = 0;
        while (taken) {
            var x = (int)Math.floor(Math.random()*(max-min+1)+min);
            var y = (int)Math.floor(Math.random()*(max-min+1)+min);
            var v = new Vector2d(x, y);
            if (objectAt(v) == null) {
                taken = false;
                var grass = new Grass(v);
                boundary.place(grass);
                this.entities.put(v, grass);
            }
        }
    }


    @Override
    public boolean place(IMapElement mapElement) {
        boundary.place(mapElement);
        return super.place(mapElement);
    }

    @Override
    public void positionChanged(Vector2d oldPos, Vector2d newPos) {
        if (entities.get(newPos) != null && entities.get(newPos).getClass().equals(Grass.class)) {
            generateGrass();
        }
        super.positionChanged(oldPos, newPos);
    }

    @Override
    public String toString() {
        var coordinates = boundary.getBoundaries();
        return super.toString(coordinates.get(0), coordinates.get(1));
    }
}
