package halverson.c195;

import javafx.scene.control.TableColumn;
/** This class is to hold the method that sets up the customer tableview */
public class CustomerTable {
    /** This method sets up the main tableview for the customers to be displayed
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
    public static void setupCustomerTable(TableColumn Col1, TableColumn Col2, TableColumn Col3,
                                          TableColumn Col4, TableColumn Col5, TableColumn Col6,
                                          TableColumn Col7, TableColumn Col8, TableColumn Col9,
                                          TableColumn Col10){

        Col1.setText("Customer ID");
        Col1.setPrefWidth(80);
        Col1.setMinWidth(80);
        Col1.setMaxWidth(80);

        Col2.setText("Customer Name");
        Col2.setPrefWidth(180);
        Col2.setMinWidth(180);
        Col2.setMaxWidth(180);

        Col3.setText("Address");
        Col3.setPrefWidth(200);
        Col3.setMinWidth(200);
        Col3.setMaxWidth(200);

        Col4.setText("Postal Code");
        Col4.setPrefWidth(100);
        Col4.setMinWidth(100);
        Col4.setMaxWidth(100);

        Col5.setText("Phone");
        Col5.setPrefWidth(180);
        Col5.setMinWidth(180);
        Col5.setMaxWidth(180);

        Col6.setText("Create Date");
        Col6.setPrefWidth(260);
        Col6.setMinWidth(260);
        Col6.setMaxWidth(260);

        Col7.setText("Created By");
        Col7.setPrefWidth(80);
        Col7.setMinWidth(80);
        Col7.setMaxWidth(80);

        Col8.setText("Last Update");
        Col8.setPrefWidth(260);
        Col8.setMinWidth(260);
        Col8.setMaxWidth(260);

        Col9.setText("Last Updated By");
        Col9.setPrefWidth(100);
        Col9.setMinWidth(100);
        Col9.setMaxWidth(100);

        Col10.setText("State/Province");
        Col10.setPrefWidth(160);
        Col10.setMinWidth(160);
        Col10.setMaxWidth(160);
    }
}
