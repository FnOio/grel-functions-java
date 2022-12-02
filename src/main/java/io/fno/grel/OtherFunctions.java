package io.fno.grel;


/*
 * NOTE: brought over from commit 91a2defcdbc1f5fffffe95ce3e1e823fce91a7e7,
 *       has not been tested
 */
public class OtherFunctions {

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

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#hasfieldo-s-name">...</a>
     * <br>
     * Returns a boolean indicating whether {@code o} has a member field called {@code name}.
     * @param o     The object to check for member field called {@code name}.
     * @param name  The name of the field to check its presence in object {@code o}.
     * @return      {@code true} of {@code o} has a public field {@code name}.
     */
    public static boolean hasField(Object o, String name) {
        Class<?> someClass = o.getClass();
        try {
            someClass.getField(name);
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
