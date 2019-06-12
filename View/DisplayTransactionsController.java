package View;

import com.LeeGlen.Controller;
import com.LeeGlen.TransactionLog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Gets the transaction data and passes it to another FXML controller.
 */
public class DisplayTransactionsController {

    /**
     * When button pressed get text from the field and then add stock to the product.
     */
    private Controller controller;

    /**
     * The transaction button.
     */
    @FXML
    public Button transactionButton;
    /**
     * Used to get the date that the user has chosen.
     */
    @FXML
    public DatePicker transactionDate;
    /**
     * Displays instructions.
     */
    @FXML
    public Label transactionLabel;

    /**
     * Gets the transaction logs based of the chosen date.
     * @param actionEvent Listens for the button being clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void findTransaction(ActionEvent actionEvent) throws IOException {
        try {
            //If the user has chosen a date then
            if (transactionDate.getValue() != null) {
                //Gets the date from the date picker.
                LocalDate date = transactionDate.getValue();
                //Gets all of the transactions on that date.
                ArrayList<TransactionLog> transactionLogs = controller.displayTransactionLog(date);

                //Creates transaction found scene.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("TransactionFound.fxml"));
                Parent transactionParent = loader.load();
                Scene transactionScene = new Scene(transactionParent);

                //Passes in the controller and the transaction logs.
                TransactionFoundController controller = loader.getController();
                controller.initData(this.controller, transactionLogs);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.hide();
                window.setScene(transactionScene);
                window.show();
                //If the date hasn't been picked.
            } else {
                //Displays an error message.
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.errorMessage("NO DATE ENTERED!");
            }//END IF/ELSE
        } catch (HibernateException e){
            //If the database disconnects or unavailable display an error message.
                ErrorMessage message = new ErrorMessage();
                message.errorMessage("DATABASE DISCONNECTED! - SYSTEM SHUTDOWN!");
                controller.exit();
                System.exit(0);
        }
    }//END METHOD findTransaction

    /**
     * Initialises the controller
     * @param controller The controller that's used everywhere.
     */
    public void initData(Controller controller){
        //Initialises controller.
        this.controller = controller;
    }//END METHOD initData
}//END CLASS DisplayTransactionsController
