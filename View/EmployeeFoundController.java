package View;

import com.LeeGlen.Address;
import com.LeeGlen.Controller;
import com.LeeGlen.Employee;
import com.LeeGlen.Product;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Displays the employee data to a table
 */
public class EmployeeFoundController {

    /**
     * The object that is the access to the database.
     */
    private Controller controller;
    /**
     * Holds the employees date to be used for the table.
     */
    private ObservableList<Employee> observableList;

    /**
     * Defines that it is a employee table.
     */
    @FXML
    public TableView<Employee> employeeTable;
    /**
     * The ID column for the table.
     */
    @FXML
    public TableColumn<Employee, Integer> col_id;
    /**
     * The first name column for the table.
     */
    @FXML
    public TableColumn<Employee, String> col_firstName;
    /**
     * The last name column for the table.
     */
    @FXML
    public TableColumn<Employee, String> col_lastName;
    /**
     * The salary column for the table.
     */
    @FXML
    public TableColumn<Employee, Double> col_salary;
    /**
     * The manager column for the table.
     */
    @FXML
    public TableColumn<Employee, String> col_manager;
    /**
     * The address column for the table.
     */
    @FXML
    public TableColumn<Employee, Address> col_address;
    /**
     * The main menu button.
     */
    @FXML
    public Button menuButton;

    /**
     * Display Main Menu
     * @param actionEvent Listens for the button being clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void mainMenu(ActionEvent actionEvent) throws IOException {
        if (controller.getJobType().equals("Admin")) {
            //Creates main menu admin.
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
        } else {
            //Creates main menu employee.
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
     * Creates the table from the employee data.
     * @param controller The controller that's used everywhere.
     * @param employeeArrayList The list that holds the customer data.
     */
    public void initData(Controller controller, ArrayList<Employee> employeeArrayList) {
        //Initialise controller
        this.controller = controller;
        //Assigns the data to the correct columns.
        this.observableList = FXCollections.observableArrayList(employeeArrayList);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        //Will take in either a dash or the managers id.
        col_manager.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> param) {
                //If the manager id exists for that employee.
                if (param.getValue().getManager() != null) {
                    //Return the manager ID.
                    return new SimpleStringProperty(String.valueOf(param.getValue().getManager().getId()));
                    //Otherwise
                } else {
                    //Return a dash.
                    return new SimpleStringProperty("-");
                }//END IF/ELSE
            }//END METHOD call
        });
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        //Adds the data to the table.
        employeeTable.setItems(observableList);
    }//END METHOD initData
}//END CLASS EmployeeFoundController
