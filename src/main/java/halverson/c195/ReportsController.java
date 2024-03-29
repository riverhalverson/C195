package halverson.c195;

import halverson.c195.DAO.AppointmentsQuery;
import halverson.c195.entities.AppointmentRow;
import halverson.c195.entities.CountrySumRow;
import halverson.c195.entities.MonthlyApptRow;
import halverson.c195.helper.GetId;
import halverson.c195.helper.GetName;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/** This class is the controller for the reports window */
public class ReportsController implements Initializable {
    public TableColumn IDCol;
    public TableColumn TitleCol;
    public TableColumn TypeCol;
    public TableColumn DescriptionCol;
    public TableColumn LocationCol;
    public TableColumn StartCol;
    public TableColumn EndCol;
    public TableColumn CustomerIdCol;
    public ComboBox ContactComboBox;
    public TableColumn MonthAppointmentCol;
    public TableColumn MonthNumberCol;
    public TableColumn CountryCol;
    public TableColumn CountryNumberCol;
    public TableView AppointmentContactsTable;
    public TableView AppointmentMonthTable;
    public TableView AppointmentCountryTable;
    public ComboBox TypeComboBox;

    String contact = "";
    String month;

     /** This method gets the selected contact to filter the tableview by
     * @param actionEvent the contact combo box selection is made
     */
    public void OnSelectContact(ActionEvent actionEvent) throws SQLException {
        contact = (String) ContactComboBox.getValue();
        int contactId = GetId.getContactId(contact);

        populateAppointmentTable(contactId);
    }
    /** This method populates the contact combo box with contacts from the database */
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
     /** This method runs a query on the database with a given statement
     * @param sql the statement to run a query with
     * @return the resultset from the query
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
     /** This method populates the appointment table and filters by contact selected
     * LAMBDA this is one of the lambda expressions I used in this program, it calls the same function used in the
     * main screen tableview but this time filters it by contact. The amount of code written to change the filter
     * criteria is far shorter than if I didn't use a lambda
     * @param contactId the contact to sort the table by
     */
    public void populateAppointmentTable(int contactId) throws SQLException {
        try {
            ObservableList<AppointmentRow> list = AppointmentsQuery.get().stream()
                            .filter(aptRow -> GetId.getContactId( aptRow.getcontact())==contactId)
                                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            AppointmentContactsTable.setItems(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     /** This method populates the sum appointments by month table, and allows to sort by type selected
     * @param type the type to sort by
     */
    public void populateMonthlyAppointmentTable(String type) throws SQLException {
        AppointmentMonthTable.getItems().clear();

        Vector<Month> monthsFound = new Vector<Month>(0);
        Vector<Month> monthOccurances = new Vector<Month>(0);
        ObservableList<MonthlyApptRow> monthlyRows = FXCollections.observableArrayList();

        ResultSet rs = null;

        String sql = "SELECT * FROM APPOINTMENTS WHERE Type = ?";

        try{
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, type);
            rs = ps.executeQuery();

            while(rs.next()){
                LocalDateTime date = rs.getTimestamp("Start").toLocalDateTime();
                Month month = date.getMonth();

                monthOccurances.addElement(month);

                if(!monthsFound.contains(month)){
                    monthsFound.addElement(month);
                }


            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        if(monthOccurances.size() != 0){
            for(int i = 0; i < monthsFound.size(); i++){
                Month monthSeeking = monthsFound.elementAt(i);
                int count = 0;

                for(int j = 0; j < monthOccurances.size(); j++){
                    Month currentMonth = monthOccurances.elementAt(j);

                    if(monthSeeking.equals(currentMonth)){
                        count++;
                    }
                }

                MonthlyApptRow row = new MonthlyApptRow(monthSeeking, count);
                monthlyRows.add(row);
            }

        }

        AppointmentMonthTable.setItems(monthlyRows);

        MonthAppointmentCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        MonthNumberCol.setCellValueFactory(new PropertyValueFactory<>("count"));
    }
     /** This method populates the sum appointments by country table */
    public void populateCountrySumTable() throws SQLException {

        AppointmentCountryTable.getItems().clear();

        Vector<String> countriesFound = new Vector<String>(0);
        Vector<String> countryOccurances = new Vector<String>(0);
        ObservableList<CountrySumRow> countrySum = FXCollections.observableArrayList();

        ResultSet rs = null;

        String sql = "SELECT * FROM CUSTOMERS";
        Statement stmt;


        try{
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery(sql);

            //make list of country occurances in customers
            while(rs.next()){
                String country = String.valueOf(GetName.getCountry(rs.getInt("Division_ID")));

                countryOccurances.addElement(country);

                if(!countriesFound.contains(country)){
                    countriesFound.addElement(country);
                }
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        //sum the amount of customers for each country if customer list is not empty
        if(countryOccurances.size() != 0){
            for(int i = 0; i < countriesFound.size(); i++){
                String countrySeeking = countriesFound.elementAt(i);
                int count = 0;

                for(int j = 0; j < countryOccurances.size(); j++){
                    String currentCountry = countryOccurances.elementAt(j);

                    if(countrySeeking.equals(currentCountry)){
                        count++;
                    }
                }

                CountrySumRow row = new CountrySumRow(countrySeeking, count);
                countrySum.add(row);
            }

        }

        AppointmentCountryTable.setItems(countrySum);

        CountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        CountryNumberCol.setCellValueFactory(new PropertyValueFactory<>("count"));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentid"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        LocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        StartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerid"));

        typeComboBox();
        contactComboBox();
        try {
            populateCountrySumTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     /** This method returns the user to the main screen
     * @param actionEvent the exit button is pressed
     */
    public void OnExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Mainscreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addProductsMenu = new Scene(root, 1640, 900);
        stage.setTitle("Add Product Menu");
        stage.setScene(addProductsMenu);
        stage.show();
    }
     /** This method takes the users selection of type to sort the table by
     * @param actionEvent the combo box selection is made
     */
    public void OnSelectType(ActionEvent actionEvent) throws SQLException {
        month = (String) TypeComboBox.getValue();

        populateMonthlyAppointmentTable(month);
    }
    /** This method populates the type combo box with unique types from the database options */
    private void typeComboBox(){
        String stratType = "Business Strategy Session";
        String modelType = "New Model Informational";
        String consultType = "Consultation";

        TypeComboBox.getItems().add(stratType);
        TypeComboBox.getItems().add(modelType);
        TypeComboBox.getItems().add(consultType);
    }

}
