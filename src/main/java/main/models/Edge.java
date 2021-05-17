package main.models;

import javafx.scene.control.Label;
import javafx.scene.shape.Shape;

public class Edge {
    public Node source, target;
    public double weight;
    public Shape line;
    public Label weightLabel;


    public Edge(Node source, Node target,double weight,Shape line, Label weightLabel) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.line = line;
        this.weightLabel = weightLabel;
    }

    public Edge edgeWithSwappedNodes() {
        var temp = this.target;
        return new Edge(this.target,this.source,this.weight,this.line,this.weightLabel);
    }

}
