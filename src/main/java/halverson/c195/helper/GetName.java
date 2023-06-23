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
    public static String getDivisionName(int seekingID) throws SQLException {
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, seekingID);
        ResultSet rs = ps.executeQuery();

        String divisionName = "";

        while(rs.next()){
            divisionName = rs.getString("Division");
        }

        return divisionName;
    }
    public static String getCountry(int divisionId) throws SQLException {
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionId);
        ResultSet getcountryid = ps.executeQuery();

        int countryId = 0;

        while(getcountryid.next()){
            countryId = getcountryid.getInt("Country_ID");
        }

        String sqlcountry = "SELECT * FROM COUNTRIES WHERE Country_ID = ?";
        PreparedStatement pscountryname = JDBC.connection.prepareStatement(sqlcountry);
        pscountryname.setInt(1, countryId);
        ResultSet getcountryname = pscountryname.executeQuery();

        String country = "";

        while(getcountryname.next()){
            country = getcountryname.getString("Country");
        }

        return country;
    }

    public static String getContact(int contactId) throws SQLException{
        String sql = "SELECT * FROM CONTACTS WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();

        String contactName = "";

        while(rs.next()){
            contactName = rs.getString("Contact_Name");
        }

        return contactName;
    }

}
