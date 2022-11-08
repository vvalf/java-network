package lessons.three;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class HTTPServerForPost {
    public static void main(String[] args) throws IOException, URISyntaxException {
        final var server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.createContext("/login", new LoginHTTPHandler());
        server.start();

        new HTTPClient().asynchronousPostRequest("http://localhost:8080/login"); //todo change to testMethod
    }



}
