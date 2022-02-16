package de.geomagic.model;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import lombok.Value;

@Value
public class Line {
    Point2D p1, p2;

    public Line(double x1, double y1, double x2, double y2){
       this.p1 = new Point2D(x1, y1);
       this.p2 = new Point2D(x2, y2);
    }

    public Line(Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double calcEuclideanDistance() {
        double xterm = square(p2.getX(), p1.getX());
        double yterm = square(p2.getY(), p1.getY());
        return sqrt(xterm + yterm);
    }

    private double square(double p2, double p1) {
        return pow(p2 - p1, 2);
    }
}
