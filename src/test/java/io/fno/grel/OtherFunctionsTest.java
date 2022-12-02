package io.fno.grel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>Copyright 2022 IDLab (Ghent University - imec)</p>
 *
 * @author Gerald Haesendonck
 */
public class OtherFunctionsTest {

    @Test
    public void testTypePrimitive() {
        boolean input = true;
        String expected = "boolean";
        String result = OtherFunctions.type(input);
        assertEquals(expected, result);
    }

    @Test
    public void testTypeBoxed() {
        Double input = 5.45d;
        String expected = "double";
        String result = OtherFunctions.type(input);
        assertEquals(expected, result);
    }

    @Test
    public void testHasField() {
        Integer five = 5;
        boolean hasField = OtherFunctions.hasField(five, "MIN_VALUE");
        assertTrue(hasField);
        boolean hasNoField = OtherFunctions.hasField(five, "Shrek");
        assertFalse(hasNoField);
    }
}
