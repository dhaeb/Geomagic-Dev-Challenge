package de.geomagic.model;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test functions for ConnetedLineTuple
 *
 * @author Dan Haeberlein
 */
class ConnectedLineTest {

    @ParameterizedTest
    @MethodSource("getArgsForTestSumAllEuclideanDistances")
    void testSumAllEuclideanDistances(List<SimpleLine> input , double expected) {
        val testable = new ConnectedLine(input);
        assertEquals(expected, testable.sumAllEuclideanDistances(), new TestConstants().getTestDelta());

    }

    /**
     * Parameters for testing testSum
     *
     * @return each Stream entry will result into a test in {@code testSum}
     */
    private static Stream<Arguments> getArgsForTestSumAllEuclideanDistances() {
        return Stream.of(
                Arguments.of(List.of(new SimpleLine(1, 1, 2, 1),
                                     new SimpleLine(0, 1, 1, 1)),
                        2
                ),
                Arguments.of(List.of(new SimpleLine(1, 1, 2, 1),
                        new SimpleLine(0, 1, 1, 1),
                        new SimpleLine(2, 1, 3, 1)),
                        3
                ),
                Arguments.of(List.of(new SimpleLine(1, 1, 2, 2),
                                new SimpleLine(2, 2, 3, 3),
                                new SimpleLine(3, 3, 4, 4)),
                        3 * 1.414 //(3 * √2)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getArgsForTestConnectionPoint")
    void testGetConnectionPoint(List<SimpleLine> input, List<Point2D> expected) {
        val testable = new ConnectedLine(input);
        assertEquals(expected, testable.getConnectionPoints());
    }

    @Test
    void testGetConnectionPointWithErrorInData() {
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    val l1 = new SimpleLine(0,0,1,1);
                    val l2 = new SimpleLine(2,2,3,3);
                    new ConnectedLine(List.of(l1, l2)).getConnectionPoints();
                }, "Assertions error was expected");
    }

    /**
     * Parameters for testing getConnectionPoint
     *
     * @return each Stream entry will result into a test in {@code testGetConnectionPoint}
     */
    private static Stream<Arguments> getArgsForTestConnectionPoint() {
        return Stream.of(
                Arguments.of(List.of(
                        new SimpleLine(1, 1, 2, 2),
                        new SimpleLine(0, 0, 1, 1)
                        ),
                        List.of(new Point2D(1, 1))
                ),
                Arguments.of(List.of(
                        new SimpleLine(3, 3, 2, 2),
                        new SimpleLine(2, 2, 1, 1)
                        ),
                        List.of(new Point2D(2, 2))
                )
        );
    }

}