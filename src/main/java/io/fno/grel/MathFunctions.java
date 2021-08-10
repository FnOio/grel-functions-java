package io.fno.grel;

/*
 * NOTE: brought over from commit f598c20470abdc7b30b444a1f3a382ab310c551d,
 *       has not been tested
 */
public class MathFunctions {
    public static double floor(int d) {
        return Math.floor(d);
    }

    public static double ceil(int d) {
        return Math.ceil(d);
    }

    public static double round(int d) {
        return Math.round(d);
    }

    public static double min(int d1, int d2) {
        return Math.min(d1, d2);
    }

    public static double max(int d1, int d2) {
        return Math.max(d1, d2);
    }

    public static int mod(int d1, int d2) {
        return Math.floorMod(d1, d2);
    }

    public static double ln(int d) {
        return Math.log(d);
    }

    public static double log(int d) {
        return Math.log10(d);
    }

    public static double exp(int d) {
        return Math.exp(d);
    }

    public static double pow(int d, int e) {
        return Math.pow(d, e);
    }
}
