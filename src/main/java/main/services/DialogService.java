package main.services;

import javafx.scene.control.TextInputDialog;

public class DialogService {
    public static TextInputDialog provideWeightedEdgeDialog() {
        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle(null);
        dialog.setHeaderText("Enter Weight of the Edge :");
        dialog.setContentText(null);
        return dialog;
    }
}
