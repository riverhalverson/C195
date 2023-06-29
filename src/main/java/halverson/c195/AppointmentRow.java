package halverson.c195;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

/** This is the class for the appointment object in all of the tables */
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

    /** This method creates a new appointment object
     * @param appointmentid the appointment id
     * @param title the title of the appointment
     * @param description the description of the appointment
     * @param location the location of the appointment
     * @param contact the contact linked to the appointment
     * @param type the type of appointment
     * @param start the start date/time of the appointment
     * @param end the end date/time of the appointment
     * @param customerid the id of the customer linked to the appointment
     * @param userid the id of the user linked to the appointment
     */
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

    /** This method is the appointment id property
     * @return the appointment id property
     */
    public IntegerProperty appointmentidProperty(){
        return appointmentid;
    }
    public final int getappointmentid(){
        return appointmentidProperty().get();
    }
    /** This method is the appointment title property
     * @return the appointment title property
     */
    public StringProperty titleProperty(){
        return title;
    }
    public final String gettitle(){
        return titleProperty().get();
    }
    /** This method is the appointment description property
     * @return the appointment description property
     */
    public StringProperty descriptionProperty(){
        return description;
    }
    public final String getdescription(){
        return descriptionProperty().get();
    }
    /** This method is the appointment location property
     * @return the appointment location property
     */
    public StringProperty locationProperty(){
        return location;
    }
    public final String getlocation(){
        return locationProperty().get();
    }
    /** This method is the appointment contact property
     * @return the appointment contact property
     */
    public StringProperty contactProperty(){
        return contact;
    }
    public final String getcontact(){
        return contactProperty().get();
    }
    /** This method is the appointment type property
     * @return the appointment type property
     */
    public StringProperty typeProperty(){
        return type;
    }
    public final String gettype(){
        return typeProperty().get();
    }
    /** This method is the appointment start date/time property
     * @return the appointment start date/time
     */
    public LocalDateTime getStart(){
        return start;
    }
    /** This method is the appointment end date/time property
     * @return the appointment end date/time
     */
    public LocalDateTime getEnd(){
        return end;
    }
    /** This method is the appointment customer id property
     * @return the appointment customer id property
     */
    public IntegerProperty customeridProperty(){
        return customerid;
    }
    public final int getcustomerid(){
        return customeridProperty().get();
    }
    /** This method is the appointment user id property
     * @return the appointment user id property
     */
    public IntegerProperty useridProperty(){
        return userid;
    }
    public final int getuserid(){
        return useridProperty().get();
    }
}
