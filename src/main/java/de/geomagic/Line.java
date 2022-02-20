package de.geomagic;

import de.geomagic.model.ConnectedLine;
import de.geomagic.model.Point2D;
import de.geomagic.model.SimpleLine;
import de.geomagic.types.OrderAgnosticPair;

import java.util.Set;

public interface Line {
    OrderAgnosticPair<Point2D, Point2D> getEndpoints();
    ConnectedLine add(Line s);
    Set<SimpleLine> asSet();
    double length();
}
