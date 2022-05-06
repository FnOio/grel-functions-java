package io.fno.grel;

import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return StringUtils.join(a, sep);
    }

    // TODO these functions need docstrings
    public static Integer length(List<Object> a) {
        return a.size();
    }

    public static List<Object> slice(List<Object> a, Integer from, Integer to) {
        return a.subList(from, to + 1);
    }

    public static List<Object> slice(List<Object> a, Integer from) {
        return slice(a, from, length(a));
    }

    // see get of strings
    public static List<Object> reverse(List<Object> a) {
        Collections.reverse(a);
        return a;
    }

    public static Integer sum(List<Integer> a) {
        return a.stream().reduce(0, Integer::sum);
    }

    // TODO how to do this sort?
    public static List<Object> sort(List<Object> a) {
        // a.sort();
        return a;
    }

    public static List<Object> uniques(List<Object> a) {
        return a.stream().distinct().collect(Collectors.toList());

    }

}
