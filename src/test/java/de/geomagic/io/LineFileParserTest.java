package de.geomagic.io;

import de.geomagic.model.SimpleLine;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class LineFileParserTest {

    @Test
    void testReadFromFile(){
        val testable = new LineFileParser();
        List<SimpleLine> result = testable.parse(new File("src/test/resources/LineFileParser/geomagic.txt"));
        Assertions.assertEquals(geomagicLinesInput(), result);
    }

    List<SimpleLine> geomagicLinesInput(){
        return List.of(
                SimpleLine.of(282,580,44,362),
                SimpleLine.of(970, 362, 742, 580),
                SimpleLine.of(509, 742, 227, 882),
                SimpleLine.of(509, 742, 798, 882),
                SimpleLine.of(512, 512, 282, 580),
                SimpleLine.of(652, 320, 970, 362),
                SimpleLine.of(282, 580, 227, 882),
                SimpleLine.of(509, 39, 652, 320),
                SimpleLine.of(512, 512, 509, 39),
                SimpleLine.of(44, 362, 371, 320),
                SimpleLine.of(970, 362, 512, 512),
                SimpleLine.of(742, 580, 798, 882),
                SimpleLine.of(371, 320, 509, 39));
    }
}
