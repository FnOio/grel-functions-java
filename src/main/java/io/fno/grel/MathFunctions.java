package io.fno.grel;

/*
 * NOTE: brought over from commit f598c20470abdc7b30b444a1f3a382ab310c551d,
 *       has not been tested
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
}
