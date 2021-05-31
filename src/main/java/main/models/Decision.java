package main.models;

public class Decision {
    String startingNode;
    String targetNode;
    String decision;
    public Decision(
            String startingNode,
            String targetNode,
            String decision
    ){
        this.startingNode = startingNode;
        this.targetNode = targetNode;
        this.decision = decision;
    }

}
