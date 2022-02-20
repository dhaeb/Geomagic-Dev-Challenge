package de.geomagic.model;

import de.geomagic.Line;
import de.geomagic.types.OrderAgnosticPair;
import lombok.Value;
import lombok.val;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
public class ConnectedLine implements Line {
    Set<SimpleLine> lines;

    public static ConnectedLine of(Line l1, Line l2){
        if(l1 instanceof ConnectedLine){
            return l1.add(l2);
        }
        if(l2 instanceof ConnectedLine){
            return l2.add(l1);
        }
        return of((SimpleLine) l1, (SimpleLine) l2);
    }

    public static ConnectedLine of(SimpleLine l1, SimpleLine l2){
        val constructable = new ConnectedLine(Set.of(l1, l2));
        // throws IllegalArgumentException when there is no connection between the two lines
        constructable.getEndpoints();
        return constructable;
    }

    ConnectedLine(Set<SimpleLine> lines) {
        this.lines = lines;
    }

    public Set<Point2D> getConnectionPoints() {
        var result = new HashSet<Point2D>();
        val testingHash = new HashSet<Point2D>();
        for(SimpleLine l : this.lines){
            if(testingHash.contains(l.getP1())){
                result.add(l.getP1());
            }
            if(testingHash.contains(l.getP2())){
                result.add(l.getP2());
            }
            testingHash.addAll(l.asPointList());
        }
        if (result.isEmpty()){
            throw new IllegalArgumentException("Should contain at least one connection point");
        }
        return result;
    }

    public double sumAllEuclideanDistances() {
        return lines.stream()
                    .map((e) -> e.calcEuclideanDistance())
                    .reduce(0d, Double::sum);
    }

    @Override
    public OrderAgnosticPair<Point2D, Point2D> getEndpoints() {
        Set<Point2D> connectionPoints = getConnectionPoints();
        val allPointsAsSteam = Stream.concat(lines.stream().map(l -> l.getP1()), lines.stream().map(l -> l.getP2()));
        List<Point2D> result = allPointsAsSteam.filter((p) -> !connectionPoints.contains(p)).collect(Collectors.toList());
        if(result.size() == 2) {
            return OrderAgnosticPair.of(result.get(0), result.get(1));
        } else {
            throw new IllegalArgumentException("There are no starting distinct two starting points!");
        }
    }

    @Override
    public double length() {
        return sumAllEuclideanDistances();
    }

    @Override
    public ConnectedLine add(Line addable) {
        val inputForNewCl = new HashSet<SimpleLine>(lines);
        val connectionPoints = getEndpoints();
        val isConnected = List.of(addable.getEndpoints().getLeft(), addable.getEndpoints().getRight())
                                         .stream()
                                         .anyMatch(p -> connectionPoints.containsOne(p));
        if(isConnected){
            inputForNewCl.addAll(addable.asSet());
            return new ConnectedLine(inputForNewCl);
        } else {
            throw new IllegalArgumentException("New lines is not connected to ConnectedLines at the endpoints");
        }

    }

    @Override
    public Set<SimpleLine> asSet() {
        return lines;
    }
}
