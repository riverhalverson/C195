package halverson.c195;

import halverson.c195.helper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class is the controller for the login window */
public class LoginController implements Initializable {
    public TextField userNameField;
    public TextField passwordField;
    public Label locationLabel;
    public Text loginLabel;
    public Text usernameLabel;
    public Text passwordLabel;
    public Button loginbuttonLabel;
    public Text yourlocationLabel;

    public static boolean english = true;
    public static boolean french = false;

    public String message = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResourceBundle rb = ResourceBundle.getBundle("halverson.c195/Languages_fr", Locale.getDefault());
        ResourceBundle rl = ResourceBundle.getBundle("halverson.c195/Locations");

        String country = Locale.getDefault().getCountry();

        if(Locale.getDefault().getLanguage().equals("fr")){
            french = true;
            english = false;
            loginLabel.setText(rb.getString("login"));
            usernameLabel.setText(rb.getString("username"));
            passwordLabel.setText(rb.getString("password"));
            loginbuttonLabel.setText(rb.getString("login"));
            yourlocationLabel.setText(rb.getString("yourlocation"));
        }
        locationLabel.setText(country);
    }

    /** This method checks the users credentials and continues them to the main screen if matching
     * @param actionEvent the login button is clicked
     */
    public void OnLoginClick(ActionEvent actionEvent) throws SQLException, IOException {
        boolean found = true;
        String username = "";
        String password = "";

        username = userNameField.getText();
        password = passwordField.getText();

        int userId = GetId.getUserId(username);


        //check for blank inputs
        if(username.isBlank() || password.isBlank()) {
            if(english){
                DisplayAlert.customError("Username or password is blank");
                LoginLogger.log(false);
            }
            else{
                DisplayAlert.customError("Le nom d'utilisateur ou le mot de passe est vide");
                LoginLogger.log(false);
            }
        }
        else {

            //run query on database
            String sql = "SELECT * FROM USERS WHERE User_Name = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            String queryUsername = "";
            String queryPassword = "";

            //gets query results
            if(rs.next()){
                do{
                    queryUsername = rs.getString("User_Name");
                    queryPassword = rs.getString("Password");
                } while(rs.next());
            } else{
                //if query was not successful
                found = false;
                if (english) {
                    DisplayAlert.customError("Username not found");
                    LoginLogger.log(false);
                } else {
                    DisplayAlert.customError("Nom d'utilisateur introuvable");
                    LoginLogger.log(false);
                }
            }
            //if user was found
            if (found) {
                if (username.equals(queryUsername) && password.equals(queryPassword)) {
                    System.out.println("Success");
                    LoginLogger.log(true);

                    UpcomingApts.checkApts(userId);

                    Parent root = FXMLLoader.load(getClass().getResource("Mainscreen.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene addProductsMenu = new Scene(root, 1640, 900);
                    stage.setTitle("Add Product Menu");
                    stage.setScene(addProductsMenu);
                    stage.show();
                } else {
                    if (english) {
                        DisplayAlert.customError("Password incorrect");
                        LoginLogger.log(false);
                    } else {
                        DisplayAlert.customError("Mot de passe incorrect");
                        LoginLogger.log(false);
                    }
                }
            }
        }
    }

}
