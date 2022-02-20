package de.geomagic.model;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Small "library test for project Lombok"
 * as well as test for Line functions.
 *
 * @author Dan Haeberlein
 */
class PointTest {

    @Test
    void constructorAndGettersExists() {
        val testable = new TestConstants().getPoint_0_1();
        assertEquals(0.0d, testable.getX(), new TestConstants().getTestDelta());
        assertEquals(1.0d, testable.getY(), new TestConstants().getTestDelta());
    }

}