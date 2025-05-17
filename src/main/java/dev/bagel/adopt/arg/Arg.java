package dev.bagel.adopt.arg;

public record Arg<T>(SortType type, ArgType argtype, T data) {

    @Override
    public String toString() {
        return "Arg[SortType=%s,Value=%s]".formatted(type, data);
    }
}
