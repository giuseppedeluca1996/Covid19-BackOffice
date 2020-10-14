package com.covid19.view;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public interface PopUpDialog {

    static void showPopUpError( String headerText, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Attention!");
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();

    }

    static boolean showPopUpWarning( String headerText, String message) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,message,  yes, no);
        alert.setTitle("Attention!");
        alert.setHeaderText(headerText);
        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(no) != no;

    }

}
