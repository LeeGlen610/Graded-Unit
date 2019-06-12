package View;

import com.LeeGlen.Controller;
import com.LeeGlen.Customer;
import com.LeeGlen.Employee;
import com.LeeGlen.Supplier;
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
import java.util.ArrayList;

/**
 * Will display details depending on user choice.
 */
public class DisplayECSDetailsController {

    /**
     * List used to make choices for choice box.
     */
    private ObservableList<String> choices = FXCollections.observableArrayList("Employees", "Customers", "Suppliers");
    /**
     * The object that is the access to the database.
     */
    private Controller controller;

    /**
     * Choice box for the user to choose.
     */
    @FXML
    public ChoiceBox<String> detailChoice;
    /**
     * Button used to display details.
     */
    @FXML
    public Button detailButton;
    /**
     * Used to display instructions
     */
    @FXML
    public Label detailLabel;

    /**
     * Takes in the controller and assigns the choices to the choice box.
     * @param controller Controller that's used everywhere.
     */
    public void initData(Controller controller){
        //Initialise variables.
        this.controller = controller;
        detailChoice.setItems(choices);
    }

    /**
     * Displays details depending on what's been picked.
     * @param actionEvent Listens for button click.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void displayDetails(ActionEvent actionEvent) throws IOException {
        try {
            //Checks to see if the user has chosen something.
            //If so then
        if (!(detailChoice.getValue() == null)) {
            //Gets the choice from the choice box.
            String choice = detailChoice.getValue();
            //Initialise variables.
            FXMLLoader loader = new FXMLLoader();
            Parent foundParent;
            Scene foundScene;
            Stage window;
            //Checks to see which choice the user has made.
            switch (choice) {
                //If user picked employees then
                case "Employees":
                    //Employees data is asked for and received.
                    ArrayList<Employee> employees = controller.displayEmployeeCustomerSupplierInfo(choice);
                    //Creates the employee found scene to display employee data.
                    loader.setLocation(getClass().getResource("EmployeeFound.fxml"));
                    foundParent = loader.load();
                    foundScene = new Scene(foundParent);
                    //Passes in the controller and the employee data.
                    EmployeeFoundController employeeFoundController = loader.getController();
                    employeeFoundController.initData(controller, employees);

                    window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.hide();
                    window.setScene(foundScene);
                    window.show();
                    break;
                case "Customers":
                    //Customer data is asked for and received.
                    ArrayList<Customer> customers = controller.displayEmployeeCustomerSupplierInfo(choice);
                    //Creates the customer found scene to display customer data.
                    loader.setLocation(getClass().getResource("CustomerFound.fxml"));
                    foundParent = loader.load();
                    foundScene = new Scene(foundParent);

                    //Passes in the controller and the customers data.
                    CustomerFoundController customerFoundController = loader.getController();
                    customerFoundController.initData(controller, customers);

                    window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.hide();
                    window.setScene(foundScene);
                    window.show();
                    break;
                case "Suppliers":
                    //Suppliers data is asked for and received.
                    ArrayList<Supplier> suppliers = controller.displayEmployeeCustomerSupplierInfo(choice);
                    //Creates the suppliers found scene to display suppliers data.
                    loader.setLocation(getClass().getResource("SupplierFound.fxml"));
                    foundParent = loader.load();
                    foundScene = new Scene(foundParent);

                    //Passes in the controller and the suppliers data.
                    SupplierFoundController supplierFoundController = loader.getController();
                    supplierFoundController.initData(controller, suppliers);

                    window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.hide();
                    window.setScene(foundScene);
                    window.show();
                    break;
            }//END SWITCH
            //If no choice has been picked.
        }else {
            //Displays error message.
            ErrorMessage message = new ErrorMessage();
            message.errorMessage("NO CHOICE PICKED!");
        }//END IF/ELSE
            } catch (HibernateException e) {
                //If the database disconnects or unavailable it displays an error message
                    ErrorMessage message = new ErrorMessage();
                    message.errorMessage("DATABASE DISCONNECTED! - SYSTEM SHUTDOWN!");
                    controller.exit();
                    System.exit(0);
            }//END TRY/CATCH
        }//END METHOD displayDetails
}//END CLASS DisplayECSDetailsController
