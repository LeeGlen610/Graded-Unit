package View;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;

/**
 * Displays an error message.
 */
public class ErrorMessage {

    /**
     * Displays an error message.
     * @param message The error message.
     */
    public void errorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText("Read Information Below!");
        alert.setContentText(message);
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
