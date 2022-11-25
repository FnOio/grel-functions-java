package io.fno.grel;

import java.util.*;

public class ArrayFunctions {

    /**
     * If o has fields, returns the field named 'from' of o.
     * If o is an array, returns o[from, to].
     * If o is a string, returns o.substring(from, to)
     * https://github.com/OpenRefine/OpenRefine/wiki/GREL%20Array%20Functions#getarray-a-number-or-string-from-optional-number-to
     *
     * @param a    object (either List or Map) to get a value from
     * @param from from index (either Integer or String, must be )
     * @return Depends on actual arguments
     */
    public static Object get(Object a, Object from) {
        return ArrayFunctions.get(a, from, null);
    }

    /**
     * If o has fields, returns the field named 'from' of o.
     * If o is an array, returns o[from, to].
     * If o is a string, returns o.substring(from, to)
     * https://github.com/OpenRefine/OpenRefine/wiki/GREL%20Array%20Functions#getarray-a-number-or-string-from-optional-number-to
     *
     * @param a    object (either List or Map) to get a value from
     * @param from from index (either Integer or String, must be )
     * @param to   to index (optional, exclusive)
     * @return Depends on actual arguments
     */
    public static Object get(Object a, Object from, Integer to) {
        if (a instanceof List) {
            List a_array = (List) a;
            if (from instanceof Integer) {
                if (to == null) {
                    return a_array.get((Integer) from);
                }
                return a_array.subList((Integer) from, to);
            }
            throw new Error("'a' is a List, so 'from' parameter must be Integer");
        }
        if (a instanceof Map) {
            if (to != null) {
                throw new Error("'a' is a Map, so no 'to' parameter is allowed");
            }
            if (from instanceof Integer) {
                Map a_map = (Map) a;
                return a_map.get(from);
            }
            if (from instanceof String) {
                Map a_map = (Map) a;
                return a_map.get(from);
            }
            throw new Error("'a' is a Map, so 'from' parameter should be Integer or String");
        }
        throw new Error("'a' is not a List or a Map");
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
        return String.join(sep, a);
    }

    // TO-DO these functions are untested and need docstrings
    // (brought over from commit 98360fe7f7c13dcbd51c14db12218b605bd86c16)

    public static Integer length(Object[] a) {
        return a.length;
    }

    public static Object[] slice(Object[] a, Integer from, Integer to) {
        return Arrays.copyOfRange(a, from, to + 1);
    }

    public static Object[] slice(Object[] a, Integer from) {
        return slice(a, from, a.length);
    }

    // see get of strings
    public static Object[] reverse(Object[] a) {
        Collections.reverse(Arrays.asList(a));  // this also mutates a, aha!
        return a;
    }

    public static Object[] sort(Object[] a) {
        Arrays.sort(a);
        return a;
    }

    public static Integer sum(Integer[] a) {
        return Arrays.stream(a).mapToInt(Integer::intValue).sum();
    }

    public static Object[] uniques(Object[] a) {
        SortedSet<Object> set = new TreeSet<>(Arrays.asList(a));
        return set.toArray(new Object[0]);

    }

}
