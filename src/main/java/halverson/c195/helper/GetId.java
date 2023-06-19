package halverson.c195.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetId {

    public static int getUserId(String seekingName) throws SQLException {
        String sql = "SELECT * FROM CONTACTS WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, seekingName);
        ResultSet rs = ps.executeQuery();

        int userID = 0;

        while(rs.next()){
            userID = rs.getInt("User_ID");
        }

        return userID;
    }
    public static int getContactId(String seekingName) throws SQLException {
        String sql = "SELECT * FROM CONTACTS WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, seekingName);
        ResultSet rs = ps.executeQuery();

        int contactID = 0;

        while(rs.next()){
            contactID = rs.getInt("Contact_ID");
        }

        return contactID;
    }
    public static int getDivisionId(String division) throws SQLException {
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, division);
        ResultSet rs = ps.executeQuery();

        int userID = 0;

        /*
        while(rs.next()){
            userID = rs.getInt("Division_ID");
        }

         */


        if (rs.next()) {
            do {
                userID = rs.getInt("Division_ID");
            } while (rs.next());
        }

        return userID;
    }

}
