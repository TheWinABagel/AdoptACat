package dev.bagel.adopt;

import dev.bagel.adopt.util.ArgParser;
import dev.bagel.adopt.util.CatComparison;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        ArgParser parser = new ArgParser(args);
        if (parser.containsAny("h", "help", "?")) {
            //TODO Write descriptive usage/readme
            System.out.println("Usage: ");
            return;
        }
        CatComparison comp = new CatComparison(parser, new StringBuilder("http://localhost:3000/cats"));

        try {
            URL url = URI.create(comp.getString()).toURL();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))){
                for (String line; (line = reader.readLine()) != null;) {
                    System.out.println(line);
                }
            }
        } catch (MalformedURLException ignored) {} //Impossible
        catch (IOException e) {
            System.err.println("");
            e.printStackTrace(System.err);
        }

    }

}