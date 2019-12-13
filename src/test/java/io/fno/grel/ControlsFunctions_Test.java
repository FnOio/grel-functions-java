package io.fno.grel;

import org.junit.Test;
import static org.junit.Assert.*;

public class ControlsFunctions_Test {
    @Test
    public void ifThenElse() {
        String one = "one";
        String two = "two";
        String out = (String) ControlsFunctions.ifThenElse(true, one, two);
        assertEquals(out, one);
    }
}
