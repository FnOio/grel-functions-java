package io.fno.grel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Locale;

/*
 * NOTE: brought over from commit f598c20470abdc7b30b444a1f3a382ab310c551d,
 *       has not been tested
 */
public class DateFunctions {

    /**
     * Returns the current time according to your system clock, in the ISO 8601 extended format (converted to UTC).
     *
     * @return  The current time in UTC
     */
    public static LocalDateTime now() {
        return LocalDateTime.now(Clock.systemUTC());
    }

    /**
     * Returns the inputted object converted to a date object. Note that this deviates
     * from the GREL function toDate as defined in {@link <a href="https://openrefine.org/docs/manual/grelfunctions#todateo-b-monthfirst-s-format1-s-format2-">toDate</a>}
     *
     * @param dateStr   The date object. A {@code toString()} is performed before
     * @param pattern   The given pattern to parse the date. See {@link SimpleDateFormat}.
     *                  If {@code null}, a best effort is done to parse the given date String.
     * @return          A {@link Date} parsed from the string.
     * @throws ParseException if {@code dateStr} cannot be parsed.
     */
    public static Date toDate(String dateStr, String pattern) throws ParseException {
        DateFormat dateFormat = pattern == null? new SimpleDateFormat() : new SimpleDateFormat(pattern);
        return dateFormat.parse(dateStr);
    }

    /**
     * Formats a given date according to a given pattern.
     * @param date      The date to format as a String.
     * @param pattern   The given pattern to parse the date. See {@link SimpleDateFormat}.
     *                  If {@code null}, the date will be formatted according to the current local JVM settings.
     * @return          The given date formatted according to the given pattern.
     */
    public static String toString(Date date, String pattern) {
        DateFormat dateFormat = pattern == null ? new SimpleDateFormat() : new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * Returns the difference between two dates, expressed in a given time unit.
     * This number is always >= 0.
     * @param startDate The first date, inclusive
     * @param endDate   The second date, exclusive.
     * @param timeUnit  The time unit. Supported time units: {@link ChronoUnit}
     * @return          The difference between start- and enddate, expressed in the given timeUnit.
     * @throws IllegalArgumentException if this enum class has no constant with the specified name
     * @throws NullPointerException     if the argument is null
     */
    public static long diff(final Date startDate, final Date endDate, String timeUnit) {
        if (timeUnit.equalsIgnoreCase("milliseconds")) {
            timeUnit = "millis";
        } else if (timeUnit.equalsIgnoreCase("nanoseconds")) {
            timeUnit = "nanos";
        }
        ChronoUnit parsedTimeUnit = ChronoUnit.valueOf(timeUnit.toUpperCase(Locale.ROOT));
        final Date d1;
        final Date d2;
        if (endDate.before(startDate)) {
            d1 = endDate;
            d2 = startDate;
        } else {
            d1 = startDate;
            d2 = endDate;
        }
        LocalDateTime dt1 = d1.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime dt2 = d2.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        return parsedTimeUnit.between(dt1, dt2);
    }

    public static String inc(LocalDateTime f, long value, TemporalUnit unit) {
        return f.plus(value, unit).toString();
    }

    public static long datePart(LocalDateTime d, TemporalField unit) {
        return d.get(unit);
    }
}
