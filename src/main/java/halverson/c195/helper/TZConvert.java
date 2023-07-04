package halverson.c195.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
/** The class for converting between timezones */
public class TZConvert {
    /** This method converts a time from local time to UTC time
     * @param ldt the localdatetime to convert
     * @return utcTime the converted time
     */
    public static ZonedDateTime UserToUTC(LocalDateTime ldt){
        ZoneId localZoneId = ZoneId.systemDefault();

        ZonedDateTime userTime = ZonedDateTime.of(ldt,localZoneId);

        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime utcTime = ZonedDateTime.ofInstant(userTime.toInstant(), utcZoneId);

        return utcTime;
    }
    /** This method converts a time from UTC to user time
     * @param ldt the localdatetime to convert
     * @return userTime the converted time
     */
    public static ZonedDateTime UTCToUser(LocalDateTime ldt){
        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime utcTime = ZonedDateTime.of(ldt, utcZoneId);

        ZoneId localZoneId = ZoneId.systemDefault();

        ZonedDateTime userTime = ZonedDateTime.ofInstant(utcTime.toInstant(), localZoneId);

        return userTime;
    }
    /** This method converts a time from utc to est timezone
     * @param ldt the localdatetime to convert
     * @return estTime the converted time
     */
    public static ZonedDateTime UTCToEST(LocalDateTime ldt){
        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime utcTime = ZonedDateTime.of(ldt, utcZoneId);

        ZoneId estZoneId = ZoneId.of("US/Eastern");

        ZonedDateTime estTime = ZonedDateTime.ofInstant(utcTime.toInstant(), estZoneId);

        return estTime;
    }
    /** This method converts a time from user to est timezone
     * @param ldt the localdatetime to convert
     * @return estTime the converted time
     */
    public static ZonedDateTime UserToEST(LocalDateTime ldt){
        ZoneId localZoneId = ZoneId.systemDefault();

        ZonedDateTime userTime = ZonedDateTime.of(ldt,localZoneId);

        ZoneId estZoneId = ZoneId.of("US/Eastern");

        ZonedDateTime estTime = ZonedDateTime.ofInstant(userTime.toInstant(), estZoneId);

        return estTime;
    }
}
