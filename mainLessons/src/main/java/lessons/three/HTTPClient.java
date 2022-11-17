package lessons.three;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClient {
    public void asynchronousPostRequest(final String uri) throws URISyntaxException{
        final HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "text/plain; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString("login=java&password=javaPassword"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(rs -> {
                    System.out.println("Response from: " + rs.uri());
                    System.out.println("Response statusCode: " + rs.statusCode());
                    System.out.println("Response body: " + rs.body());
                });
    }

}
