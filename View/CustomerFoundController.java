package View;

import com.LeeGlen.Address;
import com.LeeGlen.Controller;
import com.LeeGlen.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Displays customer details in a table.
 */
public class CustomerFoundController {

    /**
     * The object that is the access to the database.
     */
    private Controller controller;
    /**
     * Holds the customer data.
     */
    private ObservableList<Customer> observableList;

    /**
     * Defines that it is a customer table.
     */
    @FXML
    public TableView<Customer> customerTable;
    /**
     * The ID columm for the table.
     */
    @FXML
    public TableColumn<Customer, Integer> col_id;
    /**
     * The first name column for the table.
     */
    @FXML
    public TableColumn<Customer, String> col_firstName;
    /**
     * The last name column for the table.
     */
    @FXML
    public TableColumn<Customer, String> col_lastName;
    /**
     * The address column for the table.
     */
    @FXML
    public TableColumn<Customer, Address> col_address;
    /**
     * Displays the main menu.
     */
    @FXML
    public Button menuButton;

    /**
     * Will return to the main menu depending on the job title.
     * @param actionEvent Listens for the button being clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void mainMenu(ActionEvent actionEvent) throws IOException {
        //If the user is the admin
        if (controller.getJobType().equals("Admin")) {
            //Creates the admin main menu
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainMenuAdmin.fxml"));
            Parent mainMenuParent = loader.load();
            Scene mainMenuScene = new Scene(mainMenuParent);

            //Passes in the controller into the main menu
            MainMenuAdminController mainMenuAdminController = loader.getController();
            mainMenuAdminController.initData(controller);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.hide();
            window.setScene(mainMenuScene);
            window.show();
            //If the user is not an admin
        } else {
            //Creates the employee main menu
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainMenuEmployee.fxml"));
            Parent mainMenuParent = loader.load();
            Scene mainMenuScene = new Scene(mainMenuParent);

            //Passes in the controller into the maain menu
            MainMenuAdminController mainMenuAdminController = loader.getController();
            mainMenuAdminController.initData(controller);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.hide();
            window.setScene(mainMenuScene);
            window.show();
        }//END IF/ElSE
    }//END METHOD mainMenu

    /**
     * Creates the table from the customer data.
     * @param controller The controller that's used everywhere.
     * @param customerArrayList The list that holds the customer data.
     */
    public void initData(Controller controller, ArrayList<Customer> customerArrayList){
        //Initialises controller.
        this.controller = controller;
        //Assigns the data to the correct columns.
        observableList = FXCollections.observableArrayList(customerArrayList);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        //Adds the data to the table.
        customerTable.setItems(observableList);
    }

}
