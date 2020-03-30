package io.fno.grel;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayFunctions_Test {

    @Test
    public void get() {
        Map testMap = new HashMap<String, Object>();
        testMap.put("test", "testValue");
        List testList = new ArrayList<String>();
        testList.add("testValue1");
        testList.add("testValue2");
        testList.add("testValue3");
        assertEquals("testValue", ArrayFunctions.get(testMap, "test"));
        assertEquals("testValue2", ArrayFunctions.get(testList, 1));
        List expectedList = new ArrayList<String>();
        expectedList.add("testValue2");
        expectedList.add("testValue3");
        assertEquals(expectedList, ArrayFunctions.get(testList, 1, 3));
    }

    @Test
    public void join() {
        List<String> input = new ArrayList<>();
        input.add("one");
        input.add("two");

        String output = ArrayFunctions.join(input, " ");
        assertEquals("one two", output);
    }
}
