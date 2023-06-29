package halverson.c195;

import halverson.c195.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class is the main function, starts and ends the application */
public class Main extends Application {

    /** This method starts the application
     * @param stage the stage of the desktop application
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 437, 570);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    /** This is the main function, initiates db connection, launches the program, and ends the db connection
     * @param args arguments
     */
    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();
        launch();
        JDBC.closeConnection();

    }
}