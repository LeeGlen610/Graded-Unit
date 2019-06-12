package View;

import com.LeeGlen.Address;
import com.LeeGlen.Controller;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Displays the supplier data.
 */
public class SupplierFoundController {
    /**
     * The object that is the access to the database.
     */
    private Controller controller;
    /**
     * Holds the suppliers data.
     */
    private ObservableList<Supplier> observableList;

    /**
     * Defines that it is a supplier table.
     */
    @FXML
    public TableView<Supplier> supplierTable;
    /**
     * The ID column of the table.
     */
    @FXML
    public TableColumn<Supplier, Integer> col_id;
    /**
     * The name column of the table.
     */
    @FXML
    public TableColumn<Supplier, String> col_name;
    /**
     * The type column of the table.
     */
    @FXML
    public TableColumn<Supplier, String> col_type;
    /**
     * The address column of the table.
     */
    @FXML
    public TableColumn<Supplier, Address> col_address;
    /**
     * Will display the main menu.
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
     * Creates the table from the supplier data.
     * @param controller The controller that's used everywhere.
     * @param suppliers The list that holds the supplier data.
     */
    public void initData(Controller controller, ArrayList<Supplier> suppliers){
        this.controller = controller;
        this.observableList = FXCollections.observableArrayList(suppliers);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        supplierTable.setItems(observableList);
    }//END METHOD initData

}//END CLASS SupplierFoundController
