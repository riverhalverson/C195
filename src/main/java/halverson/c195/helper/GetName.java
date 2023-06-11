package halverson.c195.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//given a ID, return the matching name of the row
public class GetName {

    public static String getUserName(int seekingID) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, seekingID);
        ResultSet rs = ps.executeQuery();

        String userName = "";

        while(rs.next()){
            userName = rs.getString("User_Name");
        }

        return userName;
    }

    public static String getContactName(int seekingID) throws SQLException {
        String sql = "SELECT * FROM CONTACTS WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, seekingID);
        ResultSet rs = ps.executeQuery();

        String contactName = "";

        while(rs.next()){
            contactName = rs.getString("Contact_Name");
        }

        return contactName;
    }

}
