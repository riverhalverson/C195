package halverson.c195;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class MainScreenController {
    public RadioButton CustomersOption;
    public RadioButton WeeklyOption;
    public RadioButton MonthlyOption;
    public RadioButton AllOption;
    public Button AddButton;
    public Button DeleteButton;
    public Button UpdateButton;

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
    }

    public void OnWeeklyOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Appointment");
        UpdateButton.setText("Update Appointment");
        DeleteButton.setText("Delete Appointment");
    }

    public void OnMonthlyOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Appointment");
        UpdateButton.setText("Update Appointment");
        DeleteButton.setText("Delete Appointment");
    }

    public void OnAllOptionClick(ActionEvent actionEvent) {
        AddButton.setText("Add Appointment");
        UpdateButton.setText("Update Appointment");
        DeleteButton.setText("Delete Appointment");
    }

    public void OnAddClick(ActionEvent actionEvent) {
    }

    public void OnDeleteClick(ActionEvent actionEvent) {
    }

    public void OnUpdateClick(ActionEvent actionEvent) {
    }
}
