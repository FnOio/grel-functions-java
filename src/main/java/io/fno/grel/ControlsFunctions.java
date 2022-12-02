package io.fno.grel;

public class ControlsFunctions {

    /**
     * Expression o is evaluated to a value. If that value is true, then expression eTrue is evaluated and the result is the value of the whole if expression.
     * @param b         The result of the evaluation of an expression
     * @param eTrue     The object to return if {@code b} is {@code true}
     * @param eFalse    The object to return if {@code b} is {@code false}
     * @return Object   {@code eTrue} of {@code b} is {@code true}; {@code eFalse} if {@code b} is {@code false}.
     */
    public static Object ifThenElse(Boolean b, Object eTrue, Object eFalse) {
        if (b) {
            return eTrue;
        }
        return eFalse;
    }
}
