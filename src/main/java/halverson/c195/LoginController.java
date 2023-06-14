package halverson.c195;

import halverson.c195.helper.DisplayAlert;
import halverson.c195.helper.GetId;
import halverson.c195.helper.JDBC;
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
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

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

    public void OnLoginClick(ActionEvent actionEvent) throws SQLException, IOException {
        boolean found = true;
        String username = "";
        String password = "";

        username = userNameField.getText();
        password = passwordField.getText();

        //check for blank inputs
        if(username.isBlank() || password.isBlank()) {
            if(english){
                DisplayAlert.customError("Username or password is blank");
            }
            else{
                DisplayAlert.customError("Le nom d'utilisateur ou le mot de passe est vide");
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
                } else {
                    DisplayAlert.customError("Nom d'utilisateur introuvable");
                }
            }
            //if user was found
            if (found) {
                if (username.equals(queryUsername) && password.equals(queryPassword)) {
                    System.out.print("Success");
                    Parent root = FXMLLoader.load(getClass().getResource("Mainscreen.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene addProductsMenu = new Scene(root, 1300, 900);
                    stage.setTitle("Add Product Menu");
                    stage.setScene(addProductsMenu);
                    stage.show();
                } else {
                    if (english) {
                        DisplayAlert.customError("Password incorrect");
                    } else {
                        DisplayAlert.customError("Mot de passe incorrect");
                    }
                }
            }
        }
    }
}
