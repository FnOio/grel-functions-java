package io.fno.grel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControlsFunctionsTest {
    @Test
    public void ifThenElse() {
        String one = "one";
        String two = "two";
        String out = (String) ControlsFunctions.ifThenElse(true, one, two);
        assertEquals(one, out);
        out = (String) ControlsFunctions.ifThenElse(false, one, two);
        assertEquals(two, out);
    }
}
