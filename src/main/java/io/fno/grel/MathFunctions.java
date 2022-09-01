package io.fno.grel;

import java.math.BigInteger;
import java.util.Random;

/*
 * NOTE: brought over from commit f598c20470abdc7b30b444a1f3a382ab310c551d,
 *       has not been tested
 */

/**
 * Implementations of <a href="https://docs.openrefine.org/manual/grelfunctions#math-functions">GREL math function</a>.
 */
public class MathFunctions {
    public static Double floor(Double d) {
        return Math.floor(d);
    }

    public static Double ceil(Double d) {
        return Math.ceil(d);
    }

    public static Double round(Double d) {
        return (double) Math.round(d);
    }

    public static Double min(Double d1, Double d2) {
        return Math.min(d1, d2);
    }

    public static Double max(Double d1, Double d2) {
        return Math.max(d1, d2);
    }

    public static Integer mod(Integer d1, Integer d2) {
        return Math.floorMod(d1, d2);
    }

    public static Double ln(Double d) {
        return Math.log(d);
    }

    public static Double log(Double d) {
        return Math.log10(d);
    }

    public static Double exp(Double d) {
        return Math.exp(d);
    }

    public static Double pow(Double d, Double e) {
        return Math.pow(d, e);
    }

    public static Double abs(Double d) {
        return Math.abs(d);
    }

    public static Double acos(Double d) {
        return Math.acos(d);
    }

    public static Double asin(Double d) {
        return Math.asin(d);
    }

    public static Double atan(Double d) {
        return Math.atan(d);
    }

    public static Double atan2(Double d1, Double d2) {
        return Math.atan2(d1, d2);
    }

    public static Long combin(Long n1, Long n2) {
        if (n1 < n2) {
            return 0L;
        }
        BigInteger result = BigInteger.ONE;
        for(long i = n1; i > n2; i--){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result.divide(fact(BigInteger.valueOf(n1 - n2))).longValue();
    }

    public static Double cos(Double d) {
        return Math.cos(d);
    }

    public static Double cosh(Double d) {
        return Math.cosh(d);
    }

    public static Double sin(Double d) {
        return Math.sin(d);
    }

    public static Double sinh(Double d) {
        return Math.sinh(d);
    }

    public static Double tan(Double d) {
        return Math.tan(d);
    }

    public static Double tanh(Double d) {
        return Math.tanh(d);
    }

    public static Double degrees(Double d) {
        return Math.toDegrees(d);
    }

    public static Double radians(Double d) {
        return Math.toRadians(d);
    }

    public static Long even(Double d) {
        long rounded = Math.round(d);
        if (rounded % 2 == 0) {
            return rounded;
        }
        return d < rounded ? rounded - 1 : rounded + 1;
    }

    public static Long odd(Double d) {
        long rounded = Math.round(d);
        if (rounded % 2 == 1) {
            return rounded;
        }
        return d < rounded ? rounded - 1 : rounded + 1;
    }

    // private factorial to calculate big factorials for combinatorial functions
    private static BigInteger fact(BigInteger bigInteger) {
        return fact(bigInteger, BigInteger.ONE);
    }

    private static BigInteger fact(BigInteger b1, BigInteger step) {
        if(b1.compareTo(BigInteger.ZERO) < 0){
            return BigInteger.ZERO;
        }
        BigInteger result = BigInteger.ONE;
        for(BigInteger i = b1; i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(step)){
            result = result.multiply(i);
        }
        return result;
    }

    public static Long fact(Long l) {
        return fact(BigInteger.valueOf(l)).longValue();
    }

    public static Long factn(Long l1, Long step) {
        return fact(BigInteger.valueOf(l1), BigInteger.valueOf(step)).longValue();
    }

    public static Long gcd(Long l1, Long l2) {
        // euclids algorithm
        if (l1.equals(0L)) {
            return l2;
        }
        if (l2.equals(0L)) {
            return l1;
        }
        return gcd(l2, l1 % l2);
    }

    public static Long lcm(Long l1, Long l2) {
        return BigInteger.valueOf(l1).multiply(BigInteger.valueOf(l2)).abs().divide(BigInteger.valueOf(gcd(l1, l2))).longValue();
    }

    public static Long quotient(Long l1, Long l2) {
        return l1 / l2;
    }

    public static Long randomNumber(Long lower, Long upper) {
        final Random random = new Random();
        long randomLong = random.nextLong();
        randomLong *= (upper - lower);
        randomLong += lower;
        return randomLong; // upper bound inclusive
    }

    public static Long multinomial(Long... args) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger product = BigInteger.ONE;
        for (Long arg : args) {
            BigInteger bigInteger = BigInteger.valueOf(arg);
            sum = sum.add(bigInteger);
            product = product.multiply(fact(bigInteger));
        }
        return fact(sum).divide(product).longValue();
    }
}
