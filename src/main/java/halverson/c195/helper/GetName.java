package halverson.c195.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** The inventory class, holds all objects of the parts and products classes */
public class GetName {
    /** This method gets a contact name with a given contact id
     * @param seekingID the contact id to look for
     * @return contactName the contact name of the given contact id
     */
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
    /** This method gets a division name with a given id
     * @param seekingID the division name to look for
     * @return divisionname the division name of the given id
     */
    public static String getDivisionName(int seekingID) throws SQLException {
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, seekingID);
        ResultSet rs = ps.executeQuery();

        String divisionName = "";

        //get division name
        while(rs.next()){
            divisionName = rs.getString("Division");
        }

        return divisionName;
    }
    /** This method gets a country for a given division id
     * @param divisionId the division id to look for
     * @return country the country name for the division id
     */
    public static String getCountry(int divisionId) throws SQLException {
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionId);
        ResultSet getcountryid = ps.executeQuery();

        int countryId = 0;

        //get country id
        while(getcountryid.next()){
            countryId = getcountryid.getInt("Country_ID");
        }

        //get country name
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
}
