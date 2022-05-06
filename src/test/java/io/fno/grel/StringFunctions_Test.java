package io.fno.grel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StringFunctions_Test {
    @Test
    public void length() {
        String input = "one";
        Integer output = StringFunctions.length(input);
        assertEquals(Integer.valueOf(3), output);
    }

    @Test
    public void toStringTest() {
        Integer input = 5;
        String output = StringFunctions.toString(input);
        assertEquals("5", output);
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
    public void substring2() {
        String input = "Ones";
        String output = StringFunctions.substring(input, 1, 3);
        assertEquals("ne", output);
    }

    @Test
    public void slice() {
        String input = "Ones";
        String output = StringFunctions.slice(input, 1);
        assertEquals("nes", output);
    }

    @Test
    public void get() {
        String input = "Ones";
        String output = StringFunctions.slice(input, 1);
        assertEquals("nes", output);
    }

    @Test
    public void indexOf() {
        String input = "Ononones";
        Integer output = StringFunctions.indexOf(input, "on");
        assertSame(2, output);
    }

    @Test
    public void lastIndexOf() {
        String input = "Ononones";
        Integer output = StringFunctions.lastIndexOf(input, "on");
        assertSame(4, output);
    }

    @Test
    public void replace() {
        String input = "Ononones";
        String output = StringFunctions.replace(input, "no", "mi");
        assertEquals("Omimines", output);
    }

    @Test
    public void replaceChars() throws Exception {
        String input = "Ononones";
        String output = StringFunctions.replaceChars(input, "no", "mi");
        assertEquals("Omimimes", output);
    }

    @Test
    public void match() {
        String input = "Ononones";
        List<String> output = StringFunctions.match(input, "n.");
        assertArrayEquals(new String[] { "no", "no", "ne" }, output.toArray());
    }

    @Test
    public void toNumber() throws Exception {
        String input = "4";
        Integer output = StringFunctions.toNumber(input);
        assertEquals(Integer.valueOf(4), output);
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
                new String[] { "inter", "nation", "ali" },
                StringFunctions.splitByLengths("internationalization", 5, 6, 3).toArray());
    }

    // TODO smartSplit

    // TODO splitByCharType

    @Test
    public void partition() {
        assertArrayEquals(
                new String[] { "inter", "nation", "alization" },
                StringFunctions.partition("internationalization", "nation").toArray());
    }

    @Test
    public void rpartition() {
        assertArrayEquals(
                new String[] { "par", "a", "llel" },
                StringFunctions.partition("parallel", "a").toArray());
    }

    @Test
    public void rpartition_empty() {
        assertArrayEquals(
                new String[] { "lollipop", "", "" },
                StringFunctions.partition("lollipop", "a").toArray());
    }

    // TODO diff

    @Test
    public void escape() {
        String input = "On&es";
        String output = StringFunctions.escape(input, "html");
        assertEquals("On&amp;es", output);
        input = "On es";
        output = StringFunctions.escape(input, "url");
        assertEquals("On%20es", output);
    }

    // TODO unescape

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

    // TODO phonetic

    // TODO reinterpret

    // TODO unicode
}
