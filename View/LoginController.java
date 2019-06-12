package View;

import com.LeeGlen.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.exception.JDBCConnectionException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ConnectException;

/**
 * Displays the log-in screen.
 */
public class LoginController {

    /**
     * Creates controller class that'll be used everywhere in the program.
     */
    private Controller controller = new Controller();

    /**
     * Button Used To Start Method.
     */
    @FXML
    public Button loginButton;
    /**
     * Field To Enter Password.
     */
    @FXML
    public PasswordField passwordField;
    /**
     * Field To Enter Username.
     */
    @FXML
    public TextField usernameField;
    /**
     * Label To Show Where To Enter Password.
     */
    @FXML
    public Label passwordLabel;
    /**
     * Label To Show Where To Enter Username.
     */
    @FXML
    public Label usernameLabel;

    /**
     * Takes in the data from the username and password field and checks to see if valid.
     * @param actionEvent Listens for button press.
     * @throws IOException Throws away input/output exception to be validated elsewhere.
     */
    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            //Initialise variable
            String jobType;
            //Will pass through the username and password the user has entered
            // and then will take in the value coming back from the method.
            jobType = controller.logIn(usernameField.getText(), passwordField.getText());
            //If the employee is the admin
            if (jobType.equals("Admin")) {
                //Displays The Admin's Menu
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MainMenuAdmin.fxml"));
                Parent mainMenuParent = loader.load();
                Scene mainMenuScene = new Scene(mainMenuParent);

                //Passes through the controller made here so that no value is lost.
                MainMenuAdminController mainMenuAdminController = loader.getController();
                mainMenuAdminController.initData(this.controller);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.hide();
                window.setScene(mainMenuScene);
                window.show();

                //If either the employee's username or password does not match then
                //the fields are cleared and then an error message will display using a label
            } else if (jobType.equals("ERROR")) {
                usernameField.clear();
                passwordField.clear();
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.errorMessage("USERNAME OR PASSWORD INCORRECT - TRY AGAIN!");
            } else  {
                //Displays the menu for employees only
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MainMenuEmployee.fxml"));
                Parent mainMenuParent = loader.load();
                Scene mainMenuScene = new Scene(mainMenuParent);

                MainMenuEmployeeController mainMenuEmployeeController = loader.getController();
                mainMenuEmployeeController.initData(controller);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.hide();
                window.setScene(mainMenuScene);
                window.show();
            }//END IF/ELSE
            //Catches if the database is not connected
        } catch (HibernateException e){
            //If the database cant connect then the error message displays.
                ErrorMessage message = new ErrorMessage();
                message.errorMessage("CAN'T CONNECT TO DATABASE!");
                controller.exit();
        }//END TRY/CATCH
    }//END METHOD buttonPressed

}//END CLASS LoginController
