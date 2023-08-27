module halverson.c195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens halverson.c195 to javafx.fxml;
    exports halverson.c195;
    exports halverson.c195.entities;
    opens halverson.c195.entities to javafx.fxml;
    exports halverson.c195.DAO;
    opens halverson.c195.DAO to javafx.fxml;
}