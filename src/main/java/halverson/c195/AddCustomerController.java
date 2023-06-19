package halverson.c195;

import halverson.c195.helper.GetId;
import halverson.c195.helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCustomerController implements Initializable {

    public TextField AddCustomerNameField;
    public TextField AddCustomerAddressField;
    public TextField AddCustomerPostalField;
    public TextField AddCustomerPhoneField;
    public ComboBox <String> AddCustomerCountriesBox;
    public ComboBox <String> AddCustomerDivisionsBox;
    private String division;
    private int divisionId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryComboBox();
    }

    private void CountryComboBox(){
        ResultSet rs = runQuery("SELECT Country FROM COUNTRIES");
        try{
            while(rs.next()){
                AddCustomerCountriesBox.getItems().add(rs.getString(1));
            }
        }   catch (SQLException ex){
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void DivisionComboBox() throws SQLException {
        String country = AddCustomerCountriesBox.getValue();
        System.out.println(country);

        String sql = "SELECT FIRST_LEVEL_DIVISIONS.Division " + "FROM FIRST_LEVEL_DIVISIONS, COUNTRIES " +
                     "WHERE FIRST_LEVEL_DIVISIONS.Country_ID = COUNTRIES.Country_ID " +
                     "AND COUNTRIES.Country = \"" + country + "\"";

        ResultSet rs = runQuery(sql);
        AddCustomerDivisionsBox.getItems().clear();

        try{
            while(rs.next()){
                AddCustomerDivisionsBox.getItems().add(rs.getString(1));
            }
        }   catch(SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet runQuery(String sql){
        ResultSet rs = null;

        try{
            Statement statement = JDBC.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sql);

            while(rs.next()){
                System.out.println(rs.getString(1));
            }
            rs.beforeFirst();
        }   catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }
    public void OnAddCustomerSaveClick(ActionEvent actionEvent) throws SQLException, IOException {
        //int customerId = getUniqueCustomerId();
        String name = AddCustomerNameField.getText();
        String address = AddCustomerAddressField.getText();
        String postalCode = AddCustomerPostalField.getText();
        String phoneNumber = AddCustomerPhoneField.getText();


        int rowsAffected = insertCustomer(name, address, postalCode, phoneNumber, divisionId);
        System.out.println(rowsAffected);


        //display main menu
        System.out.println("Success");
        Parent root = FXMLLoader.load(getClass().getResource("Mainscreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addProductsMenu = new Scene(root, 1640, 900);
        stage.setTitle("Add Product Menu");
        stage.setScene(addProductsMenu);
        stage.show();
    }

    public void OnCancelAddCustomerClick(ActionEvent actionEvent) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Mainscreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addProductsMenu = new Scene(root, 1640, 900);
        stage.setTitle("Add Product Menu");
        stage.setScene(addProductsMenu);
        stage.show();
    }

    public void OnCountrySelect(ActionEvent actionEvent) throws SQLException {
        DivisionComboBox();
    }

    public void OnDivisionSelect(ActionEvent actionEvent) throws SQLException {
        division = AddCustomerDivisionsBox.getValue();
        divisionId = GetId.getDivisionId(division);
    }

    public static int insertCustomer(String name, String address, String postalCode,
                                     String phoneNumber, int divisionId ) throws SQLException {

        String sql = "INSERT INTO CUSTOMERS  (Customer_Name, Address, Postal_Code, Phone, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES" +
                " (?, ?, ?, ?, NOW(), 'script', NOW(), 'script', ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setInt(5, divisionId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;




    }
/*
    public int getUniqueCustomerId() throws SQLException {
        int userID = 0;
        int uniqueId = 0;

        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            do {
                userID = rs.getInt("Customer_ID");
                if(userID < uniqueId){
                    return uniqueId;
                }
                else{
                    uniqueId++;
                }
            } while (rs.next());
        }
        return uniqueId;

    }

 */

}
