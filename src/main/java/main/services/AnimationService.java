package main.services;

import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.models.NodeFx;

public class AnimationService {
    public static void provideAddingNodeAnimation(NodeFx nodeFx) {
        ScaleTransition tr = new ScaleTransition(Duration.millis(100), nodeFx);
        tr.setByX(10f);
        tr.setByY(10f);
        tr.setInterpolator(Interpolator.EASE_BOTH);
        tr.play();
    }

    public static void provideNodeColorFillingTransition(NodeFx node,Color fromColor, Color toColor) {
        FillTransition ft = new FillTransition(Duration.millis(300), node, fromColor, toColor);
        ft.play();
    }


}
