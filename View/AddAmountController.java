package View;

import com.LeeGlen.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.HibernateException;

import java.io.IOException;

/**
 * Gets the stock and then adds the stock to the product.
 */
public class AddAmountController {
    /**
     * The add button.
     */
    @FXML
    public Button addButton;
    /**
     * The instruction label.
     */
    @FXML
    public Label addLabel;
    /**
     * The textfield the user can use to enter data.
     */
    @FXML
    public TextField addTextField;

    /**
     * The product specified by the user.
     */
    private String product;
    /**
     * The object that is the access to the database.
     */
    private Controller controller;

    /**
     * When button pressed get text from the field and then add stock to the product.
     *
     * @param actionEvent Listens for the button being clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void addStock(ActionEvent actionEvent) throws IOException {
        try {
            //Checks to see if the user has entered a number
            if (!(addTextField.getText() == null)) {
                //Initialise variable
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                //Gets number from textbox
                int addedStock = Integer.parseInt(addTextField.getText());
                //Adds the stock for specified product.
                String found = controller.addStock(product, addedStock);
                alert.initStyle(StageStyle.UTILITY);
                alert.setHeaderText("Read Information Below!");
                //Depending if the product was found or not it'll display an alert box.
                if (found.equals("Found Product")) {
                    //Displays if the product was found informing the user that the stock is added.
                    alert.setContentText("STOCK ADDED");
                    alert.setResizable(true);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                } else if (found.equals("Can't Find Product")) {
                    //Displays if the product wasn't found informing the user that the product wasn't found.
                    alert.setContentText("PRODUCT NOT FOUND!");
                    alert.setResizable(true);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                }//END IF/ELSE
                //If the user is the admin then the main menu for admin will show up.
                if (controller.getJobType().equals("Admin")) {
                    //Creates the main menu.
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("MainMenuAdmin.fxml"));
                    Parent mainMenuParent = loader.load();
                    Scene mainMenuScene = new Scene(mainMenuParent);

                    //Passes in the controller to reused.
                    MainMenuAdminController mainMenuAdminController = loader.getController();
                    mainMenuAdminController.initData(controller);

                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.hide();
                    window.setScene(mainMenuScene);
                    window.show();
                } else {
                    //Will display the main menu for employee.
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("MainMenuEmployee.fxml"));
                    Parent mainMenuParent = loader.load();
                    Scene mainMenuScene = new Scene(mainMenuParent);

                    //Passes in the controller to be reused.
                    MainMenuEmployeeController mainMenuEmployeeController = loader.getController();
                    mainMenuEmployeeController.initData(controller);

                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.hide();
                    window.setScene(mainMenuScene);
                    window.show();
                }//END IF/ELSE
                //If the user hasn't entered anything then an alert will inform them of doing so.
            } else {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage("NO NUMBER ENTERED!");
            }//END ELSE
        } catch (HibernateException e) {
            //Displays a message saying the database has disconnected and close the program.
            ErrorMessage message = new ErrorMessage();
            message.errorMessage("DATABASE DISCONNECTED! - SYSTEM SHUTDOWN!");
            controller.exit();
            System.exit(0);
        } catch (NumberFormatException e){
            //If the user entered in string then an error message will inform the user that what's been given isn't a number
            ErrorMessage message = new ErrorMessage();
            message.errorMessage("NOT A NUMBER!");
        }//END TRY/CATCH
    }//END METHOD addStock

    /**
     * Takes in the product chosen by the user and passes the controller.
     * @param product The product chosen by the user.
     * @param controller The controller that's used everywhere.
     */
    public void initData(String product, Controller controller) {
        //Will take in data from the previous FXML controller.
        this.product = product;
        this.controller = controller;
    }//END initData

} //END CLASS AddStockController
