package View;

import com.LeeGlen.Controller;
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

/**
 * Will get the product from the user.
 */
public class AddStockController {
    /**
     * The object that is the access to the database.
     */
    private Controller controller;

    /**
     * The confirm button.
     */
    @FXML
    public Button stockButton;
    /**
     * Label for instructions.
     */
    @FXML
    public Label stockLabel;
    /**
     * Text field for user input.
     */
    @FXML
    public TextField stockTextBox;

    /**
     * Used to get the product to then pass onto another FXML controller.
     * @param actionEvent Listens for the button being clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void searchProduct(ActionEvent actionEvent) throws IOException {
        try {
            //Checks to see if the text box isn't empty and if it isn't then
            if (!(stockTextBox.getText().isEmpty())) {
                //Takes in the users product to be passed on.
                String product = stockTextBox.getText();
                //Creates add amount scene.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AddAmount.fxml"));
                Parent stockParent = loader.load();
                Scene stockScene = new Scene(stockParent);

                //Passes in the controller and the users product name.
                AddAmountController controller = loader.getController();
                controller.initData(product, this.controller);

                //Displays the scene.
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.hide();
                window.setScene(stockScene);
                window.show();
                //If the text box is empty then
            } else {
                //An error message is displayed.
                ErrorMessage message = new ErrorMessage();
                message.errorMessage("NO PRODUCT ENTERED!");
            }//END IF/ELSE
        } catch (HibernateException e) {
                //Displays a message saying the database has disconnected and close the program.
                ErrorMessage message = new ErrorMessage();
                message.errorMessage("DATABASE DISCONNECTED! - SYSTEM SHUTDOWN!");
                controller.exit();
                System.exit(0);
        }//END TRY/CATCH
    }//END METHOD searchProduct

    /**
     * Takes in the controller.
     * @param controller The controller that's used everywhere.
     */
    public void initData(Controller controller){
        //Initialises controller.
        this.controller = controller;
    }//END METHOD initData


}//END CLASS AddStockController
