package io.fno.grel;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <p>Copyright 2022 IDLab (Ghent University - imec)</p>
 *
 * @author Gerald Haesendonck
 */
public class DateFunctionsTest {

    @Test
    public void testNow() {
        LocalDateTime nowUTC = DateFunctions.now();
        LocalDateTime nowLocal = LocalDateTime.now();
        int diff = nowUTC.compareTo(nowLocal);
        assertTrue(diff <= 0, "The timestamp first taken should not be later than the second one.");
    }

    @Test
    public void testToDate() throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.set(2022, Calendar.NOVEMBER, 28);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date expectedDate = calendar.getTime();

        Date date = DateFunctions.toDate("2022-11-28", "yyyy-MM-dd");

        assertEquals(expectedDate, date);
    }

    @Test
    public void testDateToString() throws ParseException {
        Date date = DateFunctions.toDate("2022-11-28", "yyyy-MM-dd");
        String result = DateFunctions.toString(date, "dd/MM/yyyy");
        assertEquals("28/11/2022", result);
    }

    @Test
    public void testDiff() throws ParseException {
        Date d1 = DateFunctions.toDate("2000-01-01 +02", "yyyy-MM-dd X");
        Date d2 = DateFunctions.toDate("2002-10-20 +02", "yyyy-MM-dd X");

        long diffYears = DateFunctions.diff(d1, d2, "years");
        assertEquals(2, diffYears);

        long diffMonths = DateFunctions.diff(d1, d2, "months");
        assertEquals(33, diffMonths);

        long diffWeeks = DateFunctions.diff(d1, d2, "weeks");
        assertEquals(146, diffWeeks);

        long diffDays = DateFunctions.diff(d1, d2, "days");
        assertEquals(1023, diffDays);

        long diffHours = DateFunctions.diff(d1, d2, "hours");
        assertEquals(24551, diffHours); // takes diff winter / summer clock into account

        long diffMinutes = DateFunctions.diff(d1, d2, "minutes");
        assertEquals(1473060, diffMinutes);

        long diffSeconds = DateFunctions.diff(d1, d2, "seconds");
        assertEquals(88383600, diffSeconds);

        long diffMs = DateFunctions.diff(d1, d2, "milliseconds");
        assertEquals(88383600000L, diffMs);

        long diffNs = DateFunctions.diff(d1, d2, "nanoseconds");
        assertEquals(88383600000000000L, diffNs);
    }

    @Test
    public void testInc() throws ParseException {
        Date date1 = DateFunctions.toDate("2000-01-01", "yyyy-MM-dd");
        Date date2 = DateFunctions.toDate("2002-10-20", "yyyy-MM-dd");

        // positive inc value
        Date resultPositiveDate = DateFunctions.inc(date1, 88383600000L, "millis");
        assertEquals(date2, resultPositiveDate);

        // negative inc value
        Date resultNegativeDate = DateFunctions.inc(date2, -88383600000L, "millis");
        assertEquals(date1, resultNegativeDate);
    }

    @Test
    public void testDatePart() throws ParseException {
        Date date = DateFunctions.toDate("2000-10-25 23:56:20:987 +02", "yyyy-MM-dd HH:mm:ss:S X");

        assertEquals(2000L, DateFunctions.datePart(date, "years"));
        assertEquals(10L, DateFunctions.datePart(date, "months"));
        assertEquals("Thursday", DateFunctions.datePart(date, "weekday"));
        assertEquals(21L, DateFunctions.datePart(date, "hours")); // Because it's UTC
        assertEquals(56L, DateFunctions.datePart(date, "minutes"));
        assertEquals(20L, DateFunctions.datePart(date, "seconds"));
        assertEquals(987L, DateFunctions.datePart(date, "milliseconds"));
    }
}
