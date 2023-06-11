package halverson.c195;

import halverson.c195.helper.DisplayAlert;
import halverson.c195.helper.GetId;
import halverson.c195.helper.JDBC;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public TextField userNameField;
    public TextField passwordField;
    public Label locationLabel;

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
