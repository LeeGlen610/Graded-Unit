package View;

import com.LeeGlen.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Displays the product data.
 */
public class ProductFoundController {

    /**
     * The object that is the access to the database.
     */
    private Controller controller;
    /**
     * Holds the products data.
     */
    private ObservableList<Product> observableList;

    /**
     * Defines that it is a product table.
     */
    @FXML
    public TableView<Product> productTable;
    /**
     * Will display the main menu.
     */
    @FXML
    public Button menuButton;
    /**
     * The ID columm for the table.
     */
    @FXML
    public TableColumn<Product, Integer> col_id;
    /**
     * The name column for the table.
     */
    @FXML
    public TableColumn<Product, String> col_name;
    /**
     * The type column for the table.
     */
    @FXML
    public TableColumn<Product, String> col_type;
    /**
     * The price column for the table.
     */
    @FXML
    public TableColumn<Product, Double> col_price;
    /**
     * The stock column for the table.
     */
    @FXML
    public TableColumn<Product, Integer> col_stock;

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
     * Creates the table from the product data.
     * @param controller The controller that's used everywhere.
     * @param products The list that holds the product data.
     */
    public void initData(Controller controller, ArrayList<Product> products) {
        this.controller = controller;
        this.observableList = FXCollections.observableArrayList(products);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productTable.setItems(observableList);
    }//END METHOD initData
}//END CLASS ProductFoundController
