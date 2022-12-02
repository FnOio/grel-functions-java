package io.fno.grel;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayFunctionsTest {

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
    public void testLength() {
        String[] anArray = {"one", "two", "three"};
        assertEquals(3, ArrayFunctions.length(anArray));
    }

    @Test
    public void unique() {
        assertArrayEquals(
                new String[]{"1", "2", "3"},
                ArrayFunctions.uniques(new String[]{"1", "2", "2", "3"})
        );
    }

    @Test
    public void testSliceWithToWithinRange() {
        Integer[] anArray = {1, 2, 3, 4, 5};
        Integer[] result = (Integer[])ArrayFunctions.slice(anArray, 1, 4);

        assertArrayEquals(
                new Integer[]{2, 3, 4},
                result);
    }

    @Test
    public void testSliceWithToEqualToLength() {
        Integer[] anArray = {1, 2, 3, 4, 5};
        Integer[] result = (Integer[])ArrayFunctions.slice(anArray, 1, 5);

        assertArrayEquals(
                new Integer[]{2, 3, 4, 5},
                result);
    }

    @Test
    public void testSliceWithoutTo() {
        Integer[] anArray = {1, 2, 3, 4, 5};
        Integer[] result = (Integer[])ArrayFunctions.slice(anArray, 1);

        assertArrayEquals(
                new Integer[]{2, 3, 4, 5},
                result);
    }

    @Test
    public void testReverse() {
        String[] input = {"one", "two", "three"};
        Object[] expected = {"three", "two", "one"};

        Object[] result = ArrayFunctions.reverse(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortWithStrings() {
        String[] input = {"al", "Joe", "Bob", "jim" };
        String[] expected = {"Bob", "Joe", "al", "jim"};
        String[] result = (String[])ArrayFunctions.sort(input);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testSum() {
        Integer[] anArray = {1, 2, 3, 4, 5};
        int result = ArrayFunctions.sum(anArray);

        assertEquals(15, result);
    }

    @Test
    public void testUniquesPerformance() {

        for (int i = 0; i < 9; i++) {
            oneRunUnique((int)Math.pow(10,  i));
        }
    }
    private void oneRunUnique(int size) {
        Integer[] bigRandomArray = new Integer[size];
        Random random = new Random(0);
        for (int i = 0; i < size; i++) {
            bigRandomArray[i] = random.nextInt(100);
        }
        long startTime = System.currentTimeMillis();
        Object[] result = ArrayFunctions.uniques(bigRandomArray);
        long endTime = System.currentTimeMillis();
        assertTrue(bigRandomArray.length >= result.length);
        System.out.println("uniqueTiming for " + size + ": " + (endTime - startTime));
    }
}
