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
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.util.Optional;

/**
 * Displays an main menu for administrators.
 */
public class MainMenuAdminController {

    /**
     * The object that is the access to the database.
     */
    private Controller controller;

    /**
     * Will add stock to a product.
     */
    @FXML
    public Button stockButton;
    /**
     * Will search for a product.
     */
    @FXML
    public Button searchButton;
    /**
     * Displays either employee, customer or supplier info.
     */
    @FXML
    public Button displayECSButton;
    /**
     * Displays the transactions from a certain date.
     */
    @FXML
    public Button displayTLButton;
    /**
     * Will Generate a chosen report.
     */
    @FXML
    public Button generateReportsButton;
    /**
     * Exits out of the system.
     */
    @FXML
    public Button exit;
    /**
     * Will mimic purchasing an item.
     */
    @FXML
    public Button purchaseButton;


    /**
     * Will add stock to an item.
     * @param mouseEvent Checks if the button is clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void addStock(MouseEvent mouseEvent) throws IOException {
        //Creates the add stock scene and displays it.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddStock.fxml"));
        Parent addStockParent = loader.load();
        Scene addStockScene = new Scene(addStockParent);

        //Passes in the controller.
        AddStockController addStockController = loader.getController();
        addStockController.initData(controller);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.hide();
        window.setScene(addStockScene);
        window.show();
    }//END METHOD addStock


    /**
     * Will initialise the controller
     * @param controller The controller that gets used everywhere.
     */
    public void initData(Controller controller) {
        //Initialise controller.
        this.controller = controller;
    }//END METHOD initData

    /**
     * Exits out of the system.
     * @param actionEvent Checks to see if the button has been clicked.
     */
    public void exit(ActionEvent actionEvent) {
        //Closes hibernate and then the program.
        controller.exit();
        System.exit(0);
    } //END METHOD exit

    /**
     * Searches for a product.
     * @param actionEvent Checks to see if the button has been clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void searchProduct(ActionEvent actionEvent) throws IOException {
        //Creates the search product scene and displays it.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchProduct.fxml"));
        Parent searchProductParent = loader.load();
        Scene searchProductScene = new Scene(searchProductParent);

        //Passes in the controller.
        SearchProductController searchProductController = loader.getController();
        searchProductController.initData(controller);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.hide();
        window.setScene(searchProductScene);
        window.show();
    } //END METHOD searchProduct

    /**
     * Displays either employee, customer or supplier details.
     * @param actionEvent Checks to see if the button has been clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void displayECSDetails(ActionEvent actionEvent) throws IOException {
        //Creates the display ECS details scene and displays it.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DisplayECSDetails.fxml"));
        Parent displayDetailsParent = loader.load();
        Scene displayDetailsScene = new Scene(displayDetailsParent);

        //Passes in the controller.
        DisplayECSDetailsController displayECSDetailsController = loader.getController();
        displayECSDetailsController.initData(controller);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.hide();
        window.setScene(displayDetailsScene);
        window.show();
    } //END METHOD displayECSDetails

    /**
     * Displays transactions on the date chosen.
     * @param actionEvent Checks to see if the button has been clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void displayTransactions(ActionEvent actionEvent) throws IOException {
        //Creates the display transactions scene and displays it.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DisplayTransactions.fxml"));
        Parent displayTransactionParent = loader.load();
        Scene displayTransactionScene = new Scene(displayTransactionParent);

        //Passes in the controller.
        DisplayTransactionsController displayTransactionsController = loader.getController();
        displayTransactionsController.initData(controller);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.hide();
        window.setScene(displayTransactionScene);
        window.show();
    }//END METHOD displayTransactions

    /**
     * Mimics what would happen if a purchase was made.
     * @param actionEvent Checks to see if the button has been clicked.
     */
    public void productPurchased(ActionEvent actionEvent) {
        try {
            //Initialises a text input dialog.
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setHeaderText("Enter What Is Asked Below!");
            dialog.setContentText("Please Enter A Product Name:");
            dialog.setResizable(true);
            dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            //Takes in the users valuie
            Optional<String> result = dialog.showAndWait();
            //Makes the purchase and adds the transaction.
            String theResult = controller.purchaseProduct(result.get());
            //If the product isn't found.
            if (theResult.equals("Product Doesn't Match")) {
                //Display an error message.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setHeaderText("Read Information Below!");
                alert.setContentText("PRODUCT NOT FOUND! - CONTACT ADMINISTRATOR!");
                alert.setResizable(true);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
                //If it is found.
            } else if (theResult.equals("Product Match!")) {
                //Display a message saying that the product is found.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setHeaderText("Read Information Below!");
                alert.setContentText("PRODUCT PURCHASED!");
                alert.setResizable(true);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }//END IF/ELSE
        } catch (HibernateException e){
            //If the database disconnects then display an error message.
            ErrorMessage message = new ErrorMessage();
            message.errorMessage("DATABASE DISCONNECTED! - SYSTEM SHUTDOWN!");
            System.exit(0);
        } //END TRY/CATCH
    }//END METHOD productPurchased

    /**
     * Generates a report based on choice.
     * @param actionEvent Checks to see if the button has been clicked.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void generateReport(ActionEvent actionEvent) throws IOException {
        //Creates the generate reports scene and displays it.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GenerateReport.fxml"));
        Parent generateReportParent = loader.load();
        Scene generateReportScene = new Scene(generateReportParent);

        //Passes in the controller.
        GenerateReportController generateReportController = loader.getController();
        generateReportController.initData(controller);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.hide();
        window.setScene(generateReportScene);
        window.show();
    }//END METHOD generateReport
}//END CLASS MainMenuAdminController
