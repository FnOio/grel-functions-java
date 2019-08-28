package be.ugent.fno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrelFunctionsTest {

    @Test
    void andTest() {
        assertTrue(GrelFunctions.and(true, true, true, true));
        assertFalse(GrelFunctions.and(true, true, false, false));
    }

    @Test
    void orTest() {
        assertTrue(GrelFunctions.or(true, true, true, true));
        assertTrue(GrelFunctions.or(true, false, false, false));
        assertFalse(GrelFunctions.or(false, false, false, false));
    }

    @Test
    void norTest() {
        assertFalse(GrelFunctions.not(true));
        assertTrue(GrelFunctions.not(false));
    }

    @Test
    void xorTest() {
        assertFalse(GrelFunctions.xor(true, true, true, true));
        assertTrue(GrelFunctions.xor(false, false, true, false));
        assertFalse(GrelFunctions.xor(false, false, false, false));
    }

    @Test
    void splitByLengths() {
        assertArrayEquals(
                new String[]{"inter", "nation", "ali"},
                GrelFunctions.splitByLengths("internationalization", 5, 6, 3)
        );
    }

    @Test
    void partition() {
        assertArrayEquals(
                new String[]{"inter", "nation", "alization"},
                GrelFunctions.partition("internationalization", "nation")
        );
    }

    @Test
    void rpartition() {
        assertArrayEquals(
                new String[]{"par", "a", "llel"},
                GrelFunctions.partition("parallel", "a")
        );
    }

    @Test
    void rpartition_empty() {
        assertArrayEquals(
                new String[]{"lollipop", "", ""},
                GrelFunctions.partition("lollipop", "a")
        );
    }
}