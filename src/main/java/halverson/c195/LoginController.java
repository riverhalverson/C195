package halverson.c195;

import halverson.c195.helper.DisplayAlert;
import halverson.c195.helper.GetId;
import halverson.c195.helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    public void OnLoginClick(ActionEvent actionEvent) throws SQLException {
        String userName = "";
        String password = "";
        int userId = 0;


        userName = userNameField.getText();
        password = passwordField.getText();

        userId = GetId.getUserId(userName);

        //if userid exists continue
        if(userId != 0) {

            String sql = "SELECT * FROM USERS WHERE User_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            String queryUserName = "";
            String queryPassword = "";

            while (rs.next()) {
                queryUserName = rs.getString("User_Name");
                queryPassword = rs.getString("Password");
            }

        }
        else{
            DisplayAlert.invalidLogin();
        }

    }


}
