package io.fno.grel;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.*;
public class Mathfunctions_Test {

    @Test
    public void testFactorial1() {
        assertEquals("5! should be 120", Long.valueOf(120), MathFunctions.fact(5L));
    }

    @Test
    public void testFactorial2() {
        assertEquals("0! should be 1", Long.valueOf(1), MathFunctions.fact(0L));
    }

    @Test
    public void testFactorial3() {
        assertEquals("1! should be 1", Long.valueOf(1), MathFunctions.fact(1L));
    }

    @Test
    public void testFactorial4() {
        assertEquals("-1! should be 0", Long.valueOf(0), MathFunctions.fact(-1L));
    }

    @Test
    public void testCombinations1(){
        assertEquals("20 choose 2 should be 190", Long.valueOf(190), MathFunctions.combin(20L,2L));
    }

    @Test
    public void testCombinations2(){
        assertEquals("25 choose 3 should be 2300", Long.valueOf(2300), MathFunctions.combin(25L,3L));
    }

    @Test
    public void testCombinations3(){
        assertEquals("2 choose 3 should be 0", Long.valueOf(0), MathFunctions.combin(2L,3L));
    }

    @Test
    public void testGcd1(){
        assertEquals("gcd(3,5) should be 1", Long.valueOf(1), MathFunctions.gcd(3L,5L));
    }

    @Test
    public void testGcd2(){
        assertEquals("gcd(16,28) should be 4", Long.valueOf(4), MathFunctions.gcd(16L,28L));
    }

    @Test
    public void testGcdSymmetric(){
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            Long l1 = random.nextLong();
            Long l2 = random.nextLong();
            assertEquals("gcd("+l1+", " + l2+") was not symmetric", MathFunctions.gcd(l1,l2) , MathFunctions.gcd(l2,l1));
        }
    }

    @Test
    public void testLcm1(){
        assertEquals("lcm(3,5) should be 15", Long.valueOf(15), MathFunctions.lcm(3L,5L));
    }

    @Test
    public void testLcm2(){
        assertEquals("lcm(21,6) should be 42", Long.valueOf(42), MathFunctions.lcm(21L,6L));
    }

    @Test
    public void testLcmSymmetric(){
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            Long l1 = random.nextLong();
            Long l2 = random.nextLong();
            assertEquals("lcm("+l1+", " + l2+") was not symmetric", MathFunctions.lcm(l1,l2) , MathFunctions.lcm(l2,l1));
        }
    }

    @Test
    public void testEven1(){
        assertEquals("even(2.1) should be 2", Long.valueOf(2), MathFunctions.even(2.1));
    }


    @Test
    public void testEven2(){
        assertEquals("even(2.6) should be 2", Long.valueOf(2), MathFunctions.even(2.6));
    }

    @Test
    public void testEven3(){
        assertEquals("even(3.1) should be 4", Long.valueOf(4), MathFunctions.even(3.1));
    }

    @Test
    public void testFactn(){
        assertEquals("factn(10,3) should be 280", Long.valueOf(280), MathFunctions.factn(10L,3L));
    }

    @Test
    public void testMultinomial(){
        assertEquals("multinomial(2,3) should be 10", Long.valueOf(10), MathFunctions.multinomial(2L,3L));
    }

}
