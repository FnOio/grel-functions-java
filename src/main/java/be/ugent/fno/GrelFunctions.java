package be.ugent.fno;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GREL Functions
 * Find info at https://github.com/OpenRefine/OpenRefine/wiki/GREL-Functions
 * @author bjdmeest
 */
public class GrelFunctions {

    // BOOLEAN
    public static boolean and(boolean b1, boolean b2, boolean... booleans) {
        if (! (b1 || b2)) {
            return false;
        }
        for (boolean aBoolean : booleans) {
            if (! aBoolean) {
                return false;
            }
        }
        return true;
    }

    public static boolean or(boolean b1, boolean b2, boolean... booleans) {
        if (b1 || b2) {
            return true;
        }
        for (boolean aBoolean : booleans) {
            if (aBoolean) {
                return true;
            }
        }
        return false;
    }

    public static boolean not(boolean b) {
        return !b;
    }

    public static boolean xor(boolean b1, boolean b2, boolean... booleans) {
        boolean alreadyTrue = false;
        if (b1 & b2) {
            return false;
        } else if (b1 || b2) {
            alreadyTrue = true;
        }
        for (boolean aBoolean : booleans) {
            if (aBoolean) {
                if (alreadyTrue) {
                    return false;
                }
                alreadyTrue = true;
            }
        }
        return alreadyTrue;
    }

    // STRINGS
    // basic

    public static int length(String valueParameter) {
        return valueParameter.length();
    }

    public static String toString(Object valueParameter) {
        if (valueParameter == null) {
            return "null";
        } else {
            return valueParameter.toString();
        }
    }

    // testing string characteristics

    public static boolean startsWith(String s, String sub) {
        return s.startsWith(sub);
    }

    public static boolean endsWith(String s, String sub) {
        return s.endsWith(sub);
    }

    public static boolean contains(String valueParameter, String subStringParameter) {
        return valueParameter.contains(subStringParameter);
    }

    public static int indexOf(String valueParameter, String subStringParameter) {
        return valueParameter.indexOf(subStringParameter);
    }

    // basic string modification

    public static String toLowercase(String valueParameter) {
        return valueParameter.toLowerCase();
    }

    public static String toUppercase(String valueParameter) {
        return valueParameter.toUpperCase();
    }

    public static String toTitlecase(String valueParameter) {
        return StringUtils.capitalize(valueParameter);
    }

    // trimming

    public static String trim(String valueParameter) {
        return valueParameter.trim();
    }

    public static String strip(String valueParameter) {
        return StringUtils.strip(valueParameter);
    }

    public static String chomp(String valueParameter, String seperator) {
        return StringUtils.chomp(valueParameter, seperator);
    }

    // substring
    public static String substring(String valueParameter, int from, int to) {
        return valueParameter.substring(from, to);
    }

    public static String slice(String valueParameter, int from, int to) {
        return substring(valueParameter, from, to);
    }

    public static String get(String valueParameter, int from, int to) {
        return substring(valueParameter, from, to);
    }

//    public static Object get(Object[] valueParameter, int from, int to) {
//        return valueParameter[from, to];
//    }
//
//
//    public static String get(Object valueParameter, String from) {
//        return valueParameter;
//    }

    // Find and replace

    public static int lastIndexOf(String s, String sub) {
        return s.lastIndexOf(sub);
    }

    public static String replace(String s, String f, String r) {
        return s.replaceAll(f, r);
    }

    public static String replaceChars(String s, String f, String r) {
        return s.replace(f, r);
    }


    public static String[] match(String s, String p) {
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile(p)
                .matcher(s);
        while (m.find()) {
            allMatches.add(m.group());
        }
        return (String[]) allMatches.toArray();
    }

    // Parsing and splitting

    public static int toNumber(Object o) {
        return (int) o;
    }

    public static String[] split(String s, String sep) {
        return s.split(sep);
    }

    public static String[] splitByLengths(String s, int... numbers) {
        List<String> output = new ArrayList<>();
        int i = 0;
        for (int n : numbers) {
            output.add(s.substring(i, i + n));
            i += n;
        }
        return output.toArray(new String[0]);
    }

    /**
     * Returns the array of strings obtained by splitting s by the separator sep. Handles quotes properly.
     * Guesses tab or comma separator if sep is not given.
     * Also, value.escape('javascript') is useful for previewing unprintable chars prior to using smartSplit.
     */
    public static String[] smartSplit(String s) {
        String sep;
        if (StringUtils.countMatches(s, "\t") < StringUtils.countMatches(s, ",")) {
            sep = ",";
        } else {
            sep = "\t";
        }
        return smartSplit(s, sep);
    }

    public static String[] smartSplit(String s, String sep) {
        return s.split(sep);
    }

    public static String[] splitByCharType(String value) {
        return StringUtils.splitByCharacterType(value);
    }

