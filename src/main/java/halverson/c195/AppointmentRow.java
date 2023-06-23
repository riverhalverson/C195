package halverson.c195;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class AppointmentRow {
    private IntegerProperty appointmentid;
    private StringProperty title;
    private StringProperty description;
    private StringProperty location;
    private StringProperty contact;
    private StringProperty type;
    private LocalDateTime start;
    private LocalDateTime end;
    private IntegerProperty customerid;
    private IntegerProperty userid;

    public AppointmentRow(int appointmentid, String title, String description, String location,
                          String contact, String type, LocalDateTime start, LocalDateTime end,
                          int customerid, int userid){

        this.appointmentid = new SimpleIntegerProperty(appointmentid);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.contact = new SimpleStringProperty(contact);
        this.type = new SimpleStringProperty(type);
        this.start = start;
        this.end = end;
        this.customerid = new SimpleIntegerProperty(customerid);
        this.userid = new SimpleIntegerProperty(userid);
    }

    public IntegerProperty appointmentidProperty(){
        return appointmentid;
    }
    public final int getappointmentid(){
        return appointmentidProperty().get();
    }
    public final void setappointmentid(int appointmentid){
        appointmentidProperty().set(appointmentid);
    }
    public StringProperty titleProperty(){
        return title;
    }
    public final String gettitle(){
        return titleProperty().get();
    }
    public final void settitle(String title){
        titleProperty().set(title);
    }
    public StringProperty descriptionProperty(){
        return description;
    }
    public final String getdescription(){
        return descriptionProperty().get();
    }
    public final void setdescription(String description){
        descriptionProperty().set(description);
    }
    public StringProperty locationProperty(){
        return location;
    }
    public final String getlocation(){
        return locationProperty().get();
    }
    public final void setlocation(String location){
        locationProperty().set(location);
    }
    public StringProperty contactProperty(){
        return contact;
    }
    public final String getcontact(){
        return contactProperty().get();
    }
    public final void setcontact(String contact){
        contactProperty().set(contact);
    }
    public StringProperty typeProperty(){
        return type;
    }
    public final String gettype(){
        return typeProperty().get();
    }
    public final void settype(String type){
        typeProperty().set(type);
    }
    public LocalDateTime getStart(){
        return start;
    }
    public LocalDateTime getEnd(){
        return end;
    }
    public IntegerProperty customeridProperty(){
        return customerid;
    }
    public final int getcustomerid(){
        return customeridProperty().get();
    }
    public final void setcustomerid(int customerid){
        customeridProperty().set(customerid);
    }
    public IntegerProperty useridProperty(){
        return userid;
    }
    public final int getuserid(){
        return useridProperty().get();
    }
    public final void setuserid(int userid){
        useridProperty().set(userid);
    }





}
