package io.fno.grel;

import java.util.List;

public class BooleanFunctions {

    /**
     * Logically AND two or more booleans to yield a boolean.
     * For example, `and(1 < 3, 1 > 0)` returns `true` because both conditions are true.
     *
     * @param b two or more booleans
     * @return boolean
     */
    public static Boolean and(List<Boolean> b) {
        return !b.contains(false);
    }

    /**
     * Logically OR two or more booleans to yield a boolean.
     * For example, `or(1 < 3, 1 > 7)` returns `true` because at least one of the conditions (the first one) is true.
     *
     * @param b two or more booleans
     * @return boolean
     */
    public static Boolean or(List<Boolean> b) {
        return b.contains(true);
    }

    /**
     * Logically NOT a boolean to yield another boolean.
     * For example, not(1 > 7) returns true because 1 > 7 itself is false.
     *
     * @param b a boolean
     * @return the reverted boolean
     */
    public static Boolean not(Boolean b) {
        return !b;
    }

    /**
     * Logically XOR (exclusive-or) two or more booleans to yield a boolean.
     * For example, `xor(1 < 3, 1 > 7)` returns `true` because only one of the conditions (the first one) is true. `xor(1 < 3, 1 < 7)` returns `false` because more than one of the conditions is true.
     *
     * @param b two or more booleans
     * @return Boolean
     */
    public static Boolean xor(List<Boolean> b) {
        int numberOfTrues = 0;
        for (Boolean b1 : b) {
            if (b1) {
                numberOfTrues++;
                if (numberOfTrues > 1) {
                    return false;
                }
            }
        }
        return numberOfTrues == 1;
    }

}
