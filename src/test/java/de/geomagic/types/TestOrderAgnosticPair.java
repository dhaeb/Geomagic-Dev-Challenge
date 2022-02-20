package de.geomagic.types;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestOrderAgnosticPair {

    @ParameterizedTest
    @MethodSource("testEqualsHashcodeArgs")
    void testEqualsAndHashcode(OrderAgnosticPair<?,?> p1, OrderAgnosticPair<?,?> p2, boolean isEqual ) {
        assertEquals(p1.equals(p2), isEqual, "Test case for OrderAgnosticPair is failing");
        if(isEqual){
            assertEquals(p1.hashCode(), p2.hashCode());
        } else {
            assertNotEquals(p1.hashCode(), p2.hashCode());
        }
    }

    /**
     * Arguments for testEqualsAndHashcode
     *
     * @return each Stream entry will result into a test in {@code testEqualsAndHashcode}
     */
    private static Stream<Arguments> testEqualsHashcodeArgs() {
        return Stream.of(
                Arguments.of(OrderAgnosticPair.of(1,2), OrderAgnosticPair.of(2,1), true),
                Arguments.of(OrderAgnosticPair.of(1,1), OrderAgnosticPair.of(1,1), true),
                Arguments.of(OrderAgnosticPair.of(1,2), OrderAgnosticPair.of(2,2), false)
        );
    }
}
