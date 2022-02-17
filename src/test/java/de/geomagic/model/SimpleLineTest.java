package de.geomagic.model;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Simple tests for Line functions.
 *
 * @author Dan Haeberlein
 */
class SimpleLineTest {

    @ParameterizedTest
    @MethodSource("calcEuclideanDistanceArgs")
    void testCalcEuclideanDistance(Point2D p1, Point2D p2, double expected ) {
        double result = new SimpleLine(p1, p2).calcEuclideanDistance();
        assertEquals(expected, result, new TestConstants().getTestDelta());
    }

    /**
     * Arguments for testing euclidean distance, 0.001 is default delta, see @{@link TestConstants}
     *
     * @return each Stream entry will result into a test in {@code testCalcEuclideanDistance}
     */
    private static Stream<Arguments> calcEuclideanDistanceArgs() {
        val fixtures = new TestConstants();
        return Stream.of(
                Arguments.of(fixtures.getOrigin(), fixtures.getPoint_0_1(), 1),
                Arguments.of(fixtures.getOrigin(), fixtures.getPoint_1_1(), 1.414), // √2
                Arguments.of(fixtures.getOrigin(), fixtures.getPoint_1_1_minus(), 1.414) // √2
        );
    }
}