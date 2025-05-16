package dev.bagel.adopt.util;

public class CatComparison {
    private final ArgParser parser;
    private final StringBuilder builder;

    public CatComparison(ArgParser parser, StringBuilder builder) {
        this.parser = parser;
        this.builder = builder;
        evaluate();
    }

    private String evaluate() {
        builder.append("?");
        for (var entry : parser.argMap.entrySet()) {
            switch (entry.getKey()) {
                case "c", "color" -> {
                    builder.append("color=");
                    builder.append(entry.getValue());
                }
                case "a", "age" -> {
                    builder.append("age");
                    builder.append("="); //todo add support for lt, gt, etc
                    builder.append(entry.getValue());
                }
                case "n", "name" -> {
                    builder.append("name=");
                }
            }
        }

        return builder.toString();
    }

    public String getString() {
        return builder.toString();
    }
}
