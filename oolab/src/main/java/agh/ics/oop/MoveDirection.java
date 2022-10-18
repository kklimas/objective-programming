package agh.ics.oop;

public enum MoveDirection {
    FORWARD("Animal is going forward"),
    BACKWARD("Animal is going backward"),
    RIGHT("Animal is going right"),
    LEFT("Animal is going left");

    private final String value;

    MoveDirection(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
