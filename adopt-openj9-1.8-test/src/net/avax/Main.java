package net.avax;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.protocol.HTTP.DEF_CONTENT_CHARSET;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        URIBuilder builder = new URIBuilder("https://www.google.com/search");

        builder.addParameter("q", "C++ Programming");

        System.out.println("Here is your URI: " + builder);

        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("username", "Sinéad O'Connor"));
        params.add(new BasicNameValuePair("password", "lion+cobra"));

        String httpPostBody = URLEncodedUtils.format(params,
                DEF_CONTENT_CHARSET);

        System.out.println("Here is your HTTP POST body: " + httpPostBody);
    }
}
