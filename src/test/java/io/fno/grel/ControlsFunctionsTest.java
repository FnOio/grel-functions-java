package io.fno.grel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    public void ifThenWithoutElse() {
        String one = "one";
        assertEquals(one, ControlsFunctions.ifThenElse(true, one));
        assertNull(ControlsFunctions.ifThenElse(false, one));
    }
}
