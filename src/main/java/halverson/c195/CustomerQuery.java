package halverson.c195;

import halverson.c195.helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** This class runs query on the customer table */
public class CustomerQuery {

    /** This method inserts a customer into the customer table
     * @param name the name of the customer
     * @param address the address of the customer
     * @param postalCode the postal code of the customer
     * @param phoneNumber the phone number of the customer
     * @param divisionId the division id of the cusotomer
     * @return rowsAffected the number of affected rows, if 0 then no insert performed, if 1 then it was successful
     */
    public static int insertCustomer(String name, String address, String postalCode,
                                     String phoneNumber, int divisionId ) throws SQLException {

        String sql = "INSERT INTO CUSTOMERS  (Customer_Name, Address, Postal_Code, Phone, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES" +
                " (?, ?, ?, ?, ?, 'script', ?, 'script', ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
        ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
        ps.setInt(7, divisionId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method updates a customer in the customer table
     * @param name the name of the customer
     * @param address the address of the customer
     * @param postalCode the postal code of the customer
     * @param phoneNumber the phone number of the customer
     * @param divisionId the division id of the cusotomer
     * @return rowsAffected the number of affected rows, if 0 then no update performed, if 1 then it was successful
     */
    public static int updateCustomer(int customerid, String name, String address, String postalCode,
                                     String phoneNumber, int divisionId) throws SQLException{

        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?," +
                "Phone = ?, Create_Date = NOW(), Created_By = 'script', Last_Update = NOW()," +
                "Last_Updated_By = 'script', Division_ID = ? WHERE Customer_ID = ?";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(6, customerid);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setInt(5, divisionId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method deletes a customer from the customer table
     * @param customerid the name of the customer
     * @return rowsAffected the number of affected rows, if 0 then no delete performed, if 1 then it was successful
     */
    public static int deleteCustomer(int customerid) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerid);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
