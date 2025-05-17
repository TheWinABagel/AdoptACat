package dev.bagel.adopt;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.bagel.adopt.util.ArgumentParser;
import dev.bagel.adopt.util.Sorter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static final String[] HEADERS = { "Name", "age", "color" };

    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser(args); //todo clean up/remove parser, roll into builder
        Gson gson = new Gson();
        try {
            Sorter sorter = parser.builder.build();
            URL url = sorter.getURL();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))){
                //Read json
                JsonArray array = gson.fromJson(reader, JsonArray.class);

                printCsv(array.asList());
                for (String line; (line = reader.readLine()) != null;) {
                    System.out.println(line);
                }
            }
        } catch (MalformedURLException ignored) {} //Impossible
        catch (IOException e) {
            System.err.println("welp");
            e.printStackTrace(System.err);
        }

    }

    public static void printHelp() {
        System.out.println("todo help"); //todo help
        System.exit(0);
    }

    private static void printCsv(List<JsonElement> cats) throws IOException {
        Path path = Paths.get("").toAbsolutePath().resolve("out.csv");
        StringWriter sw = new StringWriter();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS).get();
        try (FileWriter fw = new FileWriter(path.toFile()); CSVPrinter printer = new CSVPrinter(fw, csvFormat)) {
            for (JsonElement jsonElement : cats) {
                if (jsonElement.isJsonObject()) {
                    JsonObject js = jsonElement.getAsJsonObject();
                    System.out.println(js.get("color").getAsString());
                    printer.printRecord(js.get("name").getAsString(), js.get("age").getAsInt(), js.get("color").getAsString());
                }
            }
        }

    }
}