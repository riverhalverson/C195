package halverson.c195.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class ApptChecks {
    public static boolean checkNewAptOverlap(LocalDateTime start, LocalDateTime end, int customerId) throws SQLException {
        boolean overlap = false;

        String sql = "SELECT * FROM APPOINTMENTS WHERE Customer_ID = ?";

        try{
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LocalDateTime rStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime rEnd = rs.getTimestamp("End").toLocalDateTime();

                //convert times to local
                rStart = TZConvert.UTCToUser(rStart).toLocalDateTime();
                rEnd = TZConvert.UTCToUser(rEnd).toLocalDateTime();

                if((start.isAfter(rStart) || start.isEqual(rStart)) && (start.isBefore(rEnd)))
                {
                    return true;
                } else if(end.isAfter(rEnd) && (end.isBefore(rEnd) || end.isEqual(rEnd)) )
                {
                    return true;
                } else if((start.isBefore(rStart) || start.isEqual(rStart)) && (end.isAfter(rEnd) || end.isEqual(rEnd)))
                {
                    return true;
                }
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return overlap;
    }
    public static boolean checkUpdateAptOverlap(LocalDateTime start, LocalDateTime end, int customerId, int apptId) throws SQLException {
        boolean overlap = false;

        String sql = "SELECT * FROM APPOINTMENTS WHERE Customer_ID = ?";

        try{
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while(rs.next() && (rs.getInt("Appointment_ID") != apptId)){
                LocalDateTime rStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime rEnd = rs.getTimestamp("End").toLocalDateTime();

                //convert times to local
                rStart = TZConvert.UTCToUser(rStart).toLocalDateTime();
                rEnd = TZConvert.UTCToUser(rEnd).toLocalDateTime();

                if((start.isAfter(rStart) || start.isEqual(rStart)) && (start.isBefore(rEnd)))
                {
                    return true;
                } else if(end.isAfter(rEnd) && (end.isBefore(rEnd) || end.isEqual(rEnd)) )
                {
                    return true;
                } else if((start.isBefore(rStart) || start.isEqual(rStart)) && (end.isAfter(rEnd) || end.isEqual(rEnd)))
                {
                    return true;
                }
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return overlap;
    }
    public static boolean duringWorkingHours(LocalDateTime start, LocalDateTime end){
        boolean withinHours = false;
        LocalTime openHour = LocalTime.of(8,00);
        LocalTime closeHour = LocalTime.of(17, 00);

        //convert to eastern time
        ZonedDateTime aptStartEST = TZConvert.UserToEST(LocalDateTime.from(start));
        LocalTime aptStart = aptStartEST.toLocalTime();

        ZonedDateTime aptEndEST = TZConvert.UserToEST((LocalDateTime.from(end)));
        LocalTime aptEnd = aptEndEST.toLocalTime();


        if(aptStart.isBefore(openHour) || aptEnd.isAfter(closeHour)){
            withinHours = false;
        }
        else{
            withinHours = true;
        }

        return withinHours;
    }
}
