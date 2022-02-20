package de.geomagic.io;

import de.geomagic.model.SimpleLine;
import lombok.Value;

import java.util.Set;

@Value
public class TestFixtures {
    public Set<SimpleLine> geomagicLinesInput(){
        return Set.of(
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
