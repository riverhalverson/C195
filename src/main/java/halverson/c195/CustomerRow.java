package halverson.c195;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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


    public IntegerProperty customerIdProperty(){
        return customerid;
    }
    public final int getCustomerid(){
        return customerIdProperty().get();
    }
    public final void setCustomerid(int customerid){
        customerIdProperty().set(customerid);
    }
    public StringProperty customerNameProperty(){
        return customerName;
    }
    public final String getCustomerName(){
        return customerNameProperty().get();
    }
    public final void setCustomerName(String customerName){
        customerNameProperty().set(customerName);
    }
    public StringProperty addressProperty(){
        return address;
    }
    public final String getAddress(){
        return addressProperty().get();
    }
    public final void setAddress(String address){
        addressProperty().set(address);
    }
    public StringProperty postalCodeProperty(){
        return postalCode;
    }
    public final String getPostalCode(){
        return postalCodeProperty().get();
    }
    public final void setPostalCode(String postalCode){
        postalCodeProperty().set(postalCode);
    }
    public StringProperty phoneNumberProperty(){
        return phoneNumber;
    }
    public final String getPhoneNumber(){
        return phoneNumberProperty().get();
    }
    public final void setPhoneNumber(String phoneNumber){
        phoneNumberProperty().set(phoneNumber);
    }
    public StringProperty createDateProperty(){
        return createDate;
    }
    public final String getCreateDate(){
        return createDateProperty().get();
    }
    public final void setCreateDate(String createDate){
        createDateProperty().set(createDate);
    }
    public StringProperty createdByProperty(){
        return createdBy;
    }
    public final String getCreatedBy(){
        return createdByProperty().get();
    }
    public final void setCreatedBy(String createdBy){
        createdByProperty().set(createdBy);
    }
    public StringProperty lastUpdateProperty(){
        return lastUpdate;
    }
    public final String getLastUpdate(){
        return lastUpdateProperty().get();
    }
    public final void setLastUpdate(String lastUpdate){
        lastUpdateProperty().set(lastUpdate);
    }
    public StringProperty lastUpdatedByProperty(){
        return lastUpdatedBy;
    }
    public final String getLastUpdatedBy(){
        return lastUpdatedByProperty().get();
    }
    public final void setLastUpdatedBy(String lastUpdatedBy){
        lastUpdatedByProperty().set(lastUpdatedBy);
    }
    public StringProperty divisionProperty(){
        return division;
    }
    public final String getDivision(){
        return divisionProperty().get();
    }
    public final void setDivision(String division) {
        divisionProperty().set(division);
    }
}
