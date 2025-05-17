package dev.bagel.adopt.arg;

import java.util.function.Function;

public enum ArgType {
    NAME(Function.identity(), "-n", "--name"),
    COLOR(Function.identity(), "-c", "--color"),
    AGE(Integer::parseInt, "-a", "--age");

    /** Function used to check if arg is valid or not
     * */
    public final Function<String, ?> parser;
    private final String[] validArgs;

    ArgType(Function<String, ?> parser, String... validArgs) {
        this.parser = parser;
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
}
