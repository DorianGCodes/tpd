package main.services;

import main.controller.NodeSceneController;
import main.models.NodeFx;

public class NodeFactory {

    public static void addNodeFx(
            NodeSceneController nodeSceneController,
            NodeFx nodeFx
    ) {
        var nodeLabel = nodeFx.getName();
        nodeLabel.setLayoutX(nodeFx.getCenterX() - 18);
        nodeLabel.setLayoutY(nodeFx.getCenterY() - 18);
        nodeFx.setOpacity(0.5);
        nodeSceneController.group.getChildren().add(nodeLabel);
        nodeSceneController.group.getChildren().add(nodeFx);
        nodeSceneController.nodeList.add(nodeFx);
    }
}
