package halverson.c195;

import javafx.scene.control.TableColumn;

/** This method sets up the main screen table for the appointments to be displayed */
public class AppointmentsTable {

    /** This method sets up the main tableview for the appointments to be displayed
     * @param Col1 column 1
     * @param Col2 column 2
     * @param Col3 column 3
     * @param Col4 column 4
     * @param Col5 column 5
     * @param Col6 column 6
     * @param Col7 column 7
     * @param Col8 column 8
     * @param Col9 column 9
     * @param Col10 column 10
     */
    public static void setupAppointmentsTable(TableColumn Col1, TableColumn Col2, TableColumn Col3,
                                          TableColumn Col4, TableColumn Col5, TableColumn Col6,
                                          TableColumn Col7, TableColumn Col8, TableColumn Col9,
                                          TableColumn Col10){

        Col1.setText("Appt ID");
        Col1.setPrefWidth(50);
        Col1.setMinWidth(50);
        Col1.setMaxWidth(50);

        Col2.setText("Title");
        Col2.setPrefWidth(150);
        Col2.setMinWidth(150);
        Col2.setMaxWidth(150);

        Col3.setText("Description");
        Col3.setPrefWidth(257);
        Col3.setMinWidth(257);
        Col3.setMaxWidth(257);

        Col4.setText("Location");
        Col4.setPrefWidth(170);
        Col4.setMinWidth(170);
        Col4.setMaxWidth(170);

        Col5.setText("Contact");
        Col5.setPrefWidth(150);
        Col5.setMinWidth(150);
        Col5.setMaxWidth(160);

        Col6.setText("Type");
        Col6.setPrefWidth(200);
        Col6.setMinWidth(200);
        Col6.setMaxWidth(200);

        Col7.setText("Start Date/Time");
        Col7.setPrefWidth(200);
        Col7.setMinWidth(200);
        Col7.setMaxWidth(200);

        Col8.setText("End Date/Time");
        Col8.setPrefWidth(200);
        Col8.setMinWidth(200);
        Col8.setMaxWidth(200);

        Col9.setText("Customer ID");
        Col9.setPrefWidth(105);
        Col9.setMinWidth(105);
        Col9.setMaxWidth(105);

        Col10.setText("User ID");
        Col10.setPrefWidth(99);
        Col10.setMinWidth(99);
        Col10.setMaxWidth(99);
    }
}
