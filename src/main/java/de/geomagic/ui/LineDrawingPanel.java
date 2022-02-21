package de.geomagic.ui;

import de.geomagic.Line;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class LineDrawingPanel extends JPanel {
    private final List<Line> lines;
    private final long seed;

    public LineDrawingPanel(List<Line> lines) {
        super();
        this.lines = lines;
        Random rand = new Random();
        this.seed = rand.nextLong();
    }

    public Dimension getPreferredSize() {
        return new Dimension(1024, 1024);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setBorder(new LineBorder(Color.BLACK));
        Graphics2D g2 = (Graphics2D) graphics;
        Random rand = new Random();
        rand.setSeed(seed);
        lines.stream().map(Line::asSet).forEach(linesSet -> {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            g2.setStroke(new BasicStroke(2f));
            g2.setColor(randomColor);
            linesSet.stream().forEach(l -> g2.draw(l.toLine2D()));
        });
    }
}