package io.fno.grel;

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
     * <a href="https://docs.openrefine.org/manual/grelfunctions#hasfieldo-s-name">hasField</a>
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

    /**
     * <a href="https://docs.openrefine.org/manual/grelfunctions#hasfieldo-s-name">coalesce</a>
     * <br>
     * Returns the first non-null from a series of objects.
     * @param objects   The series of objects to check.
     * @return          The first object in {@code objects} that is not {@code null}.
     *                  Returns {@code null} if no non-null object is found.
     */
    public static Object coalesce(Object... objects) {
        for (Object object : objects) {
            if (!(object == null)) {
                return object;
            }
        }
        return null;
    }
}
