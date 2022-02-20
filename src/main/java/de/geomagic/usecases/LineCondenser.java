package de.geomagic.usecases;

import de.geomagic.Line;
import de.geomagic.model.Point2D;
import lombok.val;

import java.util.*;
import java.util.stream.Stream;

public class LineCondenser {
    public Set<Line> condenseLines(Set<Line> lines) {
        final HashMap<Point2D, Integer> endpointsConnectionAmount = getConnectionAmount(lines);
        // A connection point is a point with exact two lines starting / ending
        // Get the connection point if it exists
        final val connectionPoint = getNextConnectionPoint(endpointsConnectionAmount);
        if (connectionPoint.isEmpty()) {
            // we are finished if there is no more connection point
            return lines;
        } else {
            // add two Lines with the same connection point together and check again
            return addLinesTogetherAndCallRecursive(lines, connectionPoint.get().getKey());
        }

    }

    /**
     * Create a mapping from the input Set in the form of:
     * Mapping point(x, y) --> amount, how many lines have this point as endpoint
     */
    private HashMap<Point2D, Integer> getConnectionAmount(Set<Line> lines) {
        val endpointsConnectionAmount = Stream.concat(lines.stream().map(l -> l.getEndpoints().getRight()),
                        lines.stream().map(l -> l.getEndpoints().getLeft()))
                .reduce(new HashMap<Point2D, Integer>(), (hash, p) -> {
                    if (hash.containsKey(p)) {
                        hash.put(p, 1 + hash.get(p));
                    } else {
                        hash.put(p, 1);
                    }
                    return hash;
                }, (hash1, hash2) -> {
                    hash1.putAll(hash2);
                    return hash1;
                });
        return endpointsConnectionAmount;
    }

    /**
    * Finds the first connection point in a mapping (x,y) -> amount connections
    */
    private Optional<Map.Entry<Point2D, Integer>> getNextConnectionPoint(HashMap<Point2D, Integer> endpointsConnectionAmount) {
        val connection = endpointsConnectionAmount.entrySet()
                .stream()
                .filter(entrySet -> entrySet.getValue() == 2)
                .findFirst();
        return connection;
    }

    /**
     * This method connects two lines and starts the next recursive
     * check for remaining lines which could be connected.
     *
     * @param lines
     * @param connectionPoint a point where two lines starting / ending and therefore can be connected
     * @return
     */
    private Set<Line> addLinesTogetherAndCallRecursive(Set<Line> lines, Point2D connectionPoint) {
        val twoLines = lines.stream().filter(f -> f.getEndpoints().containsOne(connectionPoint)).toList();
        if (twoLines.size() != 2) {
            throw new RuntimeException("This should never happen");
        }
        val first = twoLines.get(0);
        val second = twoLines.get(1);
        val connectedLine = first.add(second);
        val result = new HashSet<Line>();
        result.add(connectedLine);
        result.addAll(lines.stream().filter(l -> !l.getEndpoints().containsOne(connectionPoint)).toList());
        return condenseLines(result);
    }
}
