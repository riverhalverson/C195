package halverson.c195;

import halverson.c195.helper.GetId;
import halverson.c195.helper.GetName;
import halverson.c195.helper.JDBC;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainScreenController {
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
    public TableView <CustomerRow> tableView;
    private boolean customerSelected = false;

    private static CustomerRow customer = null;

    public void OnExitClick(ActionEvent actionEvent) {
        System.out.println("Exited");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void OnReportsClick(ActionEvent actionEvent) {
    }

    public void OnCustomerOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Customer");
        UpdateButton.setText("Update Customer");
        DeleteButton.setText("Delete Customer");
        customerSelected = true;


        CustomerTable.setupCustomerTable(Col1,Col2,Col3,Col4,Col5,Col6,Col7,Col8,Col9,Col10);
        populateCustomerTable();
    }

    public void OnWeeklyOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Appointment");
        UpdateButton.setText("Update Appointment");
        DeleteButton.setText("Delete Appointment");
        customerSelected = false;

        AppointmentsTable.setupAppointmentsTable(Col1,Col2,Col3,Col4,Col5,Col6,Col7,Col8,Col9,Col10);
    }

    public void OnMonthlyOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Appointment");
        UpdateButton.setText("Update Appointment");
        DeleteButton.setText("Delete Appointment");
        customerSelected = false;

        AppointmentsTable.setupAppointmentsTable(Col1,Col2,Col3,Col4,Col5,Col6,Col7,Col8,Col9,Col10);
    }

    public void OnAllOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Appointment");
        UpdateButton.setText("Update Appointment");
        DeleteButton.setText("Delete Appointment");

        AppointmentsTable.setupAppointmentsTable(Col1,Col2,Col3,Col4,Col5,Col6,Col7,Col8,Col9,Col10);
    }

    public void OnAddClick(ActionEvent actionEvent) throws IOException {
        if(customerSelected) {
            Parent root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addProductsMenu = new Scene(root, 1140, 900);
            stage.setTitle("Add Customer Menu");
            stage.setScene(addProductsMenu);
            stage.show();
        }
    }

    public void OnDeleteClick(ActionEvent actionEvent) {
    }

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

                    System.out.println("To modify products table clicked");
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Parent scene = loader.getRoot();
                    stage.setTitle("Modify Product Menu");
                    stage.setScene(new Scene(scene));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


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
}
