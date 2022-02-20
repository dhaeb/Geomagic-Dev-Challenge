package de.geomagic.io;

import de.geomagic.Line;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

public class LineFileParserTest {

    @Test
    void testReadFromFile(){
        val testable = new LineFileParser();
        val fixtures = new TestFixtures();
        Set<Line> result = testable.parse(new File("src/test/resources/LineFileParser/geomagic.txt"));
        Assertions.assertEquals(fixtures.geomagicLinesInput(), result);
    }
}
