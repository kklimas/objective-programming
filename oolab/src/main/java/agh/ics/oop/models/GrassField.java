package agh.ics.oop.models;

public class GrassField extends AbstractWorldMap {

    private final int grassCount;

    public GrassField(int grassCount) {
        this.grassCount = grassCount;
        placeGrasses();
    }

    @Override
    public void update(Vector2d oldPos, Vector2d newPos) {
        if (objectAt(newPos) != null && objectAt(newPos).getClass().equals(Grass.class)) {
            generateGrass();
        }
        super.update(oldPos, newPos);
    }

    @Override
    public void update(Animal animal) {
        if (objectAt(animal.getPosition()) != null
                && objectAt(animal.getPosition()).getClass().equals(Grass.class)) {
            generateGrass();
        }
        super.update(animal);
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
                this.entities.put(v, new Grass(v));
            }
        }
    }

    @Override
    public String toString() {
        // calculate borders
        var xVal = entities.keySet().stream()
                .map(obj -> obj.x)
                .sorted()
                .toList();
        var yVal = entities.keySet().stream()
                .map(obj -> obj.y)
                .sorted()
                .toList();

        return super.toString(
                new Vector2d(xVal.get(0), yVal.get(0)),
                new Vector2d(xVal.get(xVal.size()-1), yVal.get(yVal.size()-1)));
    }
}
