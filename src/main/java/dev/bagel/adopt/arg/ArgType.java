package dev.bagel.adopt.arg;

import java.util.function.Function;

public enum ArgType {
    NAME(null, "-n", "--name"),
    COLOR(null, "-c", "--color"),
    AGE(Integer::parseInt, "-a", "--age");

    /** Function used to check if arg is valid or not
     * */
    public final Function<String, ?> validator;
    private final String[] validArgs;

    ArgType(Function<String, ?> validator, String... validArgs) {
        this.validator = validator;
        this.validArgs = validArgs;
    }

    public boolean matches(String toMatch) {
        if (toMatch == null) {
            return false;
        }
        for (String str : validArgs) {
            if (toMatch.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public String validate(String string) {
        if (this.validator == null) {
            return string;
        }
        validator.apply(string);
        return string;
    }
}
