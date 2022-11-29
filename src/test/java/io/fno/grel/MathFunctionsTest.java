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
        assertEquals(Long.valueOf(4), MathFunctions.even(2.1), "even(2.1) should be 2");
    }


    @Test
    public void testEven2(){
        assertEquals(4, MathFunctions.even(2.6d), "even(2.6) should be 4");
    }

    @Test
    public void testEven3(){
        assertEquals(4, MathFunctions.even(3.1d), "even(3.1) should be 4");
    }

    @Test
    public void testEven4(){
        assertEquals(4, MathFunctions.even(4d), "even(4) should be 4");
    }

    @Test
    public void testFactn(){
        assertEquals(Long.valueOf(280), MathFunctions.factn(10L,3L), "factn(10,3) should be 280");
    }

    @Test
    public void testMultinomial(){
        assertEquals(Long.valueOf(10), MathFunctions.multinomial(2L,3L), "multinomial(2,3) should be 10");
    }

    @Test
    public void testFloor() {
        double d1 = 5.4d;
        assertEquals(5.0d, MathFunctions.floor(d1));

        double d2 = 5.6d;
        assertEquals(5.0d, MathFunctions.floor(d2));
    }

    @Test
    public void testCeil() {
        double d1 = 5.4d;
        assertEquals(6.0d, MathFunctions.ceil(d1));

        double d2 = 5.6d;
        assertEquals(6.0d, MathFunctions.ceil(d2));
    }

    @Test
    public void testRound() {
        double d1 = 5.4d;
        assertEquals(5.0d, MathFunctions.round(d1));

        double d2 = 5.6d;
        assertEquals(6.0d, MathFunctions.round(d2));
    }

    @Test
    public void testMin() {
        assertEquals(5d, MathFunctions.min(70d, 5d));
    }

    @Test
    public void testMax() {
        assertEquals(70d, MathFunctions.max(70d, 5d));
    }

    @Test
    public void testMod() {
        assertEquals(2, MathFunctions.mod(74, 9));
    }

    @Test
    public void testLn() {
        assertEquals( 1.6094379124341003d, MathFunctions.ln(5d));
    }

    @Test
    public void testLog() {
        assertEquals( 0.6989700043360189, MathFunctions.log(5d));
    }

    @Test
    public void testPow() {
        assertEquals(256d, MathFunctions.pow(2d, 8d));
    }

    @Test
    public void testExp() {
        assertEquals(148.4131591025766d, MathFunctions.exp(5d));
    }

    @Test
    public void testAbs() {
        assertEquals(6d, MathFunctions.abs(-6d));
    }

    @Test
    public void testAcos() {
        assertEquals(1.218557541697832d, MathFunctions.acos(0.345d));
    }

    @Test
    public void testAsin() {
        assertEquals(0.35223878509706474d, MathFunctions.asin(0.345d));
    }

    @Test
    public void testAtan() {
        assertEquals(0.3322135507465967d, MathFunctions.atan(0.345d));
    }

    @Test
    public void testAtan2() {
        assertEquals(0.5218342798144103, MathFunctions.atan2(0.345d, 0.6d));
    }

    @Test
    public void testCos() {
        assertEquals(0.28366218546322625d, MathFunctions.cos(5d));
    }

    @Test
    public void testCosh() {
        assertEquals(74.20994852478785d, MathFunctions.cosh(5d));
    }

    @Test
    public void testSin() {
        assertEquals(-0.5440211108893698d, MathFunctions.sin(10d));
    }

    @Test
    public void testSinh() {
        assertEquals(11013.232874703393d, MathFunctions.sinh(10d));
    }

    @Test
    public void testTan() {
        assertEquals(0.6483608274590866d, MathFunctions.tan(10d));
    }

    @Test
    public void testTanh() {
        assertEquals(0.9999999958776927d, MathFunctions.tanh(10d));
    }

    @Test
    public void testDegrees() {
        assertEquals(286.4788975654116d, MathFunctions.degrees(5d));
    }

    @Test
    public void testRadians() {
        assertEquals(0.17453292519943295d, MathFunctions.radians(10d));
    }

    @Test
    public void testOdd() {
        assertEquals(3, MathFunctions.odd(2.6d));
        assertEquals(5, MathFunctions.odd(3.1d));
        assertEquals(5, MathFunctions.odd(5d));
        assertEquals(1, MathFunctions.odd(0d));
    }

    @Test
    public void testNegativeOdd() {
        assertEquals(-5, MathFunctions.odd(-5d));
        assertEquals(-3, MathFunctions.odd(-4.9d));
    }

    @Test
    public void testNegativeEven() {
        assertEquals(-4, MathFunctions.even(-5d));
        assertEquals(-4, MathFunctions.even(-4.9d));
        assertEquals(-4, MathFunctions.even(-4d));
    }

    @Test
    public void testQuotient() {
        assertEquals(4, MathFunctions.quotient(9L, 2L));
    }

}
