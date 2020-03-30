package io.fno.grel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringFunctions_Test {
    @Test
    public void length() {
        String input = "one";
        Integer output = StringFunctions.length(input);
        assertEquals(new Integer(3), output);
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
    public void trim() {
        String input = " Ones   ";
        String output = StringFunctions.trim(input);
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
    public void testSubstring() {
        String input = "Ones";
        String output = StringFunctions.substring(input, 1, 3);
        assertEquals("ne", output);
    }

    @Test
    public void split() {
        String input = "Ones";
        List output = StringFunctions.split(input, "ne");
        assertEquals(2, output.size());
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
}
