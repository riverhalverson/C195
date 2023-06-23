package halverson.c195;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateAppointmentController implements Initializable {
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



    private AppointmentRow appointment = null;

    String title;
    String description;
    String location;
    String type;
    int customerId;
    int contactId;
    int userId;


    public void createTimes(){
        LocalTime time = LocalTime.of(0,0);

        //populates time selection boxes
        for(int i = 0; i <= 46; i++){
            time = time.plusMinutes(30);

            StartTimeComboBox.getItems().add(time);
            EndTimeComboBox.getItems().add(time);
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
    public void DatePicked(ActionEvent actionEvent) {
    }

    public void OnSaveClick(ActionEvent actionEvent) {
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

    public void OnStartTimeSelect(ActionEvent actionEvent) {
        startTime = (LocalTime) StartTimeComboBox.getValue();
    }

    public void OnEndTimeSelect(ActionEvent actionEvent) {
        endTime = (LocalTime) EndTimeComboBox.getValue();
    }
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
    public void AppointmentToModify(AppointmentRow aptToModify) throws SQLException {
        appointment = aptToModify;

        AppointmentIdField.setPromptText(String.valueOf((appointment.getappointmentid())));
        TitleField.setText(String.valueOf((appointment.gettitle())));
        DescriptionField.setText(String.valueOf((appointment.getdescription())));
        LocationField.setText(String.valueOf((appointment.getlocation())));
        TypeField.setText(String.valueOf((appointment.gettype())));
        CustomerIdField.setText(String.valueOf((appointment.getcustomerid())));
        UserIdField.setText(String.valueOf((appointment.getuserid())));


        //sets values to existing, incase nothing is changed
        title = String.valueOf((appointment.gettitle()));
        description = String.valueOf((appointment.getdescription()));
        location = String.valueOf((appointment.getlocation()));
        type = String.valueOf((appointment.gettype()));
        customerId = appointment.getcustomerid();
        contactId = GetId.getContactId(appointment.getcontact());
        userId = appointment.getuserid();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox();
        createTimes();
    }
}
