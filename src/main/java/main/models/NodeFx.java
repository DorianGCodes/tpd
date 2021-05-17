package main.models;

import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class NodeFx extends Circle {
    Node node;
    Point point;
    Label distance = new Label("Dist. : INFINITY");
    Label name;
    boolean isSelected = false;

    public NodeFx(MouseEvent event, String name) {
        super(event.getX(),event.getY(),1.2);
        this.node = new Node(name, this);
        this.point = new Point((int) event.getX(),(int) event.getY());
        this.name = new Label(name);

        this.setOpacity(0.5);
        this.setBlendMode(BlendMode.MULTIPLY);
        this.setId("node");
    }
}
