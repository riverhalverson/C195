package halverson.c195;

import halverson.c195.helper.GetId;
import halverson.c195.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddAppointmentController implements Initializable {
    public TextField AppointmentIdField;
    public TextField TitleField;
    public TextField DescriptionField;
    public TextField LocationField;
    public TextField TypeField;
    public TextField CustomerIdField;
    public TextField UserIdField;
    public ComboBox ContactComboBox;
    public DatePicker StartDatePicker;
    public ComboBox StartTimeComboBox;
    public ComboBox EndTimeComboBox;

    private LocalTime startTime;
    private LocalTime endTime;




    public void createTimes(){
        LocalTime time = LocalTime.of(0,0);

        //populates time selection boxes
        for(int i = 0; i <= 46; i++){
            time = time.plusMinutes(30);

            StartTimeComboBox.getItems().add(time);
            EndTimeComboBox.getItems().add(time);
        }
    }

    public void OnSaveClick(ActionEvent actionEvent) throws SQLException, IOException {

        String title = TitleField.getText();
        String description = DescriptionField.getText();
        String location = LocationField.getText();
        String type = TypeField.getText();
        int customerId = Integer.parseInt(CustomerIdField.getText());
        int contactId = GetId.getContactId((String) ContactComboBox.getValue());
        int userId = Integer.parseInt(UserIdField.getText());

        LocalDate datePicked = LocalDate.from(StartDatePicker.getValue().atStartOfDay());
        LocalDateTime start = LocalDateTime.of(datePicked, startTime);
        LocalDateTime end = LocalDateTime.of(datePicked, endTime);

        Timestamp appointmentStart = Timestamp.valueOf(start);
        Timestamp appointmentEnd = Timestamp.valueOf(end);

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
        createTimes();

    }

    public void DatePicked(ActionEvent actionEvent) {
    }

    public void OnStartTimeSelect(ActionEvent actionEvent) {
        startTime = (LocalTime) StartTimeComboBox.getValue();
    }

    public void OnEndTimeSelect(ActionEvent actionEvent) {
        endTime = (LocalTime) EndTimeComboBox.getValue();
    }
}
