package de.geomagic.types;

import lombok.Value;

@Value(staticConstructor = "of")
public class OrderAgnosticPair<A, B> {
    final A left;
    final B right;

    public boolean equals(Object obj){
        var ret = false;
        if(obj != null && obj instanceof OrderAgnosticPair){
            OrderAgnosticPair<?,?> other = (OrderAgnosticPair<?, ?>) obj;
            ret = other.left.equals(left) && other.right.equals(right) ||
                  other.left.equals(right) && other.right.equals(left);
        }
        return ret;
    }

    public boolean containsOne(Object obj){
        var ret = false;
        if(obj != null){
            ret = obj.equals(left) || obj.equals(right);
        }
        return ret;
    }

    @Override
    public int hashCode() {
        return left.hashCode() + right.hashCode();
    }
}