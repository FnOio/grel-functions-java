import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GREL Functions
 *
 * @author bjdmeest
 */
public class GrelFunctionsCustom {

    public static boolean isSet(String valueParameter) {
        return !StringUtils.isEmpty(valueParameter);
    }

    public static boolean booleanMatch(String valueParameter, String regexParameter) {
        return valueParameter.matches(regexParameter);
    }


    /**
     * Returns the string obtained by joining two strings `s1` and `s2` with the separator `sep`.
     * For example, `join("foo", "bar", ";")` returns the string `foo;bar`.
     *
     * @param s1  string
     * @param s2  string
     * @param sep separator
     * @return the string obtained by joining two strings `s1` and `s2` with the separator `sep`
     */
    public static String join2(String s1, String s2, String sep) {
        return s1 + sep + s2;
    }

    /**
     * Returns `s` as a normalized xsd:date string, using `f` as current date form.
     *
     * @param s string
     * @param f format
     * @return a normalized xsd:date string
     */
    public static String normalizeDate(String s, String f) {
        DateFormat format = new SimpleDateFormat(f);
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return s;
        }
        DateFormat xsdDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return xsdDateFormat.format(date);
    }

    private static String decodeURIComponent(String encodedURI) {
        char actualChar;

        StringBuffer buffer = new StringBuffer();

        int bytePattern, sumb = 0;

        for (int i = 0, more = -1; i < encodedURI.length(); i++) {
            actualChar = encodedURI.charAt(i);

            switch (actualChar) {
                case '%': {
                    actualChar = encodedURI.charAt(++i);
                    int hb = (Character.isDigit(actualChar) ? actualChar - '0'
                            : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
                    actualChar = encodedURI.charAt(++i);
                    int lb = (Character.isDigit(actualChar) ? actualChar - '0'
                            : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
                    bytePattern = (hb << 4) | lb;
                    break;
                }
                case '+': {
                    bytePattern = ' ';
                    break;
                }
                default: {
                    bytePattern = actualChar;
                }
            }

            if ((bytePattern & 0xc0) == 0x80) { // 10xxxxxx
                sumb = (sumb << 6) | (bytePattern & 0x3f);
                if (--more == 0)
                    buffer.append((char) sumb);
            } else if ((bytePattern & 0x80) == 0x00) { // 0xxxxxxx
                buffer.append((char) bytePattern);
            } else if ((bytePattern & 0xe0) == 0xc0) { // 110xxxxx
                sumb = bytePattern & 0x1f;
                more = 1;
            } else if ((bytePattern & 0xf0) == 0xe0) { // 1110xxxx
                sumb = bytePattern & 0x0f;
                more = 2;
            } else if ((bytePattern & 0xf8) == 0xf0) { // 11110xxx
                sumb = bytePattern & 0x07;
                more = 3;
            } else if ((bytePattern & 0xfc) == 0xf8) { // 111110xx
                sumb = bytePattern & 0x03;
                more = 4;
            } else { // 1111110x
                sumb = bytePattern & 0x01;
                more = 5;
            }
        }
        return buffer.toString();
    }
}
