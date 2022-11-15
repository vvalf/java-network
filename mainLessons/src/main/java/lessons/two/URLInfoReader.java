package lessons.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLInfoReader {
    public void getURLInfoToConsole(final String urlForInfo) throws IOException {
        URL address = new URL(urlForInfo);
        URLConnection connection = address.openConnection();

        if (connection.getContentLength() == -1) return;

        System.out.println("Address: " + address);
        System.out.println("Content Length: " + connection.getContentLength());
        System.out.println("Content Type: " + connection.getContentType());

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            in.lines().forEach(System.out::println);
        }
    }
}
