package halverson.c195;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {
    public TextField UpdateCustomerNameField;
    public TextField UpdateCustomerAddressField;
    public TextField UpdateCustomerPostalField;
    public TextField UpdateCustomerPhoneField;
    public ComboBox UpdateCustomerCountriesBox;
    public ComboBox UpdateCustomerDivisionsBox;
    public TextField CustomerIDField;
    private String division;
    private int divisionId;

    private CustomerRow customer = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void CustomerToModify(CustomerRow customerToModify){
        customer = customerToModify;

        CustomerIDField.setPromptText(String.valueOf((customer.getCustomerid())));
        UpdateCustomerNameField.setText(String.valueOf((customer.getCustomerName())));
        UpdateCustomerAddressField.setText(String.valueOf((customer.getAddress())));
        UpdateCustomerPostalField.setText(String.valueOf((customer.getPostalCode())));
        UpdateCustomerPhoneField.setText(String.valueOf((customer.getPhoneNumber())));


    }
    public void OnCountrySelect(ActionEvent actionEvent) {
    }

    public void OnDivisionSelect(ActionEvent actionEvent) {
    }

    public void OnUpdateCustomerSaveClick(ActionEvent actionEvent) {
    }

    public void OnCancelUpdateCustomerClick(ActionEvent actionEvent) {
    }
}
