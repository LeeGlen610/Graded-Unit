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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.HibernateError;

import java.io.IOException;

/**
 * Gets the users choice of report and creates the report.
 */
public class GenerateReportController {

    /**
     * List used to make choices for choice box.
     */
    private ObservableList<String> choices = FXCollections.observableArrayList("Employee Sold Most", "Most Popular Product - All Time", "Products Out of Stock", "Most Popular Product - Specific Timeframe");
    /**
     * The object that is the access to the database.
     */
    private Controller controller;

    /**
     * Used to display the report
     */
    @FXML
    public Button reportButton;
    /**
     * Choice box for the user to choose.
     */
    @FXML
    public ComboBox<String> choiceBox;
    /**
     * Used to display instructions
     */
    @FXML
    public Label chooseLabel;

    /**
     * Initialises the controller and choicebox.
     * @param controller The controller that's used everywhere.
     */
    public void initData(Controller controller) {
        //Initialises controller and sets the items of the list in the choicebox.
        this.controller = controller;
        choiceBox.setItems(choices);
    }

    /**
     * Gets the report and then passes it into another FXML controller.
     * @param actionEvent Listens for button click.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void displayReport(ActionEvent actionEvent) throws IOException {
        //Checks to see if user has chosen a option.
        if (choiceBox.getValue() != null) {
            try {
                //Initialise variables.
                FXMLLoader loader = new FXMLLoader();
                StringBuilder report;
                //Gets the choice from the choice box the user has picked.
                String choice = choiceBox.getValue();
                //If the user chose most popular product - specific time then
                if (choice.equals("Most Popular Product - Specific Timeframe")) {
                    //Create a products from timeframe scene.
                    loader.setLocation(getClass().getResource("ProductsFromTimeframe.fxml"));
                    Parent reportParent = loader.load();
                    Scene reportScene = new Scene(reportParent);

                    //Pass in the controller to initialise.
                    ProductsFromTimeframeController controller = loader.getController();
                    controller.initData(this.controller);

                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.hide();
                    window.setScene(reportScene);
                    window.show();
                } else {
                    //Gets the report.
                    report = controller.generateReports(choice);
                    //Creates a display report scene.
                    loader.setLocation(getClass().getResource("DisplayReport.fxml"));
                    Parent reportParent = loader.load();
                    Scene reportScene = new Scene(reportParent);

                    //Passes in the controller to initialise.
                    DisplayReportController displayReportController = loader.getController();
                    displayReportController.initData(controller, report);

                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.hide();
                    window.setScene(reportScene);
                    window.show();
                }//END IF/ELSE
            } catch(HibernateError e){
                //If the database disconnects then display an error message.
                    ErrorMessage message = new ErrorMessage();
                    message.errorMessage("DATABASE DISCONNECTED! - SYSTEM SHUTDOWN!");
                    controller.exit();
                    System.exit(0);
                }//END TRY/CATCH
            //If the user didn't choice a report then it'll display an error message.
        } else {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage("NO CHOICE PICKED!");
        }//END IF/ELSE
    }//END METHOD displayReport
} //END CLASS GenerateReportController
