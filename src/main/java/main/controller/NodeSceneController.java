package main.controller;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.models.Node;
import main.models.NodeFx;
import main.services.AnimationService;
import main.services.DijkstraAlgorithm;
import main.services.EventHandlerProvider;
import main.services.NodeFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NodeSceneController implements Initializable {

    @FXML
    public Group group;

    public List<NodeFx> nodeList = new ArrayList();

    @FXML
    public Pane nodePane;

    @FXML
    public RadioButton nodeButton;

    @FXML
    public TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addNode(MouseEvent event) {
        if(event.getButton() != MouseButton.PRIMARY)
            return;
        if (nodeButton.isSelected()) {
            if(event.getSource().equals(nodePane)) {
                NodeFx node = new NodeFx(event,String.valueOf(nodeList.size()));
                NodeFactory.addNodeFx(this,node);
                AnimationService.provideAddingNodeAnimation(node);
                node.setOnMouseReleased(EventHandlerProvider.provideNodeMouseEventHandler(nodeList, group));
            }
        }
    }

    public void clearSelectedNode(MouseEvent event) {
        if(event.getButton() == MouseButton.SECONDARY) {
//            AnimationService.provideNodeColorFillingTransition(EventHandlerProvider.selectedNode.get(), Color.RED,Color.BLACK);
            EventHandlerProvider.selectedNode.set(null);
        }
    }

    public void calulatePaths() {
        List<Label> distances = new ArrayList<>();
        for (NodeFx n : nodeList) {
            n.getDistance().setLayoutX(n.getPoint().x + 20);
            n.getDistance().setLayoutY(n.getPoint().y);
            if(n == EventHandlerProvider.selectedNode.get()) {
                n.getDistance().setText(("Dist. : " + 0));
            }
            group.getChildren().add(n.getDistance());
            distances.add(n.getDistance());
        }
        DijkstraAlgorithm.provideDijkstra(EventHandlerProvider.selectedNode.get().getNode());
    }

    public void printlnShortestPath() {
//        var listOfVisitedNodes = DijkstraAlgorithm.getShortestPath(EventHandlerProvider.selectedNode.get().getNode());

        var strBuilder = new StringBuilder();
        nodeList.forEach(e -> {
            var listOfVisitedNodes1 = DijkstraAlgorithm.getShortestPath(e.getNode());
            System.out.print("Target Node Id: " + e.getNode().id + " Path: ");
            strBuilder.append("Target Node Id: " + e.getNode().id + " Path: ");
            listOfVisitedNodes1.forEach(w -> System.out.print(strBuilder.append(w.id + " ")));
            System.out.println("");
            strBuilder.append("\n");
        });
        textArea.setText(strBuilder.toString());
    }



}
