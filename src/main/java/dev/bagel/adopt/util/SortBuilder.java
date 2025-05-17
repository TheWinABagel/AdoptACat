package dev.bagel.adopt.util;

import dev.bagel.adopt.arg.Arg;
import dev.bagel.adopt.arg.ArgType;
import dev.bagel.adopt.arg.SortType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SortBuilder {
    private final String base;
    private final List<Arg<?>> args = new ArrayList<>();

    public SortBuilder(String base) {
        this.base = base;
    }

    public boolean thing(SortType type, ArgType argType, String data) {
        if (type == null) {
            System.err.println("comparison does not exist");
            return false;
        }
        try {
            return args.add(new Arg<>(type, argType, argType.parser.apply(data)));
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
        List<Arg<?>> extras = new ArrayList<>();
        for (Arg<?> arg : args) {
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

}
