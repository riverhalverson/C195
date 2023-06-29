package halverson.c195.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** The GetId class, these functions return an ID for a given name */
public class GetId {

    /** This method returns the user id for a given username
     * @param seekingName the user id to look for
     * @return userID the user id of the given username
     */
    public static int getUserId(String seekingName) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, seekingName);
        ResultSet rs = ps.executeQuery();

        int userID = 0;

        //get user id
        while(rs.next()){
            userID = rs.getInt("User_ID");
        }

        return userID;
    }
    /** This method gets a contact id with a given contact name
     * @param seekingName the username to look for
     * @return contactId the contact id of the given contact
     */
    public static int getContactId(String seekingName) {
        String sql = "SELECT * FROM CONTACTS WHERE Contact_Name = ?";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, seekingName);
            ResultSet rs = ps.executeQuery();

            int contactID = 0;

            //get contact id
            while(rs.next()){
                contactID = rs.getInt("Contact_ID");
                return contactID;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }
    /** This method gets a division id for a given division name
     * @param division the division to look for
     * @return divId the division id
     */
    public static int getDivisionId(String division) throws SQLException {
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, division);
        ResultSet rs = ps.executeQuery();

        int divId = 0;

        //get division id
        if (rs.next()) {
            do {
                divId = rs.getInt("Division_ID");
            } while (rs.next());
        }

        return divId;
    }
}
