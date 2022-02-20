package de.geomagic.usecases;

import de.geomagic.Line;
import de.geomagic.model.TestConstants;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

public class LineCondenserTest {

    @ParameterizedTest(name = "{2}")
    @MethodSource("testCondenseLinesArgs")
    void testCondenseLines(Set<Line> input, Set<Line> expected, String desc) {
        val testable = new LineCondenser();
        val result = testable.condenseLines(input);
        Assertions.assertEquals(expected, result, "failed: " + desc);
    }

    /**
     * Arguments for testing the main algorithm of the program to condense lines into CollectedLines.
     *
     * @return each Stream entry will result into a test in {@code testCondenseLinesArgs}
     */
    private static Stream<Arguments> testCondenseLinesArgs() {
        val fixtures = new TestConstants();
        return Stream.of(
                Arguments.of(Set.of(), Set.of(), "Empty input should yield empty output"),
                Arguments.of(Set.of(fixtures.getLine_0_0__1_1(), fixtures.getLine_1_1__2_2()),
                        Set.of(fixtures.getCl_00_11__11_22()),
                        "Line (0,0)-(1,1) should be connected with (1,1)-(2,2)"),
                Arguments.of(Set.of(fixtures.getLine_0_0__1_1(), fixtures.getLine_1_1__2_2(), fixtures.getLine_3_3__2_2()),
                        Set.of(fixtures.getCl_00_11__11_22__22_33()),
                        "Line (0,0)-(1,1) should be connected with (1,1)-(2,2) and line (2,2)-(3,3)"),
                Arguments.of(Set.of(fixtures.getLine_0_0__1_1(), fixtures.getLine_1_1__2_2(), fixtures.getLine_3_3__2_2(), fixtures.getLine_5_5__6_6()),
                        Set.of(fixtures.getCl_00_11__11_22__22_33(), fixtures.getLine_5_5__6_6()),
                        "Line (0,0)-(1,1) should be connected with (1,1)-(2,2), Line (5,5)-(6,6) should be untouched"),
                Arguments.of(Set.of(fixtures.getLine_0_0__1_1(), fixtures.getLine_3_3__2_2(), fixtures.getLine_5_5__6_6()),
                        Set.of(fixtures.getLine_0_0__1_1(), fixtures.getLine_5_5__6_6(), fixtures.getLine_2_2__3_3()),
                        "Line (0,0)-(1,1) should not be connected with all other lines (2,2)-(3,3), Line (5,5)-(6,6)")
                );
    }
}
