package de.geomagic;

import de.geomagic.ui.DrawLinesFrame;
import lombok.val;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.List;

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