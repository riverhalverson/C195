package halverson.c195.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
/** This class is the customer object used to populate the tables within the program */
public class CustomerRow {
    private IntegerProperty customerid;
    private StringProperty customerName;
    private StringProperty address;
    private StringProperty postalCode;
    private StringProperty phoneNumber;
    private StringProperty country;
    private StringProperty division;

    /** This method creates a new customer object
     * @param customerName the name of the customer
     * @param customerid the id of the customer
     * @param address the address of the customer
     * @param postalCode the postal code of the customer
     * @param phoneNumber the phone number of the customer
     * @param country who last updated the customer record
     * @param division the division id of the customer
     */
    public CustomerRow(int customerid, String customerName,
                       String address, String postalCode,
                       String phoneNumber,
                       String country, String division){

        this.customerid = new SimpleIntegerProperty(customerid);
        this.customerName = new SimpleStringProperty(customerName);
        this.address = new SimpleStringProperty(address);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.country = new SimpleStringProperty(country);
        this.division = new SimpleStringProperty(division);
    }

    /** This method is the customer id property
     * @return the customer id property
     */
    public IntegerProperty customerIdProperty(){
        return customerid;
    }
    public final int getCustomerid(){
        return customerIdProperty().get();
    }
    /** This method is the customer name property
     * @return the customer name property
     */
    public StringProperty customerNameProperty(){
        return customerName;
    }
    public final String getCustomerName(){
        return customerNameProperty().get();
    }
    /** This method is the customer address property
     * @return the customer address property
     */
    public StringProperty addressProperty(){
        return address;
    }
    public final String getAddress(){
        return addressProperty().get();
    }
    /** This method is the customer postal code property
     * @return the customer postal code property
     */
    public StringProperty postalCodeProperty(){
        return postalCode;
    }
    public final String getPostalCode(){
        return postalCodeProperty().get();
    }
    /** This method is the customer phone number property
     * @return the customer phone number property
     */
    public StringProperty phoneNumberProperty(){
        return phoneNumber;
    }
    public final String getPhoneNumber(){
        return phoneNumberProperty().get();
    }
    /** This method is the customer record create date property
     * @return the customer record create date property
     */
    public StringProperty countryProperty(){
        return country;
    }
    public final String getLastUpdatedBy(){
        return countryProperty().get();
    }
    /** This method is the customer division property
     * @return the customer division property
     */
    public StringProperty divisionProperty(){
        return division;
    }
    public final String getDivision(){
        return divisionProperty().get();
    }

}
