package halverson.c195.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TZConvert {

    public static ZonedDateTime UserToUTC(LocalDateTime ldt){
        ZoneId localZoneId = ZoneId.systemDefault();

        ZonedDateTime userTime = ZonedDateTime.of(ldt,localZoneId);

        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime utcTime = ZonedDateTime.ofInstant(userTime.toInstant(), utcZoneId);

        return utcTime;
    }

    public static ZonedDateTime UTCToUser(LocalDateTime ldt){
        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime utcTime = ZonedDateTime.of(ldt, utcZoneId);

        ZoneId localZoneId = ZoneId.systemDefault();

        ZonedDateTime userTime = ZonedDateTime.ofInstant(utcTime.toInstant(), localZoneId);

        return userTime;
    }

    public static ZonedDateTime UTCToEST(LocalDateTime ldt){
        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime utcTime = ZonedDateTime.of(ldt, utcZoneId);

        ZoneId estZoneId = ZoneId.of("EST");

        ZonedDateTime estTime = ZonedDateTime.ofInstant(utcTime.toInstant(), estZoneId);

        return estTime;
    }
}
