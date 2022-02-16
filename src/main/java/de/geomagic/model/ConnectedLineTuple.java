package de.geomagic.model;

import lombok.Value;
import lombok.val;

import java.awt.*;
import java.util.HashSet;

@Value
public class ConnectedLineTuple {
    Line l1, l2;

    public Point2D getConnectionPoint() {
        val testingHash = new HashSet<Point2D>();
        testingHash.add(l1.getP1());
        testingHash.add(l1.getP2());
        if(testingHash.contains(l2.getP1())){
            return l2.getP1();
        } else if(testingHash.contains(l2.getP2())){
            return l2.getP2();
        } else {
            throw new IllegalArgumentException("each connected line tuple should have a common point");
        }
    }
}
