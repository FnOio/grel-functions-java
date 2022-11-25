package io.fno.grel;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathFunctionsTest {

    @Test
    public void testFactorial1() {
        assertEquals(Long.valueOf(120), MathFunctions.fact(5L), "5! should be 120");
    }

    @Test
    public void testFactorial2() {
        assertEquals(Long.valueOf(1), MathFunctions.fact(0L), "0! should be 1");
    }

    @Test
    public void testFactorial3() {
        assertEquals(Long.valueOf(1), MathFunctions.fact(1L), "1! should be 1");
    }

    @Test
    public void testFactorial4() {
        assertEquals(Long.valueOf(0), MathFunctions.fact(-1L), "-1! should be 0");
    }

    @Test
    public void testCombinations1(){
        assertEquals(Long.valueOf(190), MathFunctions.combin(20L,2L), "20 choose 2 should be 190");
    }

    @Test
    public void testCombinations2(){
        assertEquals(Long.valueOf(2300), MathFunctions.combin(25L,3L), "25 choose 3 should be 2300");
    }

    @Test
    public void testCombinations3(){
        assertEquals(Long.valueOf(0), MathFunctions.combin(2L,3L), "2 choose 3 should be 0");
    }

    @Test
    public void testGcd1(){
        assertEquals(Long.valueOf(1), MathFunctions.gcd(3L,5L), "gcd(3,5) should be 1");
    }

    @Test
    public void testGcd2(){
        assertEquals(Long.valueOf(4), MathFunctions.gcd(16L,28L), "gcd(16,28) should be 4");
    }

    @Test
    public void testGcdSymmetric(){
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            Long l1 = random.nextLong();
            Long l2 = random.nextLong();
            assertEquals(MathFunctions.gcd(l1,l2) , MathFunctions.gcd(l2,l1), "gcd("+l1+", " + l2+") was not symmetric");
        }
    }

    @Test
    public void testLcm1(){
        assertEquals(Long.valueOf(15), MathFunctions.lcm(3L,5L), "lcm(3,5) should be 15");
    }

    @Test
    public void testLcm2(){
        assertEquals(Long.valueOf(42), MathFunctions.lcm(21L,6L), "lcm(21,6) should be 42");
    }

    @Test
    public void testLcmSymmetric(){
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            Long l1 = random.nextLong();
            Long l2 = random.nextLong();
            assertEquals(MathFunctions.lcm(l1,l2) , MathFunctions.lcm(l2,l1), "lcm("+l1+", " + l2+") was not symmetric");
        }
    }

    @Test
    public void testEven1(){
        assertEquals(Long.valueOf(2), MathFunctions.even(2.1), "even(2.1) should be 2");
    }


    @Test
    public void testEven2(){
        assertEquals(Long.valueOf(2), MathFunctions.even(2.6), "even(2.6) should be 2");
    }

    @Test
    public void testEven3(){
        assertEquals(Long.valueOf(4), MathFunctions.even(3.1), "even(3.1) should be 4");
    }

    @Test
    public void testFactn(){
        assertEquals(Long.valueOf(280), MathFunctions.factn(10L,3L), "factn(10,3) should be 280");
    }

    @Test
    public void testMultinomial(){
        assertEquals(Long.valueOf(10), MathFunctions.multinomial(2L,3L), "multinomial(2,3) should be 10");
    }

}
