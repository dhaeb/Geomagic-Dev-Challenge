package de.geomagic.model;

import de.geomagic.Line;
import de.geomagic.types.OrderAgnosticPair;
import lombok.Generated;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.paukov.combinatorics3.Generator;

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

    @ParameterizedTest(name = "{2}")
    @MethodSource("getArgsForTestGetEndpoints")
    void testGetEndpoints(List<SimpleLine> input, OrderAgnosticPair<Point2D, Point2D> expected, String desc) {
        val testable = new ConnectedLine(input);
        assertEquals(expected, testable.getEndpoints(), "Failed: " + desc );
    }

    /**
     * Arguments for testing testGetEndpoints
     *
     * @return each Stream entry will result into a test in {@code testGetEndpoints}
     */
    private static Stream<Arguments> getArgsForTestGetEndpoints() {
        val fixtures = new TestConstants();
        val pair_0_0_and_3_3 = OrderAgnosticPair.of(fixtures.getOrigin(), fixtures.getPoint_3_3());
        val pair_3_3_and_0_0 = OrderAgnosticPair.of(fixtures.getPoint_3_3(), fixtures.getOrigin());
        return Stream.concat(
                Generator.permutation(fixtures.getLine_1_1__2_2(), fixtures.getLine_3_3__2_2(), fixtures.getLine_0_0__1_1())
                        .simple()
                        .stream()
                        .map(perm -> Arguments.of(perm, pair_0_0_and_3_3, "line with 3 params should have two endpoints: perm " + perm)),
                Generator.permutation(fixtures.getLine_1_1__2_2(), fixtures.getLine_3_3__2_2(), fixtures.getLine_0_0__1_1())
                        .simple()
                        .stream()
                        .map(perm -> Arguments.of(perm, pair_3_3_and_0_0, "line with 3 params should have two endpoints: perm " + perm))
        );

    }

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
                       /*Expected length: */ 2
                ),
                Arguments.of(List.of(fixtures.getLine_0_0__1_1(),
                                fixtures.getLine_1_1__2_2(),
                               fixtures.getLine_2_2__2_3()),
                        /*Expected length: */ 1 + 1.414 * 2 //(1 + √2 * 2)
                ),
                Arguments.of(List.of(fixtures.getLine_0_0__1_1(),
                        fixtures.getLine_1_1__2_2(),
                        fixtures.getLine_2_2__3_3()),
                        /*Expected length: */ 3 * 1.414 //(3 * √2)
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