package de.geomagic.ui;

import de.geomagic.Line;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DrawLinesFrame extends JFrame {


    public DrawLinesFrame(List<Line> lines) {
        JScrollPane scrollPane = new JScrollPane(new LineDrawingPanel(lines));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}