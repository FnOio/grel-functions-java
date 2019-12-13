package io.fno.grel;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

public class ArrayFunctions_Test {
    @Test
    public void join() {
        List<String> input = new ArrayList<>();
        input.add("one");
        input.add("two");

        String output = ArrayFunctions.join(input, " ");
        assertEquals("one two", output);
    }
}
