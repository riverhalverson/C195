package halverson.c195;

import halverson.c195.DAO.CustomerQuery;
import halverson.c195.helper.DisplayAlert;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/** This class is the controller for the add customer window */
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

    /** This method populates the country combo box */
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

    /** This method populates division combo box */
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
    /** This method runs query on the database
     * @param sql the sql query to run
     * @return rs resultset of the query that was ran
     */
    public ResultSet runQuery(String sql){
        ResultSet rs = null;

        try{
            Statement statement = JDBC.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sql);

            rs.beforeFirst();
        }   catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }

    /** This method saves the entered information to the customer table
     * @param actionEvent the save button is clicked
     */
    public void OnAddCustomerSaveClick(ActionEvent actionEvent) throws SQLException, IOException {
        String name = AddCustomerNameField.getText();
        String address = AddCustomerAddressField.getText();
        String postalCode = AddCustomerPostalField.getText();
        String phoneNumber = AddCustomerPhoneField.getText();

        if(name.isEmpty()){
            DisplayAlert.emptyAlert("Name");
            throw new RuntimeException("Name field cannot be blank");
        }
        if(address.isEmpty()){
            DisplayAlert.emptyAlert("Address");
            throw new RuntimeException("Address field cannot be blank");
        }
        if(postalCode.isEmpty()){
            DisplayAlert.emptyAlert("Postalcode");
            throw new RuntimeException("Postalcode field cannot be blank");
        }
        if(phoneNumber.isEmpty()){
            DisplayAlert.emptyAlert("Phone number");
            throw new RuntimeException("Phone number field cannot be blank");
        }

        int rowsAffected = CustomerQuery.insertCustomer(name, address, postalCode, phoneNumber, divisionId);
        //display main menu
        Parent root = FXMLLoader.load(getClass().getResource("Mainscreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addProductsMenu = new Scene(root, 1640, 900);
        stage.setTitle("Add Product Menu");
        stage.setScene(addProductsMenu);
        stage.show();
    }

    /** This method returns the user to the main menu
     * @param actionEvent the exit button is clicked
     */
    public void OnCancelAddCustomerClick(ActionEvent actionEvent) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Mainscreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addProductsMenu = new Scene(root, 1640, 900);
        stage.setTitle("Add Product Menu");
        stage.setScene(addProductsMenu);
        stage.show();
    }

    /** This method gets the selected country from the combo box
     * @param actionEvent the selection is made with the combo box
     */
    public void OnCountrySelect(ActionEvent actionEvent) throws SQLException {
        DivisionComboBox();
    }

    /** This method gets the selected division
     * @param actionEvent the selection is made with the combo box
     */
    public void OnDivisionSelect(ActionEvent actionEvent) throws SQLException {
        division = AddCustomerDivisionsBox.getValue();
        divisionId = GetId.getDivisionId(division);
    }

}
