package halverson.c195;

import halverson.c195.DAO.AppointmentsQuery;
import halverson.c195.entities.AppointmentRow;
import halverson.c195.helper.*;
import halverson.c195.helper.ApptChecks;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/** This class is the controller for the update appointment page */
public class UpdateAppointmentController implements Initializable {
    public TextField AppointmentIdField;
    public TextField TitleField;
    public TextField DescriptionField;
    public TextField LocationField;
    public ComboBox ContactComboBox;
    public DatePicker StartDatePicker;
    public ComboBox StartTimeComboBox;
    public ComboBox EndTimeComboBox;
    public ComboBox CustomerIdBox;
    public ComboBox UserIdBox;
    public ComboBox TypeComboBox;
    public DatePicker EndDatePicker;

    private LocalTime startTime;
    private LocalTime endTime;


    private AppointmentRow appointment = null;

    int aptId;
    String title;
    String description;
    String location;
    String type;
    int customerId;
    int contactId;
    int userId;

    /** This method populates the time boxes */
    public void createTimes(){
        LocalTime time = LocalTime.of(0,0);

        //populates time selection boxes
        for(int i = 0; i <= 46; i++){
            time = time.plusMinutes(30);

            StartTimeComboBox.getItems().add(time);
            EndTimeComboBox.getItems().add(time);
        }
    }
    /** This method populates the contact name selection box */
    private void contactComboBox(){
        ResultSet rs = runQuery("SELECT Contact_Name FROM CONTACTS");
        try{
            while(rs.next()){
                ContactComboBox.getItems().add(rs.getString(1));
            }
        }   catch (SQLException ex){
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** This method populates the customer selection box */
    private void customerComboBox(){
        ResultSet rs = runQuery("SELECT Customer_ID FROM CUSTOMERS");
        try{
            while(rs.next()){
                CustomerIdBox.getItems().add(rs.getInt(1));
            }
        }   catch (SQLException ex){
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** This method populates the user name selection box */
    private void userComboBox(){
        ResultSet rs = runQuery("SELECT USER_ID FROM USERS");
        try{
            while(rs.next()){
                UserIdBox.getItems().add(rs.getInt(1));
            }
        }   catch (SQLException ex){
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** This method populates the type selection box */
    private void typeComboBox(){
        String stratType = "Business Strategy Session";
        String modelType = "New Model Informational";
        String consultType = "Consultation";

        TypeComboBox.getItems().add(stratType);
        TypeComboBox.getItems().add(modelType);
        TypeComboBox.getItems().add(consultType);
    }
    public void DatePicked(ActionEvent actionEvent) {
    }
    /** This method saves the entered information into the database, after doing invalid information checks
     * @param actionEvent button pressed
     */
    public void OnSaveClick(ActionEvent actionEvent) throws IOException, SQLException {
        String title = TitleField.getText();
        String description = DescriptionField.getText();
        String location = LocationField.getText();
        String type = String.valueOf(TypeComboBox.getValue());
        int customerId = (int) CustomerIdBox.getValue();
        int contactId = GetId.getContactId((String) ContactComboBox.getValue());
        int userId = (int) UserIdBox.getValue();

        LocalDate startDatePicked = LocalDate.from(StartDatePicker.getValue().atStartOfDay());
        LocalDateTime start = LocalDateTime.of(startDatePicked, startTime);

        LocalDate endDatePicked = LocalDate.from(EndDatePicker.getValue().atStartOfDay());
        LocalDateTime end = LocalDateTime.of(endDatePicked, endTime);

        boolean overlap = ApptChecks.checkUpdateAptOverlap(start, end, customerId, aptId);
        boolean duringHours = ApptChecks.duringWorkingHours(start, end);

        //checks if start time and after end time and vise versa
        if(end.isBefore(start) || start.isAfter(end)){
            DisplayAlert.customError("Start time is after end time");

        }
        else if (overlap) {
            DisplayAlert.customError("Overlapping Appointment Times");
        } else if (!duringHours){
            DisplayAlert.customError("Appointment out of Business Hours (8:00 a.m. to 5:00 p.m. EST");
        } else {
            ZonedDateTime startUtc = TZConvert.UserToUTC(start);
            ZonedDateTime endUtc = TZConvert.UserToUTC(end);


            Timestamp appointmentStart = Timestamp.valueOf(startUtc.toLocalDateTime());
            Timestamp appointmentEnd = Timestamp.valueOf(endUtc.toLocalDateTime());

            int rowsAffected = AppointmentsQuery.updateAppointment(appointment.getappointmentid(), title,
                    description, location, type, appointmentStart, appointmentEnd, customerId, contactId, userId);

            //display main menu
            Parent root = FXMLLoader.load(getClass().getResource("Mainscreen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addProductsMenu = new Scene(root, 1640, 900);
            stage.setTitle("Add Product Menu");
            stage.setScene(addProductsMenu);
            stage.show();
        }
    }
    /** This method returns the user to the main menu when exit is clicked
     * @param actionEvent button pressed
     */
    public void OnCancelClick(ActionEvent actionEvent) {
        createTimes();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/halverson/C195/Mainscreen.fxml"));
            loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle("Add Appointment");
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** This method gets the selected start time
     * @param actionEvent the combobox is selected
     */
    public void OnStartTimeSelect(ActionEvent actionEvent) {
        startTime = (LocalTime) StartTimeComboBox.getValue();
    }
    /** This method gets the selected start time
     * @param actionEvent the combobox is selected
     */
    public void OnEndTimeSelect(ActionEvent actionEvent) {
        endTime = (LocalTime) EndTimeComboBox.getValue();
    }
    /** This method runs a query with a given sql statement
     * @param sql the query line to be run
     * @return rs the resultset from the query
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
    /** This method brings in the main menus selected appointment to modify and preloads the fields/combo boxes
     * @param aptToModify the appointment to modify
     */
    public void AppointmentToModify(AppointmentRow aptToModify) throws SQLException {
        appointment = aptToModify;

        aptId = appointment.getappointmentid();
        AppointmentIdField.setPromptText(String.valueOf((appointment.getappointmentid())));
        TitleField.setText(String.valueOf((appointment.gettitle())));
        DescriptionField.setText(String.valueOf((appointment.getdescription())));
        LocationField.setText(String.valueOf((appointment.getlocation())));
        TypeComboBox.setValue(appointment.gettype());
        CustomerIdBox.setValue(appointment.getcustomerid());
        UserIdBox.setValue(appointment.getuserid());
        ContactComboBox.setValue(appointment.getcontact());

        LocalDate startDate = appointment.getStart().toLocalDate();
        StartDatePicker.setValue(startDate);

        LocalDate endDate = appointment.getEnd().toLocalDate();
        EndDatePicker.setValue(endDate);

        StartTimeComboBox.setValue(appointment.getStart().toLocalTime());
        EndTimeComboBox.setValue(appointment.getEnd().toLocalTime());

        //sets values to existing, incase nothing is changed
        title = String.valueOf((appointment.gettitle()));
        description = String.valueOf((appointment.getdescription()));
        location = String.valueOf((appointment.getlocation()));
        type = String.valueOf((appointment.gettype()));
        customerId = appointment.getcustomerid();
        contactId = GetId.getContactId(appointment.getcontact());
        userId = appointment.getuserid();
        startTime = (LocalTime) StartTimeComboBox.getValue();
        endTime = (LocalTime) EndTimeComboBox.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox();
        customerComboBox();
        userComboBox();
        createTimes();
        typeComboBox();
    }

    public void EndDatePicked(ActionEvent actionEvent) {
    }
}
