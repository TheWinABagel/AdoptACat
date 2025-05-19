package dev.bagel.adopt.arg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SortParser {
    private final String base;
    private final List<SortArg<?>> args = new ArrayList<>();

    public SortParser(String base, String[] args) {
        this.base = base;
        compile(args);
    }

    private void compile(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (!args[i].startsWith("-")) {
                throw new IllegalArgumentException("You must specify the sort type.");
            }
            if (!(i + 1 < args.length)) {
                throw new IllegalArgumentException("You must specify a value to sort against.");
            }
            String[] split = args[i].split(":", 2);
            //If nothing specified, default to =
            if (split.length < 2) {
                split = Arrays.copyOf(split, 2);
                split[1] = "=";
            }
            for (ArgType type : ArgType.values()) {
                if (type.matches(split[0])) {
                    thing(SortType.find(split[1]), type, args[i + 1]);
                    break;
                }
            }
            i++;


        }
        print();
    }

    public boolean thing(SortType type, ArgType argType, String data) {
        if (type == null) {
            System.err.println("Comparison does not exist");
            return false;
        }
        try {
            return args.add(new SortArg<>(type, argType, argType.validate(data)));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse arg: ", e);
        }
    }

    public void print() {
        System.out.println("Builder arguments: " + this.args);
    }

    public Sorter build() {
        StringBuilder sb = new StringBuilder(base);
        int requests = 0;
        List<SortArg<?>> extras = new ArrayList<>();
        for (SortArg<?> arg : args) {
            if (arg.type().canUseForGetReq()) {
                char linkingChar = (requests == 0) ? '?' : '&';
                sb.append(linkingChar);
                sb.append(arg.argtype().name().toLowerCase(Locale.ROOT));
                sb.append(arg.type().getReqStr);
                sb.append(arg.data());
                requests++;
            }
            else {
                extras.add(arg);
            }
        }
        System.out.println("Extras not printed: " + extras);
        String str = sb.toString();
        System.out.println("URL to use: " + str);
        return new Sorter(str, extras);
    }

    public record SortArg<T>(SortType type, ArgType argtype, T data) {}
}
