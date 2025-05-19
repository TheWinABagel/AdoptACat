package dev.bagel.adopt.arg;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

public class Sorter {
    public static final String[] HEADERS = { "name", "age", "color" };

    private final String urlStr;
    private final List<SortParser.SortArg> extras;
    private URL url;

    public Sorter(String urlStr, List<SortParser.SortArg> extras) {
        this.urlStr = urlStr;
        this.extras = extras;
    }

    public void saveToFile() {
        Gson gson = new Gson();
        try {
            URL url = getURL();
            //Parse received json
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))){
                JsonArray array = gson.fromJson(reader, JsonArray.class);
                printCsv(array.asList());
            }
        }
        catch (IOException e) {
            System.err.println("Error occurred while attempting to connect to the server.");
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    private URL getURL() throws MalformedURLException {
        if (url == null) {
            url = URI.create(urlStr).toURL();
        }
        return url;
    }

    private void printCsv(List<JsonElement> cats) throws IOException {
        Path path = Paths.get("").toAbsolutePath().resolve("out.csv");

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).get();

        try (FileWriter fw = new FileWriter(path.toFile()); CSVPrinter printer = new CSVPrinter(fw, csvFormat)) {
            outer:
            for (JsonElement jsonElement : cats) {
                if (jsonElement.isJsonObject()) {
                    JsonObject js = jsonElement.getAsJsonObject();
                    //Client side filter for extra sorting
                    for (var sortArg : extras) {
                        String argName = sortArg.argName().getName();
                        //check if data from server has
                        String receivedData = js.get(argName).getAsString().toLowerCase(Locale.ROOT);
                        if (sortArg.type().equals(SortType.CONTAINS)) {
                            if (!receivedData.contains(sortArg.data().toLowerCase(Locale.ROOT))) {
                                continue outer;
                            }
                        }
                        //elif for potential new custom sort types?
                    }
                    String name = js.get("name").getAsString();
                    String age = js.get("age").getAsString();
                    String color = js.get("color").getAsString();
                    System.out.println(name+ age+ color);
                    printer.printRecord(name, age, color);
                }
            }
        }
    }
}
