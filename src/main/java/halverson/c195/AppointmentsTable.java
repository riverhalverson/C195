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

        Col1.setText("Appointment ID");
        Col1.setPrefWidth(106);
        Col1.setMinWidth(106);
        Col1.setMaxWidth(106);

        Col2.setText("Title");
        Col2.setPrefWidth(84);
        Col2.setMinWidth(84);
        Col2.setMaxWidth(84);

        Col3.setText("Description");
        Col3.setPrefWidth(257);
        Col3.setMinWidth(257);
        Col3.setMaxWidth(257);

        Col4.setText("Location");
        Col4.setPrefWidth(135);
        Col4.setMinWidth(135);
        Col4.setMaxWidth(135);

        Col5.setText("Contact");
        Col5.setPrefWidth(202);
        Col5.setMinWidth(202);
        Col5.setMaxWidth(202);

        Col6.setText("Type");
        Col6.setPrefWidth(117);
        Col6.setMinWidth(117);
        Col6.setMaxWidth(117);

        Col7.setText("Start Date/Time");
        Col7.setPrefWidth(252);
        Col7.setMinWidth(252);
        Col7.setMaxWidth(252);

        Col8.setText("End Date/Time");
        Col8.setPrefWidth(248);
        Col8.setMinWidth(248);
        Col8.setMaxWidth(248);

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
