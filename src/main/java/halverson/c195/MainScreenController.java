package halverson.c195;

import halverson.c195.helper.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/** This class is the controller for the main screen of the application */
public class MainScreenController implements Initializable {
    public RadioButton CustomersOption;
    public RadioButton WeeklyOption;
    public RadioButton MonthlyOption;
    public RadioButton AllOption;
    public Button AddButton;
    public Button DeleteButton;
    public Button UpdateButton;
    public TableColumn Col1;
    public TableColumn Col2;
    public TableColumn Col3;
    public TableColumn Col4;
    public TableColumn Col5;
    public TableColumn Col6;
    public TableColumn Col7;
    public TableColumn Col8;
    public TableColumn Col9;
    public TableColumn Col10;
    public TableView tableView;
    private static boolean customerSelected = false;

    private static CustomerRow customer = null;
    private static AppointmentRow appointment = null;

    /** This method exits the user out of the application
     * @param actionEvent the exit button is clicked
     */
    public void OnExitClick(ActionEvent actionEvent) {
        System.out.println("Exited");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /** This method takes the user to the reports page
     * @param actionEvent the report button is clicked
     */
    public void OnReportsClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addProductsMenu = new Scene(root, 930, 700);
        stage.setTitle("Add Customer Menu");
        stage.setScene(addProductsMenu);
        stage.show();
    }
    /** This method changes the main table view if view by customer is selected
     * @param actionEvent the customer button is clicked
     */
    public void OnCustomerOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Customer");
        UpdateButton.setText("Update Customer");
        DeleteButton.setText("Delete Customer");
        customerSelected = true;


