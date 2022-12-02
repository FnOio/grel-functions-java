package io.fno.grel;


import java.lang.reflect.Field;

/*
 * NOTE: brought over from commit 91a2defcdbc1f5fffffe95ce3e1e823fce91a7e7,
 *       has not been tested
 */
public class OtherFunctions {

    // https://docs.openrefine.org/manual/grelfunctions#typeo

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#typeo">type</a>
     * <br>
     * Returns a string with the data type of o, such as undefined, string, number, boolean, etc.
     * @param o The object to get the type of.
     * @return  The simple name of the class of {@code o} in lower case.
     */
    public static String type(Object o) {
        return o.getClass().getSimpleName().toLowerCase();
    }

    // https://docs.openrefine.org/manual/grelfunctions#hasfieldo-s-name
    public static boolean hasField(Object o, String name) {
        Class<?> someClass = o.getClass();
        try {
            Field someField = someClass.getField(name);
        } catch (NoSuchFieldException e) {
            return false;
        }
        return true;
    }

    // https://docs.openrefine.org/manual/grelfunctions#hasfieldo-s-name
    public static Object coalesce(Object... objects) {
        for (Object object : objects) {
            if (!(object == null)) {
                return object;
            }
        }
        return null;
    }
}
