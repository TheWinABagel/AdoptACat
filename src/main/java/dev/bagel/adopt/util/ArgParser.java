package dev.bagel.adopt.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class ArgParser {
    final Map<String, String> argMap = new HashMap<>();

    public ArgParser(String[] args) {
        parseArgs(args);
    }

    public boolean containsAny(String... args) {
        for (String arg : args) {
            if (argMap.containsKey(arg)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String arg) {
        return argMap.containsKey(arg);
    }

    public String get(String arg) {
        return argMap.get(arg);
    }

    public String getOrDefault(String arg, Supplier<String> def) {
        String ret = argMap.get(arg);
        if (ret == null) {
            ret = def.get();
        }
        return ret;
    }

    private void parseArgs(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Requires a search parameter");
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                String key = args[i].substring(1);
                String value = null;

                if (i + 1 < args.length) {
                    value = args[i + 1];

                    if (value.startsWith("-")) {
                        argMap.put(key, "");
                        continue;
                    }
                    i++;
                }

                if (argMap.containsKey(key)) {
                    throw new IllegalArgumentException("Argument %s was already passed".formatted(key));
                }

                argMap.put(key, value);
            }
        }
    }
}
