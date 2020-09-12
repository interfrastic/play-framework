package net.avax;

import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        URIBuilder builder = new URIBuilder("https://www.google.com/search");

        builder.addParameter("q", "C++ Programming");

        System.out.println("Here is your URI: " + builder);
    }
}
