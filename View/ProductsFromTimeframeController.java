package View;

import com.LeeGlen.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

import java.io.IOException;

/**
 * Gets the timeframe the user wants to view.
 */
public class ProductsFromTimeframeController {

    /**
     * The object that is the access to the database.
     */
    private Controller controller = new Controller();
    /**
     * List used to make choices for choice box.
     */
    private ObservableList<String> list = FXCollections.observableArrayList("By Week", "By Month", "By Year");

    /**
     * Displays the report.
     */
    @FXML
    public Button timeframeButton;
    /**
     * Chooses from the choices in the list.
     */
    @FXML
    public ChoiceBox<String> timeframeChooser;
    /**
     * Display instructions.
     */
    @FXML
    public Label timeframeLabel;


    /**
     * Gets the timeframe chosen and then gets the report and displays it.
     * @param actionEvent Listens for the button being clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void generateReport(ActionEvent actionEvent) throws IOException {
        try {
            //If the the user has chosen something.
            if (timeframeChooser.getValue() != null) {
                //Gets the report based on the timeframe
                StringBuilder report = controller.generateReports(timeframeChooser.getValue());
                //Creates the diplay report scene
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("DisplayReport.fxml"));
                Parent timeframeParent = loader.load();
                Scene timeframeScene = new Scene(timeframeParent);

                //Passes in the controller and the report to display.
                DisplayReportController controller = loader.getController();
                controller.initData(this.controller, report);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.hide();
                window.setScene(timeframeScene);
                window.show();
                //If the user didn't choose then a error message is diplayed.
            } else {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage("NO CHOICE PICKED!");
            }//END IF/ELSE
        }catch (HibernateException e){
            //If the database disconnects then an error message appears and the program is shut down.
            ErrorMessage message = new ErrorMessage();
            message.errorMessage("DATABASE DISCONNECTED! - SYSTEM SHUTDOWN!");
            controller.exit();
            System.exit(0);
        }//END TRY/CATCH
    }//END METHOD generateReport

    /**
     * Takes in the controller and assigns the choices to the choice box.
     * @param controller Controller that's used everywhere.
     */
    public void initData(Controller controller){
        this.controller = controller;
        timeframeChooser.setItems(list);
    }//END METHOD initData

}//END ClASS ProductsFromTimeframeController
