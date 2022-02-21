package de.geomagic;

import de.geomagic.ui.DrawLinesFrame;
import lombok.val;

import java.io.File;

public class MainTaskTwo {

    public static void main(String []args){
        if(args.length == 1){
            val filepath = args[0];
            val inputFile = new File(filepath);
            if(inputFile.exists()){
                val lines = MainTaskOne.getSortedLinesFromFile(inputFile);
                DrawLinesFrame frame = new DrawLinesFrame(lines);
                frame.setVisible(true);
            } else {
                System.err.println("Input files does not exist");
            }
        } else {
            System.err.println("Input files does not exist");
        }
    }
}