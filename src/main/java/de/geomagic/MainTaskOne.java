package de.geomagic;

import de.geomagic.io.LineFileParser;
import de.geomagic.usecases.LineCondenser;
import lombok.val;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainTaskOne {
    public static void main(String[] args) {
        if(args.length == 1){
            val filepath = args[0];
            val inputFile = new File(filepath);
            if(inputFile.exists()){
                List<Line> condensedLinesAsList = getSortedLinesFromFile(inputFile);
                condensedLinesAsList.forEach(l -> System.out.println("Length: " + l.length() + " --> \n" +l));
            } else {
                System.err.println("Input files does not exist");
            }
        } else {
            System.err.println("Input files does not exist");
        }

    }

    public static List<Line> getSortedLinesFromFile(File inputFile) {
        Set<Line> input = new LineFileParser().parse(inputFile);
        val condensedLines = new LineCondenser().condenseLines(input);
        List<Line> condensedLinesAsList = new ArrayList<>(condensedLines);
        condensedLinesAsList.sort((l1, l2) -> Double.compare(l1.length(), l2.length()) * -1);
        return condensedLinesAsList;
    }
}
