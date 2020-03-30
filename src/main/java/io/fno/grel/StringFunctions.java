package io.fno.grel;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class StringFunctions {

    /**
     * Returns the length of `s` as a number.
     *
     * @param s string
     * @return length
     */
    public static Integer length(String s) {
        return s.length();
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#tostringvalue

    /**
     * Returns boolean indicating whether `s` starts with `sub`.
     * For example, `startsWith("food", "foo")` returns `true`, whereas `startsWith("food", "bar")` returns `false`.
     *
     * @param s   string
     * @param sub prefix
     * @return boolean
     */
    public static Boolean startsWith(String s, String sub) {
        return s.startsWith(sub);
    }

    /**
     * Returns boolean indicating whether `s` ends with `sub`.
     * For example, `endsWith("food", "ood")` returns `true`, whereas `endsWith("food", "odd")` returns `false`.
     *
     * @param s   string
     * @param sub suffix
     * @return boolean
     */
    public static Boolean endsWith(String s, String sub) {
        return s.endsWith(sub);
    }

    /**
     * Returns boolean indicating whether s ends with sub.
     * For example, endsWith("food", "ood") returns true, whereas endsWith("food", "odd") returns false.
     * You could also write the first case as "food".endsWith("ood").
     * @param s
     * @param sub
     * @return
     */
    public static Boolean contains(String s, String sub) {
        return s.contains(sub);
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#indexofstring-s-string-sub

    /**
     * Returns `s` converted to lowercase.
     *
     * @param s string
     * @return lowercase
     */
    public static String toLowercase(String s) {
        return s.toLowerCase();
    }

    /**
     * Returns `s` converted to uppercase.
     *
     * @param s string
     * @return uppercase
     */
    public static String toUppercase(String s) {
        return s.toUpperCase();
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#totitlecasestring-s

    /**
     * Returns a copy of the string, with leading and trailing whitespace removed.
     * For example, `trim(" island ")` returns the string `island`.
     *
     * @param s string
     * @return a copy of the string, with leading and trailing whitespace removed
     */
    public static String trim(String s) {
        return s.trim();
    }

    // TODO strip sameas trim

    /**
     * Returns a copy of s with sep removed from the end if s ends with sep; otherwise, just returns s.
     * For example, chomp("hardly", "ly") and chomp("hard", "ly") both return the string hard.
     * https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#chompstring-s-string-sep
     *
     * @param s   string
     * @param sep sep
     * @return a copy of s with sep removed from the end if s ends with sep; otherwise, just returns s
     */
    public static String chomp(String s, String sep) {
        return StringUtils.chomp(s, sep);
    }

    // TODO fn-xpath:func-substring .

    /**
     * Returns the substring of `s` starting from character index `from` upto the end of the string `s`.
     * For example, `substring("profound", 3)` returns the string `found`.
     * <p>
     * Character indexes start from zero.
     *
     * @param s    string
     * @param from character index from
     * @return substring
     */
    public static String substring(String s, Integer from) {
        return s.substring(from);
    }

    /**
     * Returns the substring of `s` starting from character index `from` and upto character index `to`.
     * For example, `substring("profound", 2, 4)` returns the string `of`.
     * <p>
     * Character indexes start from zero.
     * Negative character indexes are understood as counting from the end of the string.
     * For example, `substring("profound", 1, -1)` returns the string `rofoun`.
     *
     * @param s    string
     * @param from character index from
     * @param to   character index upto
     * @return substring
     */
    public static String substring(String s, Integer from, Integer to) {
        return s.substring(from, to);
    }

    // TODO slice sameAs substring

    // TODO get sameAs substring

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#indexofstring-s-string-sub-1

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#lastindexofstring-s-string-sub

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#replacestring-s-string-f-string-r

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#replacecharsstring-s-string-f-string-r

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#matchstring-s-regexp-p

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#findstring-s-regexp-p

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#tonumbero

    /**
     * Returns the array of strings obtained by splitting `s` at wherever `sep` is found in it.
     * `sep` can be either a string or a regular expression.
     * For example, `split("fire, water, earth, air", ",")` returns the array of 4 strings:
     * "fire", " water", " earth" , and " air".
     * The double quotation marks are shown here only to highlight the fact that the spaces are retained.
     *
     * @param s   string
     * @param sep separator
     * @return the array of strings obtained by splitting `s` at wherever `sep` is found in it
     */
    public static List<String> split(String s, String sep) {
        return Arrays.asList(s.split(sep));
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#splitbylengthsstring-s-number-n1-number-n2-

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#smartsplitstring-s-optional-string-sep

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#splitbychartypes

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#partitionstring-s-string-or-regex-frag-optional-boolean-omitfragment

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#rpartitionstring-s-string-or-regex-frag-optional-boolean-omitfragment

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#diffo1-o2-optional-string-timeunit

    /**
     * Escapes `s` in the given escaping mode: `html`, `xml`, `csv`, `url`, `javascript`.
     *
     * @param s    string
     * @param mode mode
     * @return escaped
     */
    public static String escape(String s, String mode) {
        String lMode = mode.toLowerCase();
        switch (lMode) {
            case "html":
                return StringEscapeUtils.escapeHtml(s);
            case "xml":
                return StringEscapeUtils.escapeXml(s);
            case "csv":
                return StringEscapeUtils.escapeCsv(s);
            case "url":
                return encodeURIComponent(s);
            case "javascript":
                return StringEscapeUtils.escapeJavaScript(s);
        }
        return s;
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#unescapestring-s-string-mode

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#md5string-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#sha1string-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#phoneticstring-s-string-encoding

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#reinterpretstring-s-string-encoder

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#fingerprintstring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#ngramstring-s-number-n

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#ngramfingerprintstring-s-number-n

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#unicodestring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#unicodetypestring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#mqlkeyquotestring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#mqlkeyunquotestring-key

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
                    Byte[] b = ArrayUtils.toObject(e.getBytes("utf-8"));
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

    private static String getHex(Byte[] buf) {
        StringBuilder o = new StringBuilder(buf.length * 3);
        for (byte b : buf) {
            int n = (int) b & 0xff;
            o.append("%");
            if (n < 0x10) {
                o.append("0");
            }
            o.append(Long.toString(n, 16).toUpperCase());
        }
        return o.toString();
    }

}
