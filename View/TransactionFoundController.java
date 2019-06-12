package View;

import com.LeeGlen.*;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Displays the transaction data.
 */
public class TransactionFoundController {

    /**
     * The object that is the access to the database.
     */
    private Controller controller;
    /**
     * Holds the transaction data.
     */
    private ObservableList<TransactionLog> transactionLogs;

    /**
     * Defines that it is a transaction log table.
     */
    @FXML
    public TableView<TransactionLog> transactionTable;
    /**
     * The ID column of the table.
     */
    @FXML
    public TableColumn<TransactionLog, Integer> col_id;
    /**
     * The total price column of the table.
     */
    @FXML
    public TableColumn<TransactionLog, Double> col_totalPrice;
    /**
     * The date of transaction column of the table.
     */
    @FXML
    public TableColumn<TransactionLog, Date> col_year;
    /**
     * The product column of the table.
     */
    @FXML
    public TableColumn<TransactionLog, String> col_product;
    /**
     * The customer column of the table.
     */
    @FXML
    public TableColumn<TransactionLog, String> col_customer;
    /**
     * The employee column of the table.
     */
    @FXML
    public TableColumn<TransactionLog, String> col_employee;
    /**
     * Displays the main menu.s
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

            //Passes in the controller into the main menu
            MainMenuAdminController mainMenuAdminController = loader.getController();
            mainMenuAdminController.initData(controller);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.hide();
            window.setScene(mainMenuScene);
            window.show();
        }//END IF/ELSE
    }//END METHOD mainMenu

    /**
     * Creates the table from the transaction data.
     * @param controller The controller that's used everywhere.
     * @param transactionLogArrayList The list that holds the transactions.
     */
    public void initData(Controller controller, ArrayList<TransactionLog> transactionLogArrayList){
        this.controller = controller;
        transactionLogs = FXCollections.observableArrayList(transactionLogArrayList);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_totalPrice.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_employee.setCellValueFactory(new PropertyValueFactory<>("employee"));
        col_customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        col_product.setCellValueFactory(new PropertyValueFactory<>("product"));
        transactionTable.setItems(transactionLogs);
    }//END METHOD initData

}//END CLASS TransactionFoundController
