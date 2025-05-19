package dev.bagel.adopt;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.bagel.adopt.arg.SortParser;
import dev.bagel.adopt.arg.Sorter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        SortParser parser = new SortParser("http://localhost:3000/cats", args);
        Gson gson = new Gson();
        try {
            Sorter sorter = parser.getParser();
            URL url = sorter.getURL();
            //Parse received json
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))){
                JsonArray array = gson.fromJson(reader, JsonArray.class);
                sorter.printCsv(array.asList());
            }
        }
        catch (IOException e) {
            System.err.println("Error occurred while attempting to connect to the server.");
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    public static void printHelp() {
        String[] usage = {
                "Usage: java program [options...]",
                "Connects to server at 'http://localhost:3000/cats'\n",
                "Sort types [<st>], will only print to a file if all conditions match",
                " =          Equals",
                " >          Greater Than",
                " <          Less Than",
                " >=         Greater Than or Equal To",
                " <=         Less Than or Equal To",
                " contains=  Check if value contains passed string (ignoring case)\n",
                "Options",
                " -c:<st>, --color:<st> <color>          Sort against color of cat",
                " -a:<st>, --age:<st> <age>              Sort against age of cat",
                " -n:<st>, --name:<st> <age>             Sort against name of cat"
        };
        for (String str : usage) {
            System.out.println(str);
        }
        System.exit(0);
    }

}