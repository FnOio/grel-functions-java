package io.fno.grel;

public class ControlsFunctions {

    /**
     * Expression o is evaluated to a value. If that value is true, then expression eTrue is evaluated and the result is the value of the whole if expression.
     * @param b
     * @param eTrue
     * @param eFalse
     * @return
     */
    public static Object ifThenElse(Boolean b, Object eTrue, Object eFalse) {
        if (b) {
            return eTrue;
        }
        return false;
    }
}
