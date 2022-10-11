package agh.ics.oop;

public enum Direction {
    FORWARD("Zwierzak idzie do przodu"),
    BACKWARD("Zwierzak idzie do tylu"),
    RIGHT("Zwierzak idzie w prawo"),
    LEFT("Zwierzak idzie w lewo");

    private final String value;

    Direction(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
