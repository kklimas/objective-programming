package agh.ics.oop.interfaces;

import agh.ics.oop.models.Animal;
import agh.ics.oop.models.Vector2d;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPos, Vector2d newPos);

    // for direction changes
    void stateChanged(IMapElement entity);
}
