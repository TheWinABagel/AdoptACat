package dev.bagel.adopt.util;

import dev.bagel.adopt.arg.ArgType;
import dev.bagel.adopt.arg.SortType;

import java.util.*;
import java.util.function.Function;

public class ArgumentParser {
    private static final Map<String, Function<String, ?>> VALID_ARGS = new HashMap<>();

    public final SortBuilder builder = new SortBuilder(Constants.URL_BASE);

    static {
        Function<String, ?> val = Integer::parseInt;
        register("-a", val);
        register("--age", val);
        val = Function.identity();
        register("--color", val);
        register("-c", val);
        register("--name", val);
        register("-n", val);
    }

    public ArgumentParser(String[] args) {
        compile(args);
    }

    public static <T> void register(String val, Function<String, T> parsingFunction) {
        if (VALID_ARGS.containsKey(val)) {
            throw new IllegalArgumentException("CLI Arg handler for %s already registered.".formatted(val));
        }
        VALID_ARGS.put(val, parsingFunction);
    }

    private void compile(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (!args[i].startsWith("-")) {
                throw new IllegalArgumentException("You must specify the sort type.");
            }
            if (!(i + 1 < args.length)) {
                throw new IllegalArgumentException("You must specify a value to sort against.");
            }
//            if (args[i + 1].startsWith("-")) {
//                throw new IllegalArgumentException("Sorted value cannot start with -.");
//            } else
            {
                String[] split = args[i].split(":", 2);
                //If nothing specified, default to =
                if (split.length < 2) {
                    split = Arrays.copyOf(split, 2);
                    split[1] = "=";
                }
                for (ArgType type : ArgType.values()) {
                    if (type.matches(split[0])) {
                        builder.thing(SortType.find(split[1]), type, args[i + 1]);
                        break;
                    }
                }
                i++;
            }

        }
        builder.print();
    }

}
