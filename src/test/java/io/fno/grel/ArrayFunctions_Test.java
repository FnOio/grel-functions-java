package io.fno.grel;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayFunctions_Test {

    @Test
    public void get() {
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("test", "testValue");
        List<String> testList = new ArrayList<>();
        testList.add("testValue1");
        testList.add("testValue2");
        testList.add("testValue3");
        assertEquals("testValue", ArrayFunctions.get(testMap, "test"));
        assertEquals("testValue2", ArrayFunctions.get(testList, 1));
        List<String> expectedList = new ArrayList<>();
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

    @Test
    public void unique() {
        List<String> input = Arrays.asList("1","2","2","3");
        assertArrayEquals(
                new String[]{"1","2","3"},
                ArrayFunctions.uniques(new ArrayList<Object>(input)).toArray()
        );
    }

    @Test
    public void sum() {
        List<Integer> input = new ArrayList<>();
        input.add(2);
        input.add(3);
        Integer output = ArrayFunctions.sum(input);
        assertEquals(Integer.valueOf(5), output);
    }
}
