package halverson.c195;

import halverson.c195.helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerQuery {
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

    public static int deleteCustomer(int customerid) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerid);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
