package halverson.c195;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MainScreenController {
    public void OnAddAptClick(ActionEvent actionEvent) {
    }

    public void OnDeleteAptClick(ActionEvent actionEvent) {
    }

    public void OnUpdateAptClick(ActionEvent actionEvent) {
    }

    public void OnExitClick(ActionEvent actionEvent) {
        System.out.println("Exited");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void OnReportsClick(ActionEvent actionEvent) {
    }
}
