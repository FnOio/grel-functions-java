package io.fno.grel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanFunctionsTest {
    @Test
    public void and() {
        List<Boolean> input = new ArrayList<>();
        input.add(true);
        input.add(false);

        Boolean output = BooleanFunctions.and(input);
        assertFalse(output);
    }

    @Test
    public void or() {
        List<Boolean> input = new ArrayList<>();
        input.add(true);
        input.add(false);

        Boolean output = BooleanFunctions.or(input);
        assertTrue(output);
    }

    @Test
    public void not() {
        Boolean output = BooleanFunctions.not(false);
        assertTrue(output);
    }

    @Test
    public void xorFalse() {
        List<Boolean> input = new ArrayList<>();
        input.add(true);
        input.add(true);

        Boolean output = BooleanFunctions.xor(input);
        assertFalse(output);
    }

    @Test
    public void xorTrue() {
        List<Boolean> input = new ArrayList<>();
        input.add(true);
        input.add(false);

        Boolean output = BooleanFunctions.xor(input);
        assertTrue(output);
    }
}
