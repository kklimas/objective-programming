package agh.ics.oop.tools;

import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.models.Vector2d;

import java.util.Comparator;

public class CoordinatesComparator implements Comparator<Vector2d> {
    private final boolean isXCord;

    public CoordinatesComparator(boolean isXCord) {
        this.isXCord = isXCord;
    }

    @Override
    public int compare(Vector2d v1, Vector2d v2) {
        if (v1.equals(v2)) return 0;

        if (isXCord) {
            return compareXCord(v1, v2);
        }
        return compareYCord(v1, v2);
    }

    private int compareXCord(Vector2d v1, Vector2d v2) {
        if (v1.x > v2.x) {
            return 1;
        } else if (v1.x < v2.x) {
            return -1;
        }
        if (v1.y > v2.y) {
            return 1;
        }
        return -1;
    }

    private int compareYCord(Vector2d v1, Vector2d v2) {
        if (v1.y > v2.y) {
            return 1;
        } else if (v1.y < v2.y) {
            return -1;
        }
        if (v1.x > v2.x) {
            return 1;
        }
        return -1;
    }
}
