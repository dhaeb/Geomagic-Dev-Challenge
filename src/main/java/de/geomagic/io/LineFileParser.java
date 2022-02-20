package de.geomagic.io;

import de.geomagic.Line;
import de.geomagic.model.SimpleLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LineFileParser {
    static Pattern patternForEachLineInFile = Pattern.compile("(\\d+ \\d+ \\d+ \\d+\\r\\n|\\n|\\r)");
    static Pattern whiteSpacePattern = Pattern.compile(" ");

    public Set<Line> parse(File f) {
        try (Scanner s = new Scanner(f)) {
            return s.findAll(patternForEachLineInFile)
                    .map(MatchResult::group)
                    .map(line -> parseLineToListOfDoubles(line))
                    .map(doubleList -> SimpleLine.of(doubleList.get(0), doubleList.get(1), doubleList.get(2), doubleList.get(3)))
                    .collect(Collectors.toSet());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private List<Double> parseLineToListOfDoubles(String line) {
        return whiteSpacePattern
                .splitAsStream(line)
                .map(digits -> Double.parseDouble(digits.trim()))
                .collect(Collectors.toList());
    }
}
