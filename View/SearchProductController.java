package View;

import com.LeeGlen.Controller;
import com.LeeGlen.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Searches for the product the user has entered.
 */
public class SearchProductController {

    /**
     * The object that is the access to the database.
     */
    private Controller controller;

    /**
     * Searches for the product.
     */
    @FXML
    public Button searchButton;
    /**
     * Text field for user input.
     */
    @FXML
    public TextField searchTextField;
    /**
     * Displays instructions.
     */
    @FXML
    public Label searchLabel;

    /**
     * Used to get the product to then display.
     *
     * @param actionEvent Listens for the button being clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void searchProduct(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            //If the user has entered a product
            if (!(searchTextField.getText() == null)) {
                //Gets the product(s) and stores into a list.
                ArrayList<Product> products = controller.searchProduct(searchTextField.getText());
                if (products == null) {
                    ErrorMessage message = new ErrorMessage();
                    message.errorMessage("PRODUCT NOT FOUND!");
                } else {
                    //Creates product found scene.
                    loader.setLocation(getClass().getResource("ProductFound.fxml"));
                    Parent productParent = loader.load();
                    Scene productScene = new Scene(productParent);
                    //Passes in the controller and the product(s).
                    ProductFoundController productFoundController = loader.getController();
                    productFoundController.initData(controller, products);

                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.hide();
                    window.setScene(productScene);
                    window.show();
                }// END IF/ELSE
                //If the user hasn't entered anything a error message appears.
            }else{
                    ErrorMessage message = new ErrorMessage();
                    message.errorMessage("HAVEN'T ENTERED PRODUCT!");
                }//END IF/ELSE
            } catch(HibernateException e){
                //Displays a message saying the database has disconnected and close the program.
                ErrorMessage message = new ErrorMessage();
                message.errorMessage("DATABASE DISCONNECTED! - SYSTEM SHUTDOWN!");
                controller.exit();
                System.exit(0);
            }//END TRY/CATCH
        }//END METHOD searchProduct

        /**
         * Initialises the controller.
         *
         * @param controller The controller that's used everywhere.
         */
        public void initData (Controller controller){
            //Initialise controller.
            this.controller = controller;
        }//END METHOD initData

    }//END CLASS searchProductController
