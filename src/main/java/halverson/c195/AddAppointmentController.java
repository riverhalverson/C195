package halverson.c195;

import halverson.c195.helper.DisplayAlert;
import halverson.c195.helper.GetId;
import halverson.c195.helper.JDBC;
import halverson.c195.helper.TZConvert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
/** This class is the controller for the add appointment window */
public class AddAppointmentController implements Initializable {
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

    private LocalTime startTime;
    private LocalTime endTime;

    /** This method populates the time selection boxes */
    public void createTimes(){
        LocalTime time = LocalTime.of(0,0);

        //populates time selection boxes
        for(int i = 0; i <= 46; i++){
            time = time.plusMinutes(30);

            StartTimeComboBox.getItems().add(time);
            EndTimeComboBox.getItems().add(time);
        }
    }
    /** This method saves the entered information into the database, after doing invalid information checks
     * @param actionEvent button pressed
     */
    public void OnSaveClick(ActionEvent actionEvent) throws SQLException, IOException {

        String title = TitleField.getText();
        String description = DescriptionField.getText();
        String location = LocationField.getText();
        String type = String.valueOf(TypeComboBox.getValue());
        int customerId = (int) CustomerIdBox.getValue();
        int contactId = GetId.getContactId((String) ContactComboBox.getValue());
        int userId = (int) UserIdBox.getValue();

        //checks for empty input
        if(title.isEmpty()){
            DisplayAlert.emptyAlert("Title");
            throw new RuntimeException("Title field cannot be blank");
        }
        if(description.isEmpty()){
            DisplayAlert.emptyAlert("Description");
            throw new RuntimeException("Description field cannot be blank");
        }
        if(location.isEmpty()){
            DisplayAlert.emptyAlert("Location");
            throw new RuntimeException("Location field cannot be blank");
        }
        if(type.isEmpty()){
            DisplayAlert.emptyAlert("Type");
            throw new RuntimeException("Type field cannot be blank");
        }

        LocalDate datePicked = LocalDate.from(StartDatePicker.getValue().atStartOfDay());
        LocalDateTime start = LocalDateTime.of(datePicked, startTime);
        LocalDateTime end = LocalDateTime.of(datePicked, endTime);

        boolean overlap = checkOverlap(start, end, customerId);
        boolean duringHours = duringWorkingHours(start, end);

        //checks if start time and after end time and vise versa
        if(end.isBefore(start) || start.isAfter(end)){
            DisplayAlert.customError("Start time is after end time");

        }
        else if (overlap) {
                DisplayAlert.customError("Overlapping Appointment Times");
            } else if (!duringHours){
            DisplayAlert.customError("Appointment out of Business Hours (8:00 a.m. to 10:00 p.m. EST");
        }
        else {
            //convert to utc
                ZonedDateTime startUtc = TZConvert.UserToUTC(start);
                ZonedDateTime endUtc = TZConvert.UserToUTC(end);


                Timestamp appointmentStart = Timestamp.valueOf(startUtc.toLocalDateTime());
                Timestamp appointmentEnd = Timestamp.valueOf(endUtc.toLocalDateTime());

                int rowsAffected = AppointmentsQuery.insertAppointment(title, description, location, type,
                        appointmentStart, appointmentEnd, customerId, contactId, userId);

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

    /** This method populates the type selection box */
    private void typeComboBox(){
        String planType = "Planning Session";
        String briefType = "De-Briefing";
        String consultType = "Consultation";

        TypeComboBox.getItems().add(planType);
        TypeComboBox.getItems().add(briefType);
        TypeComboBox.getItems().add(consultType);
    }

    /** This method runs a query with a given sql statement
     * @param sql the query line to be run
     * @return rs the resultset from the query
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox();
        userComboBox();
        customerComboBox();
        createTimes();
        typeComboBox();
    }

    public void DatePicked(ActionEvent actionEvent) {
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

    /** This method checks for apt time overlaps
     * @param start the start apt time
     * @param end the end apt time
     * @param customerId the customer id to check overlaps for
     * @return true or false if there is an overlap
     */
    public boolean checkOverlap(LocalDateTime start, LocalDateTime end, int customerId) throws SQLException {
        boolean overlap = false;

        String sql = "SELECT * FROM APPOINTMENTS WHERE Customer_ID = ?";

        try{
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LocalDateTime rStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime rEnd = rs.getTimestamp("End").toLocalDateTime();

                //convert times to local
                rStart = TZConvert.UTCToUser(rStart).toLocalDateTime();
                rEnd = TZConvert.UTCToUser(rEnd).toLocalDateTime();

                if((start.isAfter(rStart) || start.isEqual(rStart)) && (start.isBefore(rEnd)))
                {
                    return true;
                } else if(end.isAfter(rEnd) && (end.isBefore(rEnd) || end.isEqual(rEnd)) )
                {
                    return true;
                } else if((start.isBefore(rStart) || start.isEqual(rStart)) && (end.isAfter(rEnd) || end.isEqual(rEnd)))
                {
                    return true;
                }
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return overlap;
    }

    /** This method checks if an appt is during working hours
     * @param start the start apt time
     * @param end the end apt time
     * @return true or false if outside hours
     */
    public boolean duringWorkingHours(LocalDateTime start, LocalDateTime end){
        boolean withinHours;
        LocalTime openHour = LocalTime.of(8,00);
        LocalTime closeHour = LocalTime.of(22, 00);

        //convert to eastern time
        ZonedDateTime aptStartEST = TZConvert.UserToEST(LocalDateTime.from(start));
        LocalTime aptStart = aptStartEST.toLocalTime();

        ZonedDateTime aptEndEST = TZConvert.UserToEST((LocalDateTime.from(end)));
        LocalTime aptEnd = aptEndEST.toLocalTime();

        if(aptStart.isBefore(openHour) || aptEnd.isAfter(closeHour)){
            withinHours = false;
        }
        else{
            withinHours = true;
        }

        return withinHours;
    }
}
