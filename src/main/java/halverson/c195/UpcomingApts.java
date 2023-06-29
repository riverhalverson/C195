package halverson.c195;

import halverson.c195.helper.DisplayAlert;
import halverson.c195.helper.GetName;
import halverson.c195.helper.JDBC;
import halverson.c195.helper.TZConvert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class UpcomingApts {

    public static void checkApts(int userId) throws SQLException {
        ObservableList<AppointmentRow> apts = FXCollections.observableArrayList();
        ObservableList<String> matchingApts = FXCollections.observableArrayList();
        String message = "Appointments within 15 Minutes \n";
        matchingApts.add(message);

        boolean withinFifteen = false;

        String sql = "SELECT * FROM APPOINTMENTS WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

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
            int customerid = rs.getInt("Customer_ID");
            int userid = rs.getInt("User_ID");

            //convert apt times to local time
            LocalDateTime start = TZConvert.UTCToUser(startUtc).toLocalDateTime();
            LocalDateTime end = TZConvert.UTCToUser(endUtc).toLocalDateTime();

            //gets current day
            int currentDay = LocalDateTime.now().getDayOfMonth();

            //get system time
            ZoneId localZoneId = ZoneId.systemDefault();
            LocalDateTime currentDateTime = LocalDateTime.now(localZoneId);

            //convert apt start times and local times to just time values
            LocalTime aptStart = start.toLocalTime();
            LocalTime currentTime = currentDateTime.toLocalTime();

            //checks if the date matches
            if(start.getDayOfMonth() == currentDay) {
                //checks if apt times within 15 minutes
                if (aptStart.isAfter(currentTime) && aptStart.isBefore(currentTime.plusMinutes(15))) {
                    AppointmentRow ar = new AppointmentRow(appointmentid, title,
                            description, location, contact, type, start,
                            end, customerid, userid);
                    apts.add(ar);

                }
            }
        }

        if(apts.size() != 0){
            for(int i = 0; i < apts.size(); i++ ) {
                AppointmentRow aRow = apts.get(i);
                int aptId = aRow.getappointmentid();
                LocalDateTime start = aRow.getStart();
                LocalDateTime end = aRow.getEnd();

                String apptMessage = "Appointment ID:" + aptId + ", Start:" + start + " End:" +
                        end + " \n";

                matchingApts.add(apptMessage);
            }
            DisplayAlert.customError(matchingApts.toString());
        }
        else{
            DisplayAlert.customError("No Appointments within 15 minutes");
        }

    }

}
