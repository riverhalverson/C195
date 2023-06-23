package halverson.c195;

import halverson.c195.helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentsQuery {
    public static int insertAppointment(String title, String description, String location,
                                     String type, Timestamp start, Timestamp end,
                                        int customerId, int contactId, int userId) throws SQLException {

        String sql = "INSERT INTO APPOINTMENTS  (Title, Description, Location, Type, Start, " +
                "End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, Contact_ID, User_ID) VALUES" +
                " (?, ?, ?, ?, ?, ?, NOW() , 'script', NOW(), 'script', ?, ?, ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        String name;
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setInt(7, customerId);
        ps.setInt(8, contactId);
        ps.setInt(9, userId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

}
