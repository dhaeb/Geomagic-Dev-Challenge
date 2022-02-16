package de.geomagic.model;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test functions for ConnetedLineTuple
 *
 * @author Dan Haeberlein
 */
class ConnectedLineTupleTest {

    @ParameterizedTest
    @MethodSource("calcEuclideanDistanceParams")
    void testGetConnectionPoint(Line l1, Line l2, Point2D expected) {
        val testable = new ConnectedLineTuple(l1, l2);
        assertEquals(expected, testable.getConnectionPoint());
    }

    @Test
    void testGetConnectionPointWithErrorInData() {
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    val l1 = new Line(0,0,1,1);
                    val l2 = new Line(2,2,3,3);
                    new ConnectedLineTuple(l1, l2).getConnectionPoint();
                }, "Assertions error was expected");
    }

    /**
     * Paramters for testing getConnectionPoint
     *
     * @return each Stream entry will result into a test in {@code testGetConnectionPoint}
     */
    private static Stream<Arguments> calcEuclideanDistanceParams() {
        return Stream.of(
                Arguments.of(new Line(1, 1, 2, 2),
                        new Line(0, 0, 1, 1),
                        new Point2D(1, 1)
                ),
                Arguments.of(new Line(3, 3, 2, 2),
                        new Line(2, 2, 1, 1),
                        new Point2D(2, 2)
                )
        );
    }

}