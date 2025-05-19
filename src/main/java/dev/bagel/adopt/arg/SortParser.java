package dev.bagel.adopt.arg;

import dev.bagel.adopt.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SortParser {
    private final String host;
    private final List<SortArg> args = new ArrayList<>();

    public SortParser(String host, String[] args) {
        this.host = host;
        compile(args);
    }

    private void compile(String[] args) {
        if (args.length < 2) {
            Main.printHelp();
        }
        for (int i = 0; i < args.length; i++) {
            if (!args[i].startsWith("-")) {
                throw new IllegalArgumentException("You must specify the sort type.");
            }
            if (!(i + 1 < args.length)) {
                throw new IllegalArgumentException("You must specify a value to sort against.");
            }
            if (args[i].equals("-h") || args[i].equals("--help")) {
                Main.printHelp();
            }
            String[] split = args[i].split(":", 2);
            //If nothing specified, default to =
            if (split.length < 2) {
                split = Arrays.copyOf(split, 2);
                split[1] = "=";
            }
            for (ArgName type : ArgName.values()) {
                if (type.getArgtype(split[0])) {
                    addArg(SortType.find(split[1]), type, args[i + 1]);
                    break;
                }
            }
            i++;

        }
        print();
    }

    public boolean addArg(SortType type, ArgName argName, String data) {
        try {
            return args.add(new SortArg(type, argName, data));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse arg: ", e);
        }
    }

    public void print() {
        System.out.println("Parser arguments: " + this.args);
    }

    public Sorter getParser() {
        StringBuilder sb = new StringBuilder(host);
        int requests = 0;
        List<SortArg> extras = new ArrayList<>();
        for (SortArg arg : args) {
            if (arg.type().canUseForGetReq()) {
                char linkingChar = (requests == 0) ? '?' : '&';
                sb.append(linkingChar);
                sb.append(arg.argName().getName());
                sb.append(arg.type().getReqStr);
                sb.append(arg.data());
                requests++;
            }
            else {
                extras.add(arg);
            }
        }
        String str = sb.toString();
        return new Sorter(str, extras);
    }

    public record SortArg(SortType type, ArgName argName, String data) {}
}
