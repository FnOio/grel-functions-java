package io.fno.grel;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringFunctionsTest {
    @Test
    public void length() {
        String input = "one";
        Integer output = StringFunctions.length(input);
        assertEquals(Integer.valueOf(3), output);
    }

    @Test
    public void startsWith() {
        String input = "one";
        Boolean output = StringFunctions.startsWith(input, "on");
        assertTrue(output);
    }

    @Test
    public void endsWith() {
        String input = "one";
        Boolean output = StringFunctions.endsWith(input, "ne");
        assertTrue(output);
    }

    @Test
    public void contains() {
        String input = "ones";
        Boolean output = StringFunctions.contains(input, "ne");
        assertTrue(output);
    }

    @Test
    public void toLowercase() {
        String input = "Ones";
        String output = StringFunctions.toLowercase(input);
        assertEquals("ones", output);
    }

    @Test
    public void toUppercase() {
        String input = "Ones";
        String output = StringFunctions.toUppercase(input);
        assertEquals("ONES", output);
    }

    @Test
    public void toTitlecase() {
        String input = "Once upon a midnight DREARY";
        String output = StringFunctions.toTitlecase(input);
        assertEquals("Once Upon A Midnight Dreary", output);
    }

    @Test
    public void trim() {
        String input = " Ones   ";
        String output = StringFunctions.trim(input);
        assertEquals("Ones", output);
    }

    @Test
    public void strip() {
        String input = " Ones   ";
        String output = StringFunctions.strip(input);
        assertEquals("Ones", output);
    }


    @Test
    public void chomp() {
        assertEquals("hard", StringFunctions.chomp("hardly", "ly"));
        assertEquals("hard", StringFunctions.chomp("hard", "ly"));
    }

    @Test
    public void substring() {
        String input = "Ones";
        String output = StringFunctions.substring(input, 1);
        assertEquals("nes", output);
    }

    @Test
    public void slice() {
        String input = "Ones";
        String output = StringFunctions.slice(input, 1);
        assertEquals("nes", output);
    }

    @Test
    public void sliceFromTo() {
        String input = "Ones";
        String output = StringFunctions.slice(input, 1, 3);
        assertEquals("ne", output);
    }

    @Test
    public void get() {
        String input = "Ones";
        String output = StringFunctions.get(input, 1);
        assertEquals("nes", output);
    }

    @Test
    public void getFromTo() {
        String input = "Ones";
        String output = StringFunctions.get(input, 1, 3);
        assertEquals("ne", output);
    }

    @Test
    public void indexOf() {
        String input = "internationalization";
        int output = StringFunctions.indexOf(input, "nation");
        assertEquals(5, output);
    }

    @Test
    public void lastIndexOf() {
        String input = "Ononones";
        Integer output = StringFunctions.lastIndexOf(input, "on");
        assertSame(4, output);
    }

    @Test
    public void testSubstring() {
        String input = "Ones";
        String output = StringFunctions.substring(input, 1, 3);
        assertEquals("ne", output);
    }

    @Test
    public void split() {
        String input = "Ones";
        List<String> output = StringFunctions.split(input, "ne");
        assertEquals(2, output.size());
    }

    @Test
    public void splitByLengths() {
        assertArrayEquals(
                new String[]{"inter", "nation", "ali"},
                StringFunctions.splitByLengths("internationalization", 5, 6, 3)
        );
    }

    @Test
    public void partition() {
        assertArrayEquals(
                new String[]{"inter", "nation", "alization"},
                StringFunctions.partition("internationalization", "nation")
        );
    }

    @Test
    public void rpartition() {
        assertArrayEquals(
                new String[]{"par", "a", "llel"},
                StringFunctions.partition("parallel", "a")
        );
    }

    @Test
    public void rpartition_empty() {
        assertArrayEquals(
                new String[]{"lollipop", "", ""},
                StringFunctions.partition("lollipop", "a")
        );
    }

    @Test
    public void escape() {
        String input = "On&es";
        String output = StringFunctions.escape(input, "html");
        assertEquals("On&amp;es", output);
        input = "On es";
        output = StringFunctions.escape(input, "url");
        assertEquals("On%20es", output);
    }

    @Test
    public void md5() {
        String input = "One";
        String output = StringFunctions.md5(input);
        assertEquals("06c2cea18679d64399783748fa367bdd", output);
    }

    @Test
    public void sha1() {
        String input = "One";
        String output = StringFunctions.sha1(input);
        assertEquals("b58b5a8ced9db48b30e008b148004c1065ce53b1", output);
    }

    @Test
    public void testReplace() {
        String input = "The cow jumps over the moon and moos";
        String output = StringFunctions.replace(input, "oo", "ee");
        assertEquals("The cow jumps over the meen and mees", output);
    }

    @Test
    public void testReplaceChars() throws Exception {
        String input = "Téxt thát was optícálly recógnízéd";
        String output = StringFunctions.replaceChars(input, "áéíóú", "aeiou");
        String expected = "Text that was optically recognized";
        assertEquals(expected, output);
    }

    @Test
    public void testReplaceWithRegex() {
        String input = "The cow jumps over the moon and moos";
        String regex = "/\\s+/";
        String expected = "The_cow_jumps_over_the_moon_and_moos";
        String output = StringFunctions.replace(input, regex, "_");
        assertEquals(expected, output);
    }

    @Test
    public void testMatch1() {
        String input = "230.22398, 12.3480";
        String pattern = "/.*(\\d\\d\\d\\d)/";
        String[] output = StringFunctions.match(input, pattern);
        String[] expected = new String[]{"3480"};
        assertArrayEquals(expected, output);
    }

    @Test
    public void testMatch2() {
        String input = "230.22398, 12.3480";
        String pattern = "/(.*)(\\d\\d\\d\\d)/";
        String[] output = StringFunctions.match(input, pattern);
        String[] expected = new String[]{"230.22398, 12.", "3480"};
        assertArrayEquals(expected, output);
    }

    @Test
    public void testMatch3() {
        String input = "hello 123456 goodbye";
        String pattern = "/\\d{6}/";
        String[] output = StringFunctions.match(input, pattern);
        assertArrayEquals(null, output);
    }

    @Test
    public void testMatch4() {
        String input = "hello 123456 goodbye";
        String pattern = "/.*\\d{6}.*/";
        String[] output = StringFunctions.match(input, pattern);
        String[] expected = new String[]{};
        assertArrayEquals(expected, output);
    }

    @Test
    public void testMatch5() {
        String input = "hello 123456 goodbye";
        String pattern = "/.*(\\d{6}).*/";
        String[] output = StringFunctions.match(input, pattern);
        String[] expected = new String[]{"123456"};
        assertArrayEquals(expected, output);
    }

    @Test
    public void testMatch6() {
        String input = "hello 123456 goodbye";
        String pattern = "/(.*)(\\d{6})(.*)/";
        String[] output = StringFunctions.match(input, pattern);
        String[] expected = new String[]{"hello ", "123456", " goodbye"};
        assertArrayEquals(expected, output);
    }

    @Test
    public void testDefaultToStringString() {
        assertEquals("a string", StringFunctions.toString("a string"));
    }

    @Test
    public void testDefaultToStringNumber() {
        assertEquals("3", StringFunctions.toString(3));
    }

    @Test
    public void testToStringWithPatternNumber() {
        assertEquals("3", StringFunctions.toString(3.2f, "%.0f"));
    }

    @Test
    public void testToStringWithPatternDate() throws ParseException {
        String pattern = "yyyy-MM-dd";
        Date date = DateFunctions.toDate("2022-12-01", pattern);
        assertEquals("2022-12-01", StringFunctions.toString(date, pattern));
    }
}
