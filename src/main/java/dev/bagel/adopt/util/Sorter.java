package dev.bagel.adopt.util;

import dev.bagel.adopt.arg.Arg;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class Sorter {
    private final String urlStr;
    private final List<Arg<?>> extras; //todo finish extra parsing on client
    private URL url;

    public Sorter(String urlStr, List<Arg<?>> extras) {
        this.urlStr = urlStr;
        this.extras = extras;
    }

    public URL getURL() throws MalformedURLException {
        if (url == null) {
            url = URI.create(urlStr).toURL();
        }
        return url;

    }
}
