package lessons.three;

import java.io.IOException;
import java.net.URISyntaxException;

import com.sun.net.httpserver.HttpServer;

import org.junit.Test;

import static org.junit.Assert.*;

public class HTTPServerForPostTest {

    @Test
    public void mainTest() throws IOException, URISyntaxException, InterruptedException {

        HTTPServerForPost.main(new String[0]);

    }
}