package halverson.c195.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DisplayAlert {
    public static void emptyAlert(String fieldName){
        Alert invalidData = new Alert(Alert.AlertType.ERROR,
                fieldName + " has no data entered");

        Optional<ButtonType> cannotDeleteResult = invalidData.showAndWait();
        return;
    }
    public static void customError(String errorMessage){
        Alert invalidData = new Alert(Alert.AlertType.ERROR,
                errorMessage);

        Optional<ButtonType> cannotDeleteResult = invalidData.showAndWait();
        return;
    }

}
