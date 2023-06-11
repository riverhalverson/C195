package halverson.c195.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DisplayAlert {

    public static void deleteAlert(String deletedItem){
        Alert invalidData = new Alert(Alert.AlertType.ERROR,
                deletedItem + " was deleted");

        Optional<ButtonType> cannotDeleteResult = invalidData.showAndWait();
        return;
    }
    public static void updateAlert(String updatedItem){
        Alert invalidData = new Alert(Alert.AlertType.ERROR,
                updatedItem + " was updated");

        Optional<ButtonType> cannotDeleteResult = invalidData.showAndWait();
        return;
    }
    public static void emptyAlert(String fieldName){
        Alert invalidData = new Alert(Alert.AlertType.ERROR,
                fieldName + " has no data entered");

        Optional<ButtonType> cannotDeleteResult = invalidData.showAndWait();
        return;
    }
    public static void invalidLogin(){
        Alert invalidData = new Alert(Alert.AlertType.ERROR,
                "Login information incorrect");

        Optional<ButtonType> cannotDeleteResult = invalidData.showAndWait();
        return;
    }
}
