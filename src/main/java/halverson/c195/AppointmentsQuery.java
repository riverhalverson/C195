package halverson.c195;

import halverson.c195.helper.GetName;
import halverson.c195.helper.JDBC;
import halverson.c195.helper.TZConvert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class AppointmentsQuery {
    public static int insertAppointment(String title, String description, String location,
                                     String type, Timestamp start, Timestamp end,
                                        int customerId, int contactId, int userId) throws SQLException {

        String sql = "INSERT INTO APPOINTMENTS  (Title, Description, Location, Type, Start, " +
                "End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, Contact_ID, User_ID) VALUES" +
                " (?, ?, ?, ?, ?, ?, NOW() , 'script', NOW(), 'script', ?, ?, ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
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
    public static int updateAppointment(int appointmentId, String title, String description, String location,
                                        String type, Timestamp start, Timestamp end,
                                        int customerId, int contactId, int userId) throws SQLException {

        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, " +
                "End = ?, Customer_ID = ?, Contact_ID = ?, User_ID = ? WHERE Appointment_ID = ?";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setInt(7, customerId);
        ps.setInt(8, contactId);
        ps.setInt(9, userId);
        ps.setInt(10, appointmentId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
    public static ObservableList<AppointmentRow> get(){
        ObservableList<AppointmentRow> list = FXCollections.observableArrayList();


        PreparedStatement stmt;
        String sql = "SELECT * FROM APPOINTMENTS";

        try{
            stmt = JDBC.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int appointmentid = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactid = rs.getInt("Contact_ID");
                String contact = GetName.getContactName(contactid);
                String type = rs.getString("Type");
                LocalDateTime startUtc = rs.getTimestamp("Start").toLocalDateTime();

                LocalDateTime endUtc = rs.getTimestamp("End").toLocalDateTime();

                //convert times to user times
                LocalDateTime start = TZConvert.UTCToUser(startUtc).toLocalDateTime();

                LocalDateTime end = TZConvert.UTCToUser(endUtc).toLocalDateTime();

                int customerid = rs.getInt("Customer_ID");
                int userid = rs.getInt("User_ID");

                AppointmentRow ar = new AppointmentRow( appointmentid, title,
                        description, location, contact, type, start,
                        end, customerid, userid);

                list.add(ar);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public static int deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }


}
