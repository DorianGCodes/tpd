package main.services;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.models.Edge;
import main.models.Node;
import main.models.NodeFx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

    public static void provideDijkstra(Node source) {
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
        source.minimimalDistance = 0.0;
        nodeQueue.add(source);

        SequentialTransition st = new SequentialTransition();
        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.poll();
            for (Edge e: node.adjacents) {
                Node edgeTarget = e.target;
                if(node.minimimalDistance + e.weight < edgeTarget.minimimalDistance) {
                    provideAnimations(st,e);
                    provideNodeAnimation(st,e.target.nodeFx);
                    nodeQueue.remove(edgeTarget);
                    edgeTarget.minimimalDistance = node.minimimalDistance + e.weight;
                    edgeTarget.previous = node;
                    nodeQueue.add(edgeTarget);
                }
            }
        }
        st.play();
    }

    private static void provideAnimations(SequentialTransition st,Edge e) {
        StrokeTransition ftEdge = new StrokeTransition(Duration.millis(500), e.line,Color.BLACK,Color.FORESTGREEN);
        st.getChildren().add(ftEdge);
    }
    private static void provideNodeAnimation(SequentialTransition st, NodeFx targetNode) {
        FillTransition ft1 = new FillTransition(Duration.millis(500), targetNode);
        ft1.setToValue(Color.FORESTGREEN);
        ft1.setOnFinished(ev -> {
            targetNode.getDistance().setText("Dist. : " + targetNode.getNode().minimimalDistance);
        });
        ft1.onFinishedProperty();
        st.getChildren().add(ft1);
    }

    public static List<Node>    getShortestPath(Node target) {
        List<Node> path = new ArrayList<>();
        for(Node i = target;i != null;i = i.previous ) {
            path.add(i);
        }
        Collections.reverse(path);
        return path;
    }
}
