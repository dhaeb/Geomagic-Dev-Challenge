package de.geomagic.model;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test functions for ConnectedLine, many parameterized tests to check multiple edge cases.
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
     * Arguments for testing testSumAllEuclideanDistances
     *
     * @return each Stream entry will result into a test in {@code testSum}
     */
    private static Stream<Arguments> getArgsForTestSumAllEuclideanDistances() {
        val fixtures = new TestConstants();
        return Stream.of(
                Arguments.of(List.of(fixtures.getLine_0_1__1_1(),
                                     fixtures.getLine_1_1__1_2()),
                        2
                ),
                Arguments.of(List.of(fixtures.getLine_0_0__1_1(),
                                fixtures.getLine_1_1__2_2(),
                               fixtures.getLine_2_2__2_3()),
                        1 + 1.414 * 2 //(1 + √2 * 2)
                ),
                Arguments.of(List.of(fixtures.getLine_0_0__1_1(),
                        fixtures.getLine_1_1__2_2(),
                        fixtures.getLine_2_2__3_3()),
                        3 * 1.414 //(3 * √2)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getArgsForTestConnectionPoint")
    void testGetConnectionPoint(List<SimpleLine> input, Set<Point2D> expected) {
        val testable = new ConnectedLine(input);
        assertEquals(expected, testable.getConnectionPoints());
    }

    @Test
    void testGetConnectionPointWithErrorInData() {
        val fixtures = new TestConstants();
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    val l1 = fixtures.getLine_0_0__1_1();
                    val l2 = fixtures.getLine_2_2__3_3();
                    new ConnectedLine(List.of(l1, l2)).getConnectionPoints();
                }, "Assertions error was expected");
    }

    /**
     * Parameters for testing getConnectionPoint
     *
     * @return each Stream entry will result into a test in {@code testGetConnectionPoint}
     */
    private static Stream<Arguments> getArgsForTestConnectionPoint() {
        val fixtures = new TestConstants();
        return Stream.of(
                Arguments.of(
                        List.of(fixtures.getLine_0_0__1_1(), fixtures.getLine_1_1__2_2()),
                        Set.of(fixtures.getPoint_1_1())
                ),
                Arguments.of(List.of(fixtures.getLine_3_3__2_2(), fixtures.getLine_2_2__1_1()),
                        Set.of(fixtures.getPoint_2_2())
                ),
                Arguments.of(List.of(fixtures.getLine_3_3__2_2(), fixtures.getLine_2_2__1_1(), fixtures.getLine_0_0__1_1()),
                        Set.of(fixtures.getPoint_2_2(), fixtures.getPoint_1_1())
                )
        );
    }

}