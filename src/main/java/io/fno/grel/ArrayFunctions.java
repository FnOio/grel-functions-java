package io.fno.grel;

import org.apache.commons.lang.StringUtils;

import java.util.List;

public class ArrayFunctions {

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

}
