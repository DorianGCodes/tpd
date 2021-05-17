package main.services;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Pair;
import main.models.Edge;
import main.models.Node;
import main.models.NodeFx;

import javax.swing.event.ChangeEvent;
import java.util.List;

public class EventHandlerProvider {
    public static SimpleObjectProperty<NodeFx> selectedNode = provideObservableNode();

    public static SimpleObjectProperty<NodeFx> provideObservableNode() {
        var result = new SimpleObjectProperty<NodeFx>();
        result.addListener(new ChangeListener<NodeFx>() {
            @Override
            public void changed(ObservableValue<? extends NodeFx> observable, NodeFx oldValue, NodeFx newValue) {
                System.out.println("Observable");
                if(newValue == null) {
                    AnimationService.provideNodeColorFillingTransition(oldValue,Color.RED,Color.BLACK);
                }
            }
        });
        return result;
    }

    public static EventHandler<MouseEvent> provideNodeMouseEventHandler(
            List<NodeFx> nodeList,
            Group group
    ){
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NodeFx currentSelectedNode = (NodeFx) mouseEvent.getSource();
                if(mouseEvent.getButton() != MouseButton.PRIMARY)
                    return;
                if(!currentSelectedNode.isSelected()) {
                    if(selectedNode.get() != null) {
                        if (!edgeExists(nodeList, currentSelectedNode.getNode(), selectedNode.get().getNode())) {
                            var weightAndLine = drawEdgeLine(selectedNode.get(), currentSelectedNode);
                            Edge edge = new Edge(
                                    selectedNode.get().getNode(),
                                    currentSelectedNode.getNode(),
                                    Integer.parseInt(weightAndLine.getKey().getText()),
                                    weightAndLine.getValue(),
                                    weightAndLine.getKey()
                            );
                            selectedNode.get().getNode().adjacents.add(edge);
                            currentSelectedNode.getNode().adjacents.add(edge.edgeWithSwappedNodes());
                        }
                        resetSelectedNodes(selectedNode.get());
                        return;
                    }
                    AnimationService.provideNodeColorFillingTransition(currentSelectedNode, Color.BLACK, Color.RED);
                    currentSelectedNode.setSelected(true);
                    selectedNode.set(currentSelectedNode);
                } else {
                    resetSelectedNodes(currentSelectedNode);
                }
            }

            private void resetSelectedNodes(NodeFx currentSelectedNode) {
                currentSelectedNode.setSelected(false);
                selectedNode.set(null);
            }
            private Pair<Label,Line> drawEdgeLine(NodeFx selectedNode, NodeFx currentSelectedNode) {
                var edgeLine = new Line(selectedNode.getPoint().x,selectedNode.getPoint().y,currentSelectedNode.getPoint().x,currentSelectedNode.getPoint().y);
                edgeLine.setId("line");
                group.getChildren().add(edgeLine);
                var label = getWeightLabel(edgeLine);
                group.getChildren().add(label);
                return new Pair(label,edgeLine);
            }
        };
    }



    private static Label getWeightLabel(Line edge) {
        var weightLabel = new Label();
        weightLabel.setLayoutX((edge.getStartX() + edge.getEndX()) / 2);
        weightLabel.setLayoutY((edge.getStartY()+ edge.getEndY())/ 2);
        var result = DialogService.provideWeightedEdgeDialog().showAndWait();
        weightLabel.setText(result.get());
        return weightLabel;
    }

    public static boolean edgeExists(List<NodeFx> nodes, Node firstNode, Node secondNode) {
        return nodes
                .stream()
                .flatMap(nodeFx -> nodeFx.getNode().adjacents.stream())
                .anyMatch(e -> (e.target == firstNode && e.source == secondNode) || (e.source == firstNode && e.target == secondNode));
    }
}
