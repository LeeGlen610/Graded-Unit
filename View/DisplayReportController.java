package View;

import com.LeeGlen.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Displays a chosen report.
 */
public class DisplayReportController {

    /**
     * The object that is the access to the database.
     */
    private Controller controller;

    /**
     * Displays the report
     */
    @FXML
    public TextArea reportTextbox;
    /**
     * Returns to the main menu.
     */
    @FXML
    public Button mainMenu;

    /**
     * Displays the main menu.
     * @param actionEvent Listens for the button being clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void mainMenu(ActionEvent actionEvent) throws IOException {
        //Displays the main menu if the employee was admin.
        if (controller.getJobType().equals("Admin")) {
            //Displays admin main menu.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainMenuAdmin.fxml"));
            Parent mainMenuParent = loader.load();
            Scene mainMenuScene = new Scene(mainMenuParent);

            //Passes in the controller to be reused.
            MainMenuAdminController mainMenuAdminController = loader.getController();
            mainMenuAdminController.initData(controller);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.hide();
            window.setScene(mainMenuScene);
            window.show();
            //If the employee isn't a admin.
        } else {
            //Displays employee main menu.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainMenuEmployee.fxml"));
            Parent mainMenuParent = loader.load();
            Scene mainMenuScene = new Scene(mainMenuParent);

            //Passes in the controller to be reused.
            MainMenuAdminController mainMenuAdminController = loader.getController();
            mainMenuAdminController.initData(controller);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.hide();
            window.setScene(mainMenuScene);
            window.show();
        }//END IF/ELSE
    }//END METHOD mainMenu

    /**
     * Display the report to the text box and takes in the controller.
     * @param controller The controller that's used everywhere.
     * @param report The report that the user chosen.
     */
    public void initData(Controller controller, StringBuilder report){
        this.controller = controller;
        reportTextbox.setText(report.toString());
    }

}
