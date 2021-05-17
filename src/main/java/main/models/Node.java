package main.models;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
    public String id;
    public List<Edge> adjacents = new ArrayList();
    public Node previous;
    public NodeFx nodeFx;
    public double minimimalDistance = Double.POSITIVE_INFINITY;
    public int color;

    public Node(String id) {
        this.id = id;
    }

    public Node(String id, NodeFx nodeFx) {
        this.id = id;
        this.nodeFx = nodeFx;
    }

    @Override
    public int compareTo(Node o) { return  Double.compare(minimimalDistance,o.minimimalDistance);}
}


