package io.fno.grel;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.WordUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
     * Takes any value type (string, number, date, boolean, error, null) and gives a string version of that value.
     * This is the same as invoking {@link StringFunctions#toString(Object, String)} where {@code format == null}.
     * @param valueParameter    The object to render as a String
     * @return                  The {@code valueParameter} object rendered as a String.
     */
    public static String toString(Object valueParameter) {
        return toString(valueParameter, null);
    }
    /**
     * Takes any value type (string, number, date, boolean, error, null) and gives a string version of that value.
     * <a href="https://docs.openrefine.org/manual/grelfunctions#tostringo-string-format-optional">toString</a>
     * @param valueParameter The object to render as a String. If {@code null}, The String {@code "null"} is returned.
     * @param format The format to apply.
     *               If {@code valueParameter} is a Date, then the format is expected to be a {@link java.text.SimpleDateFormat}.
     *               For other objects its {@code toString} method is invoked and then applied with
     *               a {@link java.util.Formatter}.
     *               If {@code fomat} is {@code null} then the default is used for the given {@code valueParameter}.
     * @return The {@code valueParameter} object rendered as a String according to the given format.
     * @throws IllegalFormatException  If a format string contains an illegal syntax, a format specifier that is
     * incompatible with the given arguments, insufficient arguments given the format string, or other illegal
     * conditions. For specification of all possible formatting errors, see the Details section of the formatter
     * class specification.
     */
    public static String toString(Object valueParameter, String format) {
        if (valueParameter == null) {
            return "null";
        } else {
            if (valueParameter instanceof Date) {
                return DateFunctions.toString((Date) valueParameter, format);
            } else {
                if (format == null) {
                    return valueParameter.toString();
                } else {
                    return String.format(format, valueParameter);
                }
            }
        }
    }

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
     * Returns a boolean indicating whether s contains sub, which is either a substring or a regex pattern. For example,
     * "food".contains("oo") returns true whereas "food".contains("ee") returns false.
     * TODO: the regex part
     *
     * @param s     string
     * @param sub   substring
     * @return      {@code true} if {@code s} contains {@code sub}.
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

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions/#totitlecases">toTitlecase</a>
     * <br>
     * Returns string s converted into titlecase: a capital letter starting each word, and the rest of the letters lowercase.
     *
     * @param s The string to convert into title case
     * @return capitalized string
     */
    public static String toTitlecase(String s) {
        return WordUtils.capitalizeFully(s);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#trims">trim</a>
     * <br>
     * Returns a copy of the string, with leading and trailing whitespace removed.
     * For example, `trim(" island ")` returns the string `island`.
     *
     * @param s string
     * @return a copy of the string, with leading and trailing whitespace removed
     */
    public static String trim(String s) {
        return s.trim();
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#strips">strip</a>
     * <br>
     * Returns a copy of the string s with leading and trailing whitespace removed.
     * For example, " island ".strip() returns the string “island”. Identical to trim().
     * @param s string
     * @return a copy of the string s with leading and trailing whitespace removed
     */
    public static String strip(String s) {
        return trim(s);
    }

    /**
     * Returns a copy of s with sep removed from the end if s ends with sep; otherwise, just returns s.
     * For example, chomp("hardly", "ly") and chomp("hard", "ly") both return the string hard.
     * <a href="https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#chompstring-s-string-sep">chomp</a>
     *
     * @param s   string
     * @param sep sep
     * @return a copy of s with sep removed from the end if s ends with sep; otherwise, just returns s
     */
    public static String chomp(String s, String sep) {
        return StringUtils.removeEnd(s, sep);
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

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#slices-n-from-n-to-optional">slice</a>
     * <br>
     * Identical to substring() in relation to strings.
     * @param s    string
     * @param from character index from
     * @param to   character index upto
     * @return substring
     */
    public static String slice(String s, Integer from, Integer to) {
        return substring(s, from ,to);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#slices-n-from-n-to-optional">slice</a>
     * <br>
     * Identical to substring() in relation to strings.
     * @param s    string
     * @param from character index from
     * @return substring
     */
    public static String slice(String s, Integer from) {
        return substring(s, from);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#slices-n-from-n-to-optional">get</a>
     * Identical to substring() in relation to strings.
     * @param s    string
     * @param from character index from
     * @param to   character index upto
     * @return substring
     */
    public static String get(String s, Integer from, Integer to) {
        return substring(s, from ,to);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#slices-n-from-n-to-optional">get</a>
     * <br>
     * Identical to substring() in relation to strings.
     * @param s    string
     * @param from character index from
     * @return substring
     */
    public static String get(String s, Integer from) {
        return substring(s, from);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#indexofs-sub">indexOf</a>
     * <br>
     * Returns the first character index of sub as it first occurs in s; or, returns -1 if s does not contain sub.
     * @param s     The string to find {@code sub} in.
     * @param sub   The substring to search for in {@code s}
     * @return      the first character index of {@code sub} as it first occurs in {@code s};
     *              or, returns {@code -1} if {@code s} does not contain {@code sub}.
     */
    public static Integer indexOf(String s, String sub) {
        return s.indexOf(sub);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions/#lastindexofs-sub">lastindexof</a>
     * <br>
     * Returns the last character index of sub as it last occurs in s; or, returns -1 if s does not contain sub.
     *
     * @param s     The string to search {@code sub} for.
     * @param sub   The substring to search for in {@code s}.
     * @return      the last character index of {@code sub} as it first occurs in {@code s};
     *              or, returns {@code -1} if {@code s} does not contain {@code sub}.
     */
    public static Integer lastIndexOf(String s, String sub) {
        return s.lastIndexOf(sub);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#replaces-s-or-p-find-s-replace">replace</a>
     * <br>
     * Returns the string obtained by replacing the {@code find} string with the {@code replace} string
     * in the input string.
     * @param s string to replace in
     * @param find target substring or regular expression to replace. Strings starting and ending with {@code /} are
     *             concidered regular expressions, e.g. "/\\s+/".
     * @param replace string to replace target substring or regular expression matches with
     * @return s with substring f replaced by string r
     * @throws PatternSyntaxException – if the regular expression's syntax is invalid
     */
    public static String replace(String s, String find, String replace) {
        if (find.startsWith("/") && find.endsWith("/")) {
            // then f is a regex!
            String regex = find.substring(1, find.length() - 1);
            return s.replaceAll(regex, replace);
        } else {
            // f is an ordinary String
            return s.replace(find, replace);
        }
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#replacecharss-s-find-s-replace">replaceChars</a>
     * <br>
     * Returns the string obtained by replacing a character in s, identified by find,
     * with the corresponding character identified in replace.
     * You cannot use this to replace a single character with more than one character.
     * <br>
     * For example, replaceChars("Téxt thát was optícálly recógnízéd", "áéíóú", "aeiou")
     * returns the string “Text that was optically recognized”.
     * @param s The string to search and replace characters in
     * @param f A string containing all the chars to replace
     * @param r A string containing all the chars to replace with. The ordering should be
     *          matched with the ordering in argument f
     * @throws Exception when the string of replacement chars is shorter than the string of
     *         characters to replace.
     */
    public static String replaceChars(String s, String f, String r) throws Exception {
        if (f.length() != r.length()) {
            throw new Exception("You must provide as many replacement characters as target characters.");
        }
        for (int i=0; i<f.length(); i++) {
            char find = f.charAt(i);
            char replace = r.charAt(i);
            s = s.replace(find, replace);
        }
        return s;
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#matchs-p">match</a>
     * <br>
     * Attempts to match the string s in its entirety against the regex pattern p and,
     * if the pattern is found, outputs an array of all capturing groups (found in order).
     * @param s string
     * @param p regex pattern
     * @return Array of pattern matches
     */
    public static String[] match(String s, String p) {
        if (p.length() < 2 || !p.startsWith("/") || !p.endsWith("/")) {
            throw new InvalidParameterException("A regex in GREL must start and end with '/'");
        }
        String pattern = "^" + p.substring(1, p.length() - 1) + "$";    // GREL seems to implicitly add begin and end markers
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile(pattern)
                .matcher(s);

        int nrGroups = m.groupCount();
        if (m.find()) {
            for (int i = 1; i <= nrGroups; i++) {
                allMatches.add(m.group(i)); // group(0) is always the complete input string if there's a match!
            }
            return allMatches.toArray(new String[]{});
        } else {
            return null;
        }
    }

    /**
     * Returns a string converted to a number. Will attempt to convert other formats into a string,
     * then into a number. If the value is already a number, it will return the number.
     * <a href="https://docs.openrefine.org/manual/grelfunctions#tonumbers">toNumber</a>
     * @param o the object to turn into a Number
     * @return A Number represented by the {@code o}.
     * @throws NumberFormatException    if o.toString() is not a valid representation of a BigDecimal.
     *
     */
    public static Number toNumber(Object o) {
        if (o instanceof Number) {
            return (Number)o;
        } else {
            return new BigDecimal(o.toString());
        }
    }

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
    public static String[] split(String s, String sep) {
        return s.split(sep);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#splitbylengthss-n1-n2-">splitByLength</a>
     * <br>
     * Returns the array of strings obtained by splitting s into substrings with the given
     * lengths. For example, "internationalization".splitByLengths(5, 6, 3) returns an array
     * of 3 strings: [ "inter", "nation", "ali" ]. Excess characters are discarded.
     * @param s string
     * @param numbers lengths of subsequent substrings to be extracted
     * @return Array of strings after splitting
     */
    public static String[] splitByLengths(String s, int... numbers) {
        List<String> output = new ArrayList<>();
        int i = 0;
        for (int n : numbers) {
            output.add(s.substring(i, i + n));
            i += n;
        }
        return output.toArray(new String[0]);
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#splitbylengthsstring-s-number-n1-number-n2-

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#smartsplits-s-or-p-sep-optional">smartSplit</a>
     * Returns the array of strings obtained by splitting s by the separator sep.
     * Guesses tab or comma separator if sep is not given.
     * Also, value.escape('javascript') is useful for previewing unprintable chars prior to using smartSplit.
     * TODO: this is not "smart" enough
     * @param s The input string to split
     * @return An array where each element is a part of the split input string.
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

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#smartsplits-s-or-p-sep-optional">smartSplit</a>
     * Returns the array of strings obtained by splitting s by the separator sep.
     * Guesses tab or comma separator if sep is not given.
     * Also, value.escape('javascript') is useful for previewing unprintable chars prior to using smartSplit.
     * TODO: this is not "smart" enough
     * @param s The input string to split
     * @param sep The separator. {@code /.../} is concidered a regex.
     * @return An array where each element is a part of the split input string.
     */
    public static String[] smartSplit(String s, String sep) {
        if (sep.startsWith("/") && sep.endsWith("/")) {
            // then we have a regex!
            String pattern = sep.substring(1, sep.length() - 1);
            return s.split(pattern);
        } else {
            return StringUtils.split(s, sep);
        }
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#splitbychartypes">splitByCharType</a>
     * <br>
     * Returns an array of strings obtained by splitting s into groups of consecutive characters
     * each time the characters change Unicode categories. For example, "HenryCTaylor".splitByCharType()
     * will result in an array of [ "H", "enry", "CT", "aylor" ]. It is useful for separating letters
     * and numbers: "BE1A3E".splitByCharType() will result in [ "BE", "1", "A", "3", "E" ].
     *
     * @param value The string to split by character type
     * @return An array where each element is a part of the split input string.
     */
    public static String[] splitByCharType(String value) {
        return StringUtils.splitByCharacterType(value);
    }

    public static String[] _partition(String s, String frag, Boolean omitFragment, Boolean last) {
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

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#partitions-s-or-p-fragment-b-omitfragment-optional">partition</a>
     * <br>
     * Returns an array of strings [ a, fragment, z ] where a is the substring within s before the
     * first occurrence of fragment, and z is the substring after fragment.
     * Fragment cannot be a regex at this moment.
     * <br>
     * For example, "internationalization".partition("nation") returns 3 strings:
     * [ "inter", "nation", "alization" ]. If s does not contain fragment, it returns an array of
     * [ s, "", "" ] (the original unpartitioned string, and two empty strings).
     * @param s The string to paratition
     * @param frag The fragment
     * @return  An array where each element is a part of the input string.
     */
    public static String[] partition(String s, String frag) {
        return partition(s, frag, false);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#partitions-s-or-p-fragment-b-omitfragment-optional">partition</a>
     * Returns an array of strings [ a, fragment, z ] where a is the substring within s before the
     * first occurrence of fragment, and z is the substring after fragment. Fragment cannot be a regex at this moment.
     * <br>
     * For example, "internationalization".partition("nation") returns 3 strings:
     * [ "inter", "nation", "alization" ]. If s does not contain fragment, it returns an array of
     * [ s, "", "" ] (the original unpartitioned string, and two empty strings).
     * <br>
     * If the omitFragment boolean is true, for example with "internationalization".partition("nation", true),
     * the fragment is not returned. The output is [ "inter", "alization" ].
     * @param s The string to paratition
     * @param frag The fragment
     * @param omitFragment {@code true} if the fragment is not to be included in the output
     * @return  An array where each element is a part of the input string.
     */
    public static String[] partition(String s, String frag, Boolean omitFragment) {
        return _partition(s, frag, omitFragment, false);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#rpartitions-s-or-p-fragment-b-omitfragment-optional">rpartition</a>
     * <br>
     * Returns an array of strings [ a, fragment, z ] where a is the substring within s before
     * the last occurrence of fragment, and z is the substring after the last instance of fragment.
     * (Rpartition means “reverse partition.”)
     * <br>
     * For example, "parallel".rpartition("a") returns 3 strings:
     * [ "par", "a", "llel" ]. Otherwise works identically to partition().
     * @param s The string to paratition
     * @param frag The fragment
     * @return  An array where each element is a part of the input string.
     */
    public static String[] rpartition(String s, String frag) {
        return rpartition(s, frag, false);
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#rpartitions-s-or-p-fragment-b-omitfragment-optional">rpartition</a>
     * <br>
     * Returns an array of strings [ a, fragment, z ] where a is the substring within s before
     * the last occurrence of fragment, and z is the substring after the last instance of fragment.
     * (Rpartition means “reverse partition.”)
     * <br>
     * For example, "parallel".rpartition("a") returns 3 strings:
     * [ "par", "a", "llel" ]. Otherwise works identically to partition().
     * @param s The string to paratition
     * @param frag The fragment
     * @param omitFragment {@code true} if the fragment is not to be included in the output
     * @return  An array where each element is a part of the input string.
     */
    public static String[] rpartition(String s, String frag, Boolean omitFragment) {
        return _partition(s, frag, omitFragment, true);
    }


    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#diffs1-s2-s-timeunit-optional">diff</a>
     * <br>
     * Takes two strings and compares them, returning a string. Returns the remainder
     * of s2 starting with the first character where they differ.
     * <br>
     * For example, diff("cacti", "cactus") returns "us".
     * @param o1 The first string to compare
     * @param o2 The second string to compare
     * @return The difference between the two strings
     */
    public static String diff(String o1, String o2) {
        return StringUtils.difference(o1, o2);
    }

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
                return StringEscapeUtils.escapeHtml4(s);
            case "xml":
                return StringEscapeUtils.escapeXml11(s);
            case "csv":
                return StringEscapeUtils.escapeCsv(s);
            case "url":
                return encodeURIComponent(s);
            case "javascript":
                return StringEscapeUtils.escapeEcmaScript(s);
        }
        return s;
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#unescapes-s-mode">unescape</a>
     * <br>
     * Unescapes s in the given escaping mode. The mode can be one of: "html", "xml",
     * "csv", "url", "javascript". Note that quotes are required around your mode.
     * @param valueParameter    The string to unescape
     * @param modeParameter     The mode {@code valueParameter} is escaped in
     * @return  The unescaped string
     */
    public static String unescape(String valueParameter, String modeParameter) {
        String mode = modeParameter.toLowerCase();
        switch (mode) {
            case "html":
                return StringEscapeUtils.unescapeHtml4(valueParameter);
            case "xml":
                return StringEscapeUtils.unescapeXml(valueParameter);
            case "csv":
                return StringEscapeUtils.unescapeCsv(valueParameter);
            case "url":
                return decodeURIComponent(valueParameter);
            case "javascript":
                return StringEscapeUtils.unescapeEcmaScript(valueParameter);
        }
        return valueParameter;
    }

    /**
     * Returns the MD5 hash of an object. If fed something other than a string (array, number, date, etc.), md5() will convert it to a string and deliver the hash of the string.
     *
     * @param s The object to take the MD5 hash from
     * @return  The MD5 hash of {@code s}
     */
    public static String md5(Object s) {
        return DigestUtils.md5Hex(s.toString());
    }

    /**
     * Returns the SHA-1 hash of an object. If fed something other than a string (array, number, date, etc.), sha1() will convert it to a string and deliver the hash of the string.
     *
     * @param s The object to take the SHA-1 hash from
     * @return  The SHA-1 hash of {@code s}
     */
    public static String sha1(Object s) {
        return DigestUtils.sha1Hex(s.toString());
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#phoneticstring-s-string-encoding

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#phonetics-s-encoding">phonectic</a>
     * <br>
     * Returns a phonetic encoding of a string, based on an available phonetic algorithm.
     * Can be one of the following supported phonetic methods: metaphone, doublemetaphone, metaphone3, soundex, cologne.
     *
     * @param s string to encode
     * @param mode "doublemetaphone", "metaphone", "metaphone3", "soundex", or "cologne"
     * @return encoded string
     * @throws EncoderException When encoding {@code s} fails.
     */
    public static String phonetic(String s, String mode) throws EncoderException {
        Encoder encoder;
        switch (mode) {
            case "doublemetaphone":
                DoubleMetaphone dEncoder = new DoubleMetaphone();
                dEncoder.setMaxCodeLen(s.length() * 2);
                encoder = dEncoder;
                break;
            case "metaphone":
            case "metaphone3":
                // TODO Find Metaphone 3
                Metaphone mEnc = new Metaphone();
                mEnc.setMaxCodeLen(s.length());
                encoder = mEnc;
                break;
            case "soundex":
                encoder = new Soundex();
                break;
            case "cologne":
                encoder = new ColognePhonetic();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return encoder.encode(s).toString();
    }

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#reinterprets-s-encodertarget-s-encodersource">reinterpret</a>
     * <br>
     * Returns s reinterpreted through the given character encoders. You must supply one of the
     * <a href="https://docs.oracle.com/en/java/javase/11/intl/supported-encodings.html#GUID-187BA718-195F-4C39-B0D5-F3FDF02C7205">supported encodings</a>
     * for each of the original source and the target output.
     *
     * @param s The string to reinterpret
     * @param encoderTarget The target encoding of {@code s} (or how we want {@code s} to be encoded)
     * @param encoderSource Source encoding of {@code s} (or how the bytes of {@code s} are actually to be interpreted)
     * @return A new String with the right value and encoding
     */
    public static String reinterpret(String s, String encoderTarget, String encoderSource) throws UnsupportedEncodingException {
        byte[] sourceBytes = s.getBytes(encoderSource);
        return new String(sourceBytes, encoderTarget);
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#fingerprintstring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#ngramstring-s-number-n

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#ngramfingerprintstring-s-number-n

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#unicodes">unicode</a>
     * Returns an array of ints describing each character of s in their full unicode notation.
     * For example, "Bernice Rubens".unicode() outputs
     * [ 66, 101, 114, 110, 105, 99, 101, 32, 82, 117, 98, 101, 110, 115 ].
     * @param s The string to represent as unicode notation
     * @return  An array of integers representing unicode code points of string {@code s}.
     */
    public static int[] unicode(String s) {
        if (s == null) {
            return null;
        }

        int[] output = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            output[i] = s.codePointAt(i);
        }
        return output;
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#unicodetypestring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#mqlkeyquotestring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#mqlkeyunquotestring-key

    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

    private static String encodeURIComponent(String input) {
        if (input == null) {
            return null;
        }

        if (StringUtils.isEmpty(input)) {
            return input;
        }

        int l = input.length();
        StringBuilder o = new StringBuilder(l * 3);
        for (int i = 0; i < l; i++) {
            String e = input.substring(i, i + 1);
            if (!ALLOWED_CHARS.contains(e)) {
                Byte[] b = ArrayUtils.toObject(e.getBytes(StandardCharsets.UTF_8));
                o.append(getHex(b));
                continue;
            }
            o.append(e);
        }
        return o.toString();
    }

    private static String decodeURIComponent(String encodedURI) {
        char actualChar;

        StringBuilder buffer = new StringBuilder();

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
