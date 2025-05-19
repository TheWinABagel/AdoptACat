package dev.bagel.adopt.arg;

import java.util.Locale;

public enum ArgName {
    NAME("-n", "--name"),
    COLOR("-c", "--color"),
    AGE("-a", "--age");

    private final String[] validArgs;

    ArgName(String... validArgs) {
        this.validArgs = validArgs;
    }

    public boolean getArgtype(String toMatch) {
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

    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
