package de.geomagic.model;

import lombok.Value;

import java.util.Set;

/**
 * Small class to encapsulate global settings for tests and fixtures.
 *
 * @author Dan Haeberlein
 */
@Value
public final class TestConstants {
    double testDelta = 0.001d;

    Point2D origin = new Point2D(0, 0);
    Point2D point_1_1 = new Point2D(1, 1);
    Point2D point_1_1_minus = new Point2D(-1, -1);
    Point2D point_2_2 = new Point2D(2, 2);
    Point2D point_3_3 = new Point2D(3, 3);
    Point2D point_4_4 = new Point2D(4, 4);
    Point2D point_5_5 = new Point2D(5, 5);
    Point2D point_6_6 = new Point2D(6, 6);
    Point2D point_1_0 = new Point2D(1, 0);
    Point2D point_2_0 = new Point2D(2, 0);
    Point2D point_0_1 = new Point2D(0, 1);
    Point2D point_0_2 = new Point2D(0, 1);

    SimpleLine line_0_0__1_1 = new SimpleLine(origin, point_1_1);
    SimpleLine line_1_1__2_2 = new SimpleLine(point_1_1, point_2_2);
    SimpleLine line_2_2__3_3 = new SimpleLine(point_2_2, point_3_3);
    SimpleLine line_2_2__1_1 = new SimpleLine(point_2_2, point_1_1);
    SimpleLine line_3_3__2_2 = new SimpleLine(point_3_3, point_2_2);
    SimpleLine line_3_3__4_4 = new SimpleLine(point_3_3, point_4_4);
    SimpleLine line_5_5__6_6 = new SimpleLine(point_5_5, point_6_6);

    SimpleLine line_2_2__2_3 = SimpleLine.of(2, 2, 2, 3);
    SimpleLine line_0_1__1_1 = SimpleLine.of(0, 1, 1, 1);
    SimpleLine line_1_1__1_2 = SimpleLine.of(1, 1, 1, 2);

    ConnectedLine cl_00_11__11_22 = new ConnectedLine(Set.of(line_0_0__1_1, line_1_1__2_2));
    ConnectedLine cl_11_22__22_33 = new ConnectedLine(Set.of(line_3_3__2_2, line_1_1__2_2));

    ConnectedLine cl_00_11__11_22__22_33 = new ConnectedLine(Set.of(line_0_0__1_1, line_1_1__2_2, line_2_2__3_3));
}
