package halverson.c195;

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
    private StringProperty createDate;
    private StringProperty createdBy;
    private StringProperty lastUpdate;
    private StringProperty lastUpdatedBy;
    private StringProperty division;

    /** This method creates a new customer object
     * @param customerName the name of the customer
     * @param customerid the id of the customer
     * @param address the address of the customer
     * @param postalCode the postal code of the customer
     * @param phoneNumber the phone number of the customer
     * @param createdBy who created the customer record
     * @param createDate the created date of the customer record
     * @param lastUpdate the last updated date of the customer record
     * @param lastUpdatedBy who last updated the customer record
     * @param division the division id of the customer
     */
    public CustomerRow(int customerid, String customerName,
                       String address, String postalCode,
                       String phoneNumber, String createDate,
                       String createdBy, String lastUpdate,
                       String lastUpdatedBy, String division){

        this.customerid = new SimpleIntegerProperty(customerid);
        this.customerName = new SimpleStringProperty(customerName);
        this.address = new SimpleStringProperty(address);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.createDate = new SimpleStringProperty(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
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
    public StringProperty createDateProperty(){
        return createDate;
    }
    public final String getCreateDate(){
        return createDateProperty().get();
    }
    /** This method is the customer record created by property
     * @return the customer record created by property
     */
    public StringProperty createdByProperty(){
        return createdBy;
    }
    public final String getCreatedBy(){
        return createdByProperty().get();
    }
    /** This method is the customer record updated date property
     * @return the customer record updated date property
     */
    public StringProperty lastUpdateProperty(){
        return lastUpdate;
    }
    public final String getLastUpdate(){
        return lastUpdateProperty().get();
    }
    /** This method is the customer record updated by property
     * @return the customer record updated by property
     */
    public StringProperty lastUpdatedByProperty(){
        return lastUpdatedBy;
    }
    public final String getLastUpdatedBy(){
        return lastUpdatedByProperty().get();
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
