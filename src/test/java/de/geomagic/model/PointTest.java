package de.geomagic.model;

import lombok.val;
import org.junit.jupiter.api.Test;
import de.geomagic.model.Point2D;

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
        val testable = new Point2D(1.0,2.0);
        assertEquals(1.0, testable.getX(), 0);
        assertEquals(2.0, testable.getY(), 0);
    }

}