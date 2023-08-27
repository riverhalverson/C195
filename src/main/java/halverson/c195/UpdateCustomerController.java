package halverson.c195;

import halverson.c195.DAO.CustomerQuery;
import halverson.c195.entities.CustomerRow;
import halverson.c195.helper.GetId;
import halverson.c195.helper.GetName;
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

/** This class is the controller for the update customer window */
public class UpdateCustomerController implements Initializable {
    public TextField UpdateCustomerNameField;
    public TextField UpdateCustomerAddressField;
    public TextField UpdateCustomerPostalField;
    public TextField UpdateCustomerPhoneField;
    public ComboBox <String> UpdateCustomerCountriesBox;
    public ComboBox <String> UpdateCustomerDivisionsBox;
    public TextField CustomerIDField;
    private String division;
    private int divisionId;

    private CustomerRow customer = null;

    int customerid;
    String name;
    String address;
    String postalCode;
    String phoneNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryComboBox();

    }
    /** This method populates the country combo box */
    private void CountryComboBox(){
        ResultSet rs = runQuery("SELECT Country FROM COUNTRIES");
        try{
            while(rs.next()){
                UpdateCustomerCountriesBox.getItems().add(rs.getString(1));
            }
        }   catch (SQLException ex){
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** This method populates division combo box */
    private void DivisionComboBox() throws SQLException {
        String country = UpdateCustomerCountriesBox.getValue();
        System.out.println(country);

        String sql = "SELECT FIRST_LEVEL_DIVISIONS.Division " + "FROM FIRST_LEVEL_DIVISIONS, COUNTRIES " +
                "WHERE FIRST_LEVEL_DIVISIONS.Country_ID = COUNTRIES.Country_ID " +
                "AND COUNTRIES.Country = \"" + country + "\"";

        ResultSet rs = runQuery(sql);
        UpdateCustomerDivisionsBox.getItems().clear();

        try{
            while(rs.next()){
                UpdateCustomerDivisionsBox.getItems().add(rs.getString(1));
            }
        }   catch(SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /** This method runs query on the database
     * @param sql the sql query to run
     * @return rs resultset of the query that was ran
     */
    private ResultSet runQuery(String sql) {
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
    /** This method brings in the main menus selected customer to modify and preloads the fields/combo boxes
     * @param customerToModify the customer to be modified
     */
    public void CustomerToModify(CustomerRow customerToModify) throws SQLException {
        customer = customerToModify;

        CustomerIDField.setPromptText(String.valueOf((customer.getCustomerid())));
        UpdateCustomerNameField.setText(String.valueOf((customer.getCustomerName())));
        UpdateCustomerAddressField.setText(String.valueOf((customer.getAddress())));
        UpdateCustomerPostalField.setText(String.valueOf((customer.getPostalCode())));
        UpdateCustomerPhoneField.setText(String.valueOf((customer.getPhoneNumber())));

        //sets values to existing, incase nothing is changed
        name = String.valueOf((customer.getCustomerName()));
        address = String.valueOf((customer.getAddress()));
        postalCode = String.valueOf((customer.getPostalCode()));
        phoneNumber = String.valueOf((customer.getPhoneNumber()));
        divisionId = GetId.getDivisionId(customer.getDivision());

        String country = GetName.getCountry(GetId.getDivisionId(customer.getDivision()));

        for(String b : UpdateCustomerCountriesBox.getItems() ){
            if(country.equals(b)){
                UpdateCustomerCountriesBox.setValue(b);
            }
        }
        divisionId = GetId.getDivisionId(customer.getDivision());
        DivisionComboBox();

        for(String d : UpdateCustomerDivisionsBox.getItems()){
            if(GetName.getDivisionName(divisionId).equals(d)){
                UpdateCustomerDivisionsBox.setValue(d);
            }
        }
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
        division = UpdateCustomerDivisionsBox.getValue();
        divisionId = GetId.getDivisionId(division);
    }
    /** This method updates the entered information to the customer table
     * @param actionEvent the save button is clicked
     */
    public void OnUpdateCustomerSaveClick(ActionEvent actionEvent) throws SQLException, IOException {
        customerid = customer.getCustomerid();
        name = UpdateCustomerNameField.getText();
        address = UpdateCustomerAddressField.getText();
        postalCode = UpdateCustomerPostalField.getText();
        phoneNumber = UpdateCustomerPhoneField.getText();

        int rowsAffected = CustomerQuery.updateCustomer(customerid, name, address, postalCode, phoneNumber, divisionId);
        System.out.println(rowsAffected);

        //reload main menu
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
    public void OnCancelUpdateCustomerClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Mainscreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addProductsMenu = new Scene(root, 1640, 900);
        stage.setTitle("Add Product Menu");
        stage.setScene(addProductsMenu);
        stage.show();
    }
}
