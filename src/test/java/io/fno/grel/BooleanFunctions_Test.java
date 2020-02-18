package io.fno.grel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BooleanFunctions_Test {
    @Test
    public void and() {
        List<Boolean> input = new ArrayList<>();
        input.add(true);
        input.add(false);

        boolean output = BooleanFunctions.and(input);
        assertFalse(output);
    }

    @Test
    public void or() {
        List<Boolean> input = new ArrayList<>();
        input.add(true);
        input.add(false);

        boolean output = BooleanFunctions.or(input);
        assertTrue(output);
    }

    @Test
    public void not() {
        boolean output = BooleanFunctions.not(false);
        assertTrue(output);
    }

    @Test
    public void xor() {
        List<Boolean> input = new ArrayList<>();
        input.add(true);
        input.add(true);

        boolean output = BooleanFunctions.xor(input);
        assertFalse(output);
    }
}
