package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;


import java.io.IOException;

/**
 * Starts the application.
 */
public class Start extends Application {

    /**
     * Starts the program and loads the log-in screen
     * @param primaryStage Takes in the primary stage
     * @throws IOException Throws Input/Output exception to be handled elsewhere (in Application)
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Creates the log-in screen
        Parent root = FXMLLoader.load(getClass().getResource("Log-In.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graded Unit 2");
        primaryStage.show();
    }



}
