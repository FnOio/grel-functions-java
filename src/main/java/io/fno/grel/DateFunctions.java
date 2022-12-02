package io.fno.grel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.*;
import java.util.*;

/*
 * NOTE: brought over from commit f598c20470abdc7b30b444a1f3a382ab310c551d,
 *       has not been tested
 */
public class DateFunctions {
    private final static Map<String, TemporalField> grelPartToFieldPos = new HashMap<>();
    static {
        grelPartToFieldPos.put("YEARS", ChronoField.YEAR);
        grelPartToFieldPos.put("MONTHS", ChronoField.MONTH_OF_YEAR);
        grelPartToFieldPos.put("MONTH", ChronoField.MONTH_OF_YEAR);
        grelPartToFieldPos.put("WEEK", ChronoField.ALIGNED_WEEK_OF_MONTH);
        grelPartToFieldPos.put("WEEKS", ChronoField.ALIGNED_WEEK_OF_MONTH);
        grelPartToFieldPos.put("W", ChronoField.ALIGNED_WEEK_OF_MONTH);
        grelPartToFieldPos.put("WEEKDAY", ChronoField.DAY_OF_WEEK);
        grelPartToFieldPos.put("HOURS", ChronoField.HOUR_OF_DAY);
        grelPartToFieldPos.put("H", ChronoField.HOUR_OF_DAY);
        grelPartToFieldPos.put("MINUTE", ChronoField.MINUTE_OF_HOUR);
        grelPartToFieldPos.put("MINUTES", ChronoField.MINUTE_OF_HOUR);
        grelPartToFieldPos.put("SECONDS", ChronoField.SECOND_OF_MINUTE);
        grelPartToFieldPos.put("SEC", ChronoField.SECOND_OF_MINUTE);
        grelPartToFieldPos.put("S", ChronoField.SECOND_OF_MINUTE);
        grelPartToFieldPos.put("MILLISECONDS", ChronoField.MILLI_OF_SECOND);
        grelPartToFieldPos.put("MS", ChronoField.MILLI_OF_SECOND);
        grelPartToFieldPos.put("NANOSECONDS", ChronoField.NANO_OF_SECOND);
        grelPartToFieldPos.put("NANO", ChronoField.NANO_OF_SECOND);
        grelPartToFieldPos.put("NANOS", ChronoField.NANO_OF_SECOND);
        grelPartToFieldPos.put("N", ChronoField.NANO_OF_SECOND);
    }

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
     * from the GREL function toDate as defined in <a href="https://openrefine.org/docs/manual/grelfunctions#todateo-b-monthfirst-s-format1-s-format2-">toDate</a>
     *
     * @param dateStr   The String representing a date(time).
     * @param pattern   The given pattern to parse the date. See {@link SimpleDateFormat}.
     *                  If {@code null}, a best effort is done to parse the given date String.
     *                  If no offset or zone info is detected, the time zone is assumed to be UTC.
     * @return          A {@link Date} parsed from the string.
     * @throws ParseException if {@code dateStr} cannot be parsed.
     */
    public static Date toDate(String dateStr, String pattern) throws ParseException {
        DateFormat dateFormat = pattern == null? new SimpleDateFormat() : new SimpleDateFormat(pattern);
        // check if pattern contains offzet or zone info. If not, assume UTC
        if (pattern == null
                || (!pattern.contains("z") && !pattern.contains("Z") && !pattern.contains("X"))) {
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return dateFormat.parse(dateStr);
    }

    /**
     * Formats a given date according to a given pattern.
     * @param date      The date to format as a String.
     * @param pattern   The given pattern to parse the date. See {@link SimpleDateFormat}.
     *                  If {@code null}, the date will be formatted according to the current local JVM settings.
     *                  If no offset or zone info is detected, the time zone is assumed to be UTC.
     * @return          The given date formatted according to the given pattern.
     */
    public static String toString(Date date, String pattern) {
        DateFormat dateFormat = pattern == null ? new SimpleDateFormat() : new SimpleDateFormat(pattern);
        if (pattern == null
                || (!pattern.contains("z") && !pattern.contains("Z") && !pattern.contains("X"))) {
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
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
        TemporalUnit parsedTimeUnit = convertToTemporalUnit(timeUnit);
        final Date d1;
        final Date d2;
        if (endDate.before(startDate)) {
            d1 = endDate;
            d2 = startDate;
        } else {
            d1 = startDate;
            d2 = endDate;
        }
        LocalDateTime dt1 = toLocalDateTime(d1);
        LocalDateTime dt2 = toLocalDateTime(d2);
        return parsedTimeUnit.between(dt1, dt2);
    }

    /**
     * This returns a date, based on this one, with the amount in terms of the unit added.
     * @param date  The date object to add the given value to
     * @param value     The amount to add
     * @param timeUnit  The time unit of the amount
     * @return          a date, based on this one, with the amount in terms of the unit added.
     * @throws DateTimeException if the addition cannot be made
     * @throws UnsupportedTemporalTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    public static Date inc(Date date, long value, String timeUnit) {
        return toDate(inc(toLocalDateTime(date), value, timeUnit));
    }

    /**
     * This returns a LocalDateTime, based on this one, with the amount in terms of the unit added.
     * @param dateTime  The dateTime object to add the given value to
     * @param value     The amount to add
     * @param timeUnit  The time unit of the amount
     * @return          a LocalDateTime, based on this one, with the amount in terms of the unit added.
     * @throws DateTimeException if the addition cannot be made
     * @throws UnsupportedTemporalTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    public static LocalDateTime inc(LocalDateTime dateTime, long value, String timeUnit) {
        TemporalUnit tu = convertToTemporalUnit(timeUnit);
        if (value >= 0)
            return dateTime.plus(value, tu);
        else {
            return dateTime.minus(-value, tu);
        }
    }

    /**
     * Returns the part of a date, according to <a href="https://openrefine.org/docs/manual/grelfunctions#todateo-b-monthfirst-s-format1-s-format2-">datePart</a>
     * @param date  The date to get a part of
     * @param field The name of the field to return. It supports the units as defined in
     *              <a href="https://openrefine.org/docs/manual/grelfunctions#todateo-b-monthfirst-s-format1-s-format2-">datePart</a>
     *              as well as the ones defined in {@link ChronoField}.
     * @return      a {@code long} value, except if {@code field} has value {@code weekday} in which case the
     *              name of the day is returned as a String.
     * @throws NullPointerException if the given date or field is {@code null}.
     * @throws DateTimeException    if the result exceeds the supported range
     * @throws IllegalArgumentException if ththe field is no valid GREL time unit or {@link ChronoField}.
     * @throws UnsupportedTemporalTypeException  if the field is not supported or the range of values exceeds an int
     * @throws ArithmeticException - if numeric overflow occurs
     * 
     */
    public static Object datePart(final Date date, final String field) {
        String uField = field.toUpperCase();

        if (uField.equals("TIME")) {
            // then return the nr of milliseconds between "date" and epoch
            return date.getTime();
        }

        // try to map the (GREL) field to a Java TemporalField
        // the default is to try to map to a ChronoField
        TemporalField temporalField = grelPartToFieldPos.get(uField);
        if (temporalField == null) {
            temporalField = ChronoField.valueOf(uField);
        }

        long result = toLocalDateTime(date).get(temporalField);

        if (temporalField.equals(ChronoField.DAY_OF_WEEK)) {
            // convert day of the week to a String
            return DayOfWeek.values()[(int)result].getDisplayName(TextStyle.FULL, Locale.getDefault());
        } else {
            return result;
        }
    }

    /**
     * Converts the given {@link Date} (in UTC) to a {@link LocalDateTime} object. GREL assumes dates
     * are in UTC.
     * @param date  The date to convert to a {@link LocalDateTime} object.
     * @return      The converted date.
     * @throws DateTimeException if the result exceeds the supported range
     */
    private static LocalDateTime toLocalDateTime(final Date date) {
        return date.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
    }

    /**
     * Converts the given {@link LocalDateTime} to a {@link Date} object in UTC (which is what GREL expects).
     * @param localDateTime     The local time to convert to a date.
     * @return                  The Date object corresponding to the given localDateTime.
     * @throws NullPointerException if {@code localDateTime} is null.
     * @throws IllegalArgumentException  if {@code localDateTime} is too large to represent as a Date
     */
    private static Date toDate(final LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
    }

    /**
     * Parses a string expressed as <a href="https://openrefine.org/docs/manual/grelfunctions#datepartd-s-timeunit">GREL time unit</a>
     * to a Java {@link TemporalUnit}.
     * @param timeUnit  The GREL time unit to convert
     * @return          A corresponding Java TemporalUnit (ChronoUnit)
     * @throws IllegalArgumentException if no TemporalUnit has been found with the specified name
     * @throws NullPointerException     if the argument is null
     */
    private static TemporalUnit convertToTemporalUnit(final String timeUnit) {
        String tu = timeUnit.toUpperCase();
        if (tu.equals("MILLISECONDS")) {
            tu = "MILLIS";
        } else if (tu.equalsIgnoreCase("NANOSECONDS")) {
            tu = "NANOS";
        }
        return ChronoUnit.valueOf(tu.toUpperCase(Locale.ROOT));
    }
}
