package io.fno.grel;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

/*
 * NOTE: brought over from commit f598c20470abdc7b30b444a1f3a382ab310c551d,
 *       has not been tested
 */
public class DateFunctions {

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    // TODO
    public static String toDate(String o, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(o);
    }

    public static String toString(String date, String pattern) {
        return toDate(date, pattern);
    }

    // TODO
    public static String diff(LocalDateTime d1, LocalDateTime d2, String timeUnit) {
        Duration duration = Duration.between(d1, d2);
        return duration.toString();
    }

    public static String inc(LocalDateTime f, long value, TemporalUnit unit) {
        return f.plus(value, unit).toString();
    }

    public static long datePart(LocalDateTime d, TemporalField unit) {
        return d.get(unit);
    }
}
