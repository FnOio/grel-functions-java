package io.fno.grel;

import java.util.*;
import java.util.stream.Stream;

public class ArrayFunctions {

    /**
     * If o has fields, returns the field named 'from' of o.
     * If o is an array, returns o[from, to].
     * If o is a string, returns o.substring(from, to)
     * <a href="https://github.com/OpenRefine/OpenRefine/wiki/GREL%20Array%20Functions#getarray-a-number-or-string-from-optional-number-to">...</a>
     *
     * @param a    object (either List or Map) to get a value from
     * @param from from index (either Integer or String, must be )
     * @return Depends on actual arguments
     */
    public static Object get(List<?> a, Integer from) {
        return ArrayFunctions.get(a, from, null);
    }

    /**
     * If o has fields, returns the field named 'from' of o.
     * If o is an array, returns o[from, to].
     * If o is a string, returns o.substring(from, to)
     * <a href="https://github.com/OpenRefine/OpenRefine/wiki/GREL%20Array%20Functions#getarray-a-number-or-string-from-optional-number-to">...</a>
     *
     * @param a    object (either List or Map) to get a value from
     * @param from from index (either Integer or String, must be )
     * @param to   to index (optional, exclusive)
     * @return Depends on actual arguments
     */
    public static List<?> get(List<?> a, Integer from, Integer to) {
        if (to == null) {
            to = from + 1;
        }
        return a.subList(from, to);
    }

    /**
     * Returns the string obtained by joining the array `a` with the separator `sep`.
     * For example, `join([ "foo", "bar", "baz" ], ";")` returns the string `foo;bar;baz`.
     *
     * @param a   array
     * @param sep separator
     * @return the string obtained by joining the array `a` with the separator `sep`
     */
    public static String join(List<String> a, String sep) {
        final String separator = sep == null ? "" : sep;
        return String.join(separator, a);
    }

    // TO-DO these functions are untested and need docstrings
    // (brought over from commit 98360fe7f7c13dcbd51c14db12218b605bd86c16)

    /**
     * Returns the size of an array, meaning the number of objects inside it. Arrays can be empty, in which case length() will return 0.
     *
     * @param a The array to get the length from
     * @return  The length of a
     */
    public static Integer length(Object[] a) {
        return a.length;
    }

    /**
     * Returns a sub-array of a given array, from the first index provided and up to and excluding the optional
     * last index provided. Remember that array objects are indexed starting at 0. If the to value is null,
     * it is understood to be the end of the array.
     *
     * @param a     The array to get a slice from
     * @param from  The start index of the slice
     * @param to    The end index of the slice (exclusive)
     * @return The slice of the array from {@literal from} to {@literal to} index
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     *           or {@code from > a.length}
     * @throws IllegalArgumentException if {@code from > to}
     * @throws NullPointerException if {@code a} is null
     */
    public static Object[] slice(Object[] a, Integer from, Integer to) {
        return Arrays.copyOfRange(a, from, to);
    }

    /**
     * Returns a sub-array of a given array, from the first index provided and up to the end.
     * Remember that array objects are indexed starting at 0.
     *
     * @param a     The array to get a slice from
     * @param from  The start index of the slice
     * @return The slice of the array from {@literal from} to the end of the array.
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     * @throws IllegalArgumentException if {@code from > a.length}
     * @throws NullPointerException if {@code a} is null
     */
    public static Object[] slice(Object[] a, Integer from) {
        return slice(a, from, a.length);
    }

    /**
     * Reverser an array
     * @param a The array to reverse
     * @return  a reversed
     */
    public static Object[] reverse(Object[] a) {
        Collections.reverse(Arrays.asList(a));  // this also mutates a, aha!
        return a;
    }

    /**
     * Sorts the array in ascending order. Sorting depends on the Object and the current locale used by the JVM.
     * @param a The array to sort
     * @return  The sorted array
     * @throws ClassCastException if the array contains elements that are not
     *           <i>mutually comparable</i> (for example, strings and integers)
     * @throws IllegalArgumentException (optional) if the natural
     *           ordering of the array elements is found to violate the
     *           {@link Comparable} contract
     */
    public static Object[] sort(Object[] a) {
        Arrays.sort(a);
        return a;
    }

    /**
     * Returns the sum of the numbers in the array.
     * @param a The array to sum the elements of
     * @return  The sum of the elements
     * TODO: generalize to Number?
     */
    public static Integer sum(Integer[] a) {
        return Arrays.stream(a).mapToInt(Integer::intValue).sum();
    }

    /**
     * Returns the array with duplicates removed.
     * Case-sensitive and without preserving order
     * @param a The array to remove the duplicates from
     * @return  An array without duplicates, order is not defined.
     */
    public static Object[] uniques(Object[] a) {
        return Stream.of(a).distinct().toArray();
    }

}