        CustomerTable.setupCustomerTable(Col1,Col2,Col3,Col4,Col5,Col6,Col7,Col8,Col9,Col10);
        populateCustomerTable();
    }
    /** This method changes the main table view if view by weekly apts is selected
     * @param actionEvent the weekly radio button is clicked
     */
    public void OnWeeklyOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Appointment");
        UpdateButton.setText("Update Appointment");
        DeleteButton.setText("Delete Appointment");
        customerSelected = false;

        AppointmentsTable.setupAppointmentsTable(Col1,Col2,Col3,Col4,Col5,Col6,Col7,Col8,Col9,Col10);
        populateAppointmentTableWeek();
    }
    /** This method changes the main table view if view by monthly apts is selected
     * @param actionEvent the monthly radio button is clicked
     */
    public void OnMonthlyOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Appointment");
        UpdateButton.setText("Update Appointment");
        DeleteButton.setText("Delete Appointment");
        customerSelected = false;

        AppointmentsTable.setupAppointmentsTable(Col1,Col2,Col3,Col4,Col5,Col6,Col7,Col8,Col9,Col10);
        populateAppointmentTableMonth();
    }
    /** This method changes the main table view if view all apts is selected
     * @param actionEvent the all apts radio button is clicked
     */
    public void OnAllOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Appointment");
        UpdateButton.setText("Update Appointment");
        DeleteButton.setText("Delete Appointment");
        customerSelected = false;

        AppointmentsTable.setupAppointmentsTable(Col1,Col2,Col3,Col4,Col5,Col6,Col7,Col8,Col9,Col10);
        populateAppointmentTable();
    }
    /** This method takes the user to the add customer or add appointment window
     * @param actionEvent the add button is clicked
     */
    public void OnAddClick(ActionEvent actionEvent) throws IOException {
        if(customerSelected) {
            Parent root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addProductsMenu = new Scene(root, 1140, 900);
            stage.setTitle("Add Customer Menu");
            stage.setScene(addProductsMenu);
            stage.show();
        }
        else{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AddAppt.fxml"));
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
    }
    /** This method takes the users to the delete customer or delete appointment window
     * @param actionEvent the delete button is clicked
     */
    public void OnDeleteClick(ActionEvent actionEvent) throws SQLException {
        if (customerSelected) {
            customer = (CustomerRow) tableView.getSelectionModel().getSelectedItem();
            String name = customer.getCustomerName();
            int rowsAffected = 0;

            //checks if no customer was selected, displays error
            if (customer == null) {
                DisplayAlert.customError("No customer selected");
            } else {
                Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove Customer: "
                + customer.getCustomerName());

                Optional<ButtonType> choiceResult = confirmDelete.showAndWait();

                //if ok is selected, remove part
                if (choiceResult.isPresent() && choiceResult.get() == ButtonType.OK) {
                    int customerid = customer.getCustomerid();
                    String customerName = customer.getCustomerName();

                    rowsAffected = CustomerQuery.deleteCustomer(customerid);
                }

                //checks if delete was successful
                if(rowsAffected == 0){
                    DisplayAlert.customError(name + " was not deleted successfully, there are associated appointments");
                }
                else{
                    DisplayAlert.customError(name + " was deleted successfully");

                    //reloads customer table to show updates
                    populateCustomerTable();
                }
            }
        }
        else{
            appointment = (AppointmentRow) tableView.getSelectionModel().getSelectedItem();
            int rowsAffected = 0;

            //checks if no appointment selected
            if(appointment == null){
                DisplayAlert.customError("No appointment selected");
            }
            else{
                Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure you want to remove this Appointment?");

                Optional<ButtonType> choiceResult = confirmDelete.showAndWait();

                //if ok is selected, remove part
                if (choiceResult.isPresent() && choiceResult.get() == ButtonType.OK) {
                    int appointmentid = appointment.getappointmentid();

                    rowsAffected = AppointmentsQuery.deleteAppointment(appointmentid);
                }
                if(rowsAffected == 0){
                    DisplayAlert.customError("Appointment was not deleted successfully");
                }
                else{
                    DisplayAlert.customError("Appointment was deleted successfully");

                    //reloads customer table to show updates
                    populateAppointmentTable();
                }
            }
        }
    }
    /** This method takes the user to the update customer or update appointment window
     * @param actionEvent the update button is clicked
     */
    public void OnUpdateClick(ActionEvent actionEvent) throws IOException {
        if(customerSelected) {
            customer = (CustomerRow) tableView.getSelectionModel().getSelectedItem();

            if (customer == null) {
                Alert noSelectionError = new Alert(Alert.AlertType.ERROR, "No customer selected");

                Optional<ButtonType> result = noSelectionError.showAndWait();
            } else {

                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/halverson/C195/UpdateCustomer.fxml"));
                    loader.load();

                    UpdateCustomerController ModifyController = loader.getController();
                    ModifyController.CustomerToModify(customer);

                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Parent scene = loader.getRoot();
                    stage.setTitle("Update Customer");
                    stage.setScene(new Scene(scene));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            appointment = (AppointmentRow) tableView.getSelectionModel().getSelectedItem();

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("UpdateAppt.fxml"));
                loader.load();

                UpdateAppointmentController ModifyController = loader.getController();
                ModifyController.AppointmentToModify(appointment);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setTitle("Update Appointment");
                stage.setScene(new Scene(scene));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /** This method populates the customer table from the database */
    public void populateCustomerTable(){

        tableView.getItems().clear();

        ObservableList<CustomerRow> customerList = FXCollections.observableArrayList();

        ResultSet rs = null;

        Statement stmt;
        String sql = "SELECT * FROM CUSTOMERS";

        try{
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int customerid = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phoneNumber = rs.getString("Phone");
                String createDate = rs.getString("Create_Date");
                String createdBy = rs.getString("Created_By");
                String lastUpdate = rs.getString("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                int divisionId = rs.getInt("Division_ID");
                String division = GetName.getDivisionName(divisionId);

                CustomerRow cr = new CustomerRow( customerid,
                        customerName, address,
                        postalCode, phoneNumber,
                        createDate, createdBy,
                        lastUpdate, lastUpdatedBy,
                        division);
                customerList.add(cr);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        tableView.setItems(customerList);

        Col1.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        Col2.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        Col3.setCellValueFactory(new PropertyValueFactory<>("address"));
        Col4.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        Col5.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        Col6.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        Col7.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        Col8.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        Col9.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        Col10.setCellValueFactory(new PropertyValueFactory<>("division"));
    }
    /** This method populates the appointment table from the database */
    public void populateAppointmentTable(){

        tableView.getItems().clear();

        ObservableList<AppointmentRow> apptList = FXCollections.observableArrayList();

        ResultSet rs = null;

        Statement stmt;
        String sql = "SELECT * FROM APPOINTMENTS";

        try{
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int appointmentid = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactid = rs.getInt("Contact_ID");
                String contact = GetName.getContactName(contactid);
                String type = rs.getString("Type");
                LocalDateTime startUtc = rs.getTimestamp("Start").toLocalDateTime();

                LocalDateTime endUtc = rs.getTimestamp("End").toLocalDateTime();

                //convert times to user times
                LocalDateTime start = TZConvert.UTCToUser(startUtc).toLocalDateTime();

                LocalDateTime end = TZConvert.UTCToUser(endUtc).toLocalDateTime();

                int customerid = rs.getInt("Customer_ID");
                int userid = rs.getInt("User_ID");

                AppointmentRow ar = new AppointmentRow( appointmentid, title,
                        description, location, contact, type, start,
                        end, customerid, userid);

                apptList.add(ar);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        tableView.setItems(apptList);

        Col1.setCellValueFactory(new PropertyValueFactory<>("appointmentid"));
        Col2.setCellValueFactory(new PropertyValueFactory<>("title"));
        Col3.setCellValueFactory(new PropertyValueFactory<>("description"));
        Col4.setCellValueFactory(new PropertyValueFactory<>("location"));
        Col5.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Col6.setCellValueFactory(new PropertyValueFactory<>("type"));
        Col7.setCellValueFactory(new PropertyValueFactory<>("start"));
        Col8.setCellValueFactory(new PropertyValueFactory<>("end"));
        Col9.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        Col10.setCellValueFactory(new PropertyValueFactory<>("userid"));
    }
    /** This method populates the appointment table from the database and filters by week
     * LAMBDA this is one of the lambda expressions I used, it simplifies the code by being
     * able to reuse a single function to populate the table with a database query, and has
     * the additional functionality to filter by the criteria given, in this case by weekly apts*/
    public void populateAppointmentTableWeek(){

        try {
            LocalDate today = LocalDate.now();
            int weekOfYear = today.get(WeekFields.of(Locale.getDefault()).weekOfYear());

            ObservableList<AppointmentRow> list = AppointmentsQuery.get().stream()
                    .filter(aptRow -> aptRow.getStart().get(WeekFields.of(Locale.getDefault()).weekOfYear()) == weekOfYear)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            tableView.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Col1.setCellValueFactory(new PropertyValueFactory<>("appointmentid"));
        Col2.setCellValueFactory(new PropertyValueFactory<>("title"));
        Col3.setCellValueFactory(new PropertyValueFactory<>("description"));
        Col4.setCellValueFactory(new PropertyValueFactory<>("location"));
        Col5.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Col6.setCellValueFactory(new PropertyValueFactory<>("type"));
        Col7.setCellValueFactory(new PropertyValueFactory<>("start"));
        Col8.setCellValueFactory(new PropertyValueFactory<>("end"));
        Col9.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        Col10.setCellValueFactory(new PropertyValueFactory<>("userid"));
    }
    /** This method populates the appointment table from the database and filters it by the month the appt is in*/
    public void populateAppointmentTableMonth(){
        //reset table view
        tableView.getItems().clear();
        ObservableList<AppointmentRow> apptList = FXCollections.observableArrayList();


        ResultSet rs = null;
        Statement stmt;
        String sql = "SELECT * FROM APPOINTMENTS WHERE MONTH(Start)=MONTH(now())";

        try{
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int appointmentid = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactid = rs.getInt("Contact_ID");
                String contact = GetName.getContactName(contactid);
                String type = rs.getString("Type");
                LocalDateTime startUtc = rs.getTimestamp("Start").toLocalDateTime();

                LocalDateTime endUtc = rs.getTimestamp("End").toLocalDateTime();

                //convert times to user times
                LocalDateTime start = TZConvert.UTCToUser(startUtc).toLocalDateTime();

                LocalDateTime end = TZConvert.UTCToUser(endUtc).toLocalDateTime();

                int customerid = rs.getInt("Customer_ID");
                int userid = rs.getInt("User_ID");

                AppointmentRow ar = new AppointmentRow( appointmentid, title,
                        description, location, contact, type, start,
                        end, customerid, userid);

                apptList.add(ar);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        tableView.setItems(apptList);

        Col1.setCellValueFactory(new PropertyValueFactory<>("appointmentid"));
        Col2.setCellValueFactory(new PropertyValueFactory<>("title"));
        Col3.setCellValueFactory(new PropertyValueFactory<>("description"));
        Col4.setCellValueFactory(new PropertyValueFactory<>("location"));
        Col5.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Col6.setCellValueFactory(new PropertyValueFactory<>("type"));
        Col7.setCellValueFactory(new PropertyValueFactory<>("start"));
        Col8.setCellValueFactory(new PropertyValueFactory<>("end"));
        Col9.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        Col10.setCellValueFactory(new PropertyValueFactory<>("userid"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateAppointmentTable();

    }
}
