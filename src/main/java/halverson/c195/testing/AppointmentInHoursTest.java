package halverson.c195.testing;

import halverson.c195.helper.ApptChecks;
import halverson.c195.helper.TZConvert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class AppointmentInHoursTest {

    public static void testingClass() {
        LocalDateTime test1start = LocalDateTime.of(
                2023, 1,1, 10, 00);
        LocalDateTime test1end = LocalDateTime.of(
                2023, 1,1, 11, 00);
        Boolean test1 = ApptChecks.duringWorkingHours(test1start, test1end);
        System.out.println("Given a start time of: " + test1start + " and an end time of: " + test1end +
                ", the result whether the times are within business hours are: " + test1);

        LocalDateTime test2start = LocalDateTime.of(
                2023, 1,1, 4, 00);
        LocalDateTime test2end = LocalDateTime.of(
                2023, 1,1, 11, 00);
        Boolean test2 = ApptChecks.duringWorkingHours(test2start, test2end);
        System.out.println("Given a start time of: " + test2start + " and an end time of: " + test2end +
                ", the result whether the times are within business hours are: " + test2);

        LocalDateTime test3start = LocalDateTime.of(
                2023, 1,1, 10, 00);
        LocalDateTime test3end = LocalDateTime.of(
                2023, 1,1, 18, 00);
        Boolean test3 = ApptChecks.duringWorkingHours(test3start, test3end);
        System.out.println("Given a start time of: " + test3start + " and an end time of: " + test3end +
                ", the result whether the times are within business hours are: " + test3);

        LocalDateTime test4start = LocalDateTime.of(
                2023, 1,1, 5, 00);
        LocalDateTime test4end = LocalDateTime.of(
                2023, 1,1, 15, 00);
        Boolean test4 = ApptChecks.duringWorkingHours(test4start, test4end);
        System.out.println("Given a start time of: " + test4start + " and an end time of: " + test4end +
                ", the result whether the times are within business hours are: " + test4);

        LocalDateTime test5start = LocalDateTime.of(
                2023, 1,1, 6, 00);
        LocalDateTime test5end = LocalDateTime.of(
                2023, 1,1, 15, 00);
        Boolean test5 = ApptChecks.duringWorkingHours(test5start, test5end);
        System.out.println("Given a start time of: " + test5start + " and an end time of: " + test5end +
                ", the result whether the times are within business hours are: " + test5);
    }
}
