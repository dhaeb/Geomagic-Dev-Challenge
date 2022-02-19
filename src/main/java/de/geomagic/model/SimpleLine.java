package de.geomagic.model;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import de.geomagic.Line;
import de.geomagic.types.OrderAgnosticPair;
import lombok.Value;

import java.util.List;
import java.util.Objects;

@Value
public class SimpleLine implements Line {
    Point2D p1, p2;

    public SimpleLine(double x1, double y1, double x2, double y2){
       this.p1 = new Point2D(x1, y1);
       this.p2 = new Point2D(x2, y2);
    }

    public SimpleLine(Point2D p1, Point2D p2) {
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

    public List<Point2D> asPointList() {
        return List.of(p1, p2);
    }

    @Override
    public OrderAgnosticPair<Point2D, Point2D> getEndpoints() {
        return OrderAgnosticPair.of(p1, p2);
    }

    @Override
    public double length() {
        return calcEuclideanDistance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleLine that = (SimpleLine) o;
        return Objects.equals(p1, that.p1) && Objects.equals(p2, that.p2) ||
                Objects.equals(p1, that.p2) && Objects.equals(p2, that.p1) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1) + Objects.hash(p2);
    }
}
