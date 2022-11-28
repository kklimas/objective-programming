package agh.ics.oop.models;

import agh.ics.oop.interfaces.IMapElement;

abstract class AbstractMapElement implements IMapElement {
    protected Vector2d position;

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public String toString(String sign) {
        return sign;
    }
}
