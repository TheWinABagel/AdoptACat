package dev.bagel.adopt;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        ArgParser parser = new ArgParser(args);

    }

    public static URL checkUrl(String urlStr) {
        try {
            return URI.create(urlStr).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}