    public static String[] _partition(String s, String frag, boolean omitFragment, boolean last) {
        List<String> output = new ArrayList<>();
        int offset = 0;
        int index;
        if (last) {
            index = s.indexOf(frag);
        } else {
            index = s.lastIndexOf(frag);
        }
        if (index == -1) {
            return new String[]{s, "", ""};
        }
        output.add(s.substring(0, index));
        if (! omitFragment) {
            output.add(frag);
            offset += frag.length();
        }
        output.add(s.substring(index + offset));
        return output.toArray(new String[0]);
    }

    public static String[] partition(String s, String frag) {
        return partition(s, frag, false);
    }

    public static String[] partition(String s, String frag, boolean omitFragment) {
        return _partition(s, frag, omitFragment, false);
    }

    public static String[] rpartition(String s, String frag) {
        return rpartition(s, frag, false);
    }

    public static String[] rpartition(String s, String frag, boolean omitFragment) {
        return _partition(s, frag, omitFragment, true);
    }

    // Encoding and Hashing

    /**
     * For strings, returns the portion where they differ. For dates, it returns the difference in given time units.
     * @param o1
     * @param o2
     * @return
     */
    public static String diff(String o1, String o2) {
        return StringUtils.difference(o1, o2);
    }

    public static String escape(String valueParameter, String modeParameter) {
        String mode = modeParameter.toLowerCase();
        switch (mode) {
            case "html":
                return StringEscapeUtils.escapeHtml(valueParameter);
            case "xml":
                return StringEscapeUtils.escapeXml(valueParameter);
            case "csv":
                return StringEscapeUtils.escapeCsv(valueParameter);
            case "url":
                return encodeURIComponent(valueParameter);
            case "javascript":
                return StringEscapeUtils.escapeJavaScript(valueParameter);
        }
        return valueParameter;
    }

    public static String unescape(String valueParameter, String modeParameter) {
        String mode = modeParameter.toLowerCase();
        switch (mode) {
            case "html":
                return StringEscapeUtils.unescapeHtml(valueParameter);
            case "xml":
                return StringEscapeUtils.unescapeXml(valueParameter);
            case "csv":
                return StringEscapeUtils.unescapeCsv(valueParameter);
            case "url":
                return decodeURIComponent(valueParameter);
            case "javascript":
                return StringEscapeUtils.unescapeJavaScript(valueParameter);
        }
        return valueParameter;
    }

    public static String md5(String s) {
        return new String(DigestUtils.md5(s));
    }

    public static String sh1(String s) {
        return new String(DigestUtils.sha1(s));
    }

    public static String phonetic(String s, String mode) throws EncoderException {
        Encoder encoder;
        switch (mode) {
            case "doublemetaphone":
                encoder = new DoubleMetaphone();
                break;
            case "metaphone":
                encoder = new Metaphone();
            case "metaphone3":
                // TODO Find Metaphone 3
                encoder = new Metaphone();
            case "soundex":
                encoder = new Soundex();
            case "cologne":
                encoder = new ColognePhonetic();
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return encoder.encode(s).toString();
    }

    public static String reinterpret(String s, String encoder) {
        return new String(s.getBytes(), Charset.forName(encoder));
    }

    /**
     * TODO what's a fingerprint?
     * Returns the fingerprint of s, a derived string that aims to be a more canonical form of it (this is mostly useful for finding clusters of strings related to the same information).
     */
    public static String fingerprint(String s) {
        return s;
    }

    // TODO
    public static String[] ngram(String s, int n) {
        return new String[0];
    }

    // TODO
    public static String[] ngramFingerprint(String s, int n) {
        return new String[0];
    }

    public static String[] unicode(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .map(c -> encodeURIComponent(String.valueOf(c)))
                .toArray(String[]::new);
    }

    public static String[] unicodeType(String s) {
        return unicode(s);
    }

    // mqlKeyQuote
    // mqlKeyUnquote

    

    public static boolean isSet(String valueParameter) {
        return !StringUtils.isEmpty(valueParameter);
    }



    public static boolean booleanMatch(String valueParameter, String regexParameter) {
        return valueParameter.matches(regexParameter);
    }

    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

    private static String encodeURIComponent(String input) {
        if (StringUtils.isEmpty(input)) {
            return input;
        }

        int l = input.length();
        StringBuilder o = new StringBuilder(l * 3);
        try {
            for (int i = 0; i < l; i++) {
                String e = input.substring(i, i + 1);
                if (!ALLOWED_CHARS.contains(e)) {
                    byte[] b = e.getBytes("utf-8");
                    o.append(getHex(b));
                    continue;
                }
                o.append(e);
            }
            return o.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return input;
    }

    private static String getHex(byte buf[]) {
        StringBuilder o = new StringBuilder(buf.length * 3);
        for (int i = 0; i < buf.length; i++) {
            int n = (int) buf[i] & 0xff;
            o.append("%");
            if (n < 0x10) {
                o.append("0");
            }
            o.append(Long.toString(n, 16).toUpperCase());
        }
        return o.toString();
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
