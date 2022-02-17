package de.geomagic;

import de.geomagic.model.Point2D;
import de.geomagic.types.OrderAgnosticPair;

public interface Line {
    OrderAgnosticPair<Point2D, Point2D> getEndpoints();
    double length();
}
