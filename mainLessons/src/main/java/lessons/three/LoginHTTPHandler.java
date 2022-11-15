package lessons.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import static java.nio.charset.StandardCharsets.UTF_8;

public class LoginHTTPHandler implements HttpHandler {

    @Override
    public void handle(final HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("POST")) {
            throw new UnsupportedOperationException("Unsupported request method: " + exchange.getRequestMethod());
        }

        Map<String, String> inputs = parseFormData(exchange);
        String login = inputs.get("login");
        String password = inputs.get("password");

        if (!"java".equals(login)) throw new InvalidLoginException("Некорректный логин,");

        final byte[] successMessage = "Hello Java".getBytes(UTF_8);
        exchange.sendResponseHeaders(200, successMessage.length);

        try (final var responseBody = exchange.getResponseBody()) {
            responseBody.write(successMessage);
            responseBody.flush();
        }
    }

    private static Map<String, String> parseFormData(final HttpExchange exchange) throws IOException {
        String requestBody = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), UTF_8)).readLine();
        return Arrays.stream(requestBody.split("&"))
                .map(pair -> pair.split("="))
                .collect(Collectors.toMap(pairArr -> pairArr[0], pairArr -> URLDecoder.decode(pairArr[1], UTF_8), (a, b) -> b));
    }
}


