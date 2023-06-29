package halverson.c195.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/** The display alert class, holds all classes to make displaying alerts easier */
public class DisplayAlert {

    /** This method displays an alert for an empty field
     * @param fieldName the field name to be displayed with the alert
     */
    public static void emptyAlert(String fieldName){
        Alert invalidData = new Alert(Alert.AlertType.ERROR,
                fieldName + " has no data entered");

        Optional<ButtonType> cannotDeleteResult = invalidData.showAndWait();
        return;
    }
    /** This method displays an alert with a custom message
     * @param errorMessage the message to be displayed in the alert
     */
    public static void customError(String errorMessage){
        Alert invalidData = new Alert(Alert.AlertType.ERROR,
                errorMessage);

        Optional<ButtonType> cannotDeleteResult = invalidData.showAndWait();
        return;
    }
}
