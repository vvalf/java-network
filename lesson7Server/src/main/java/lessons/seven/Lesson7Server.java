package lessons.seven;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class Lesson7Server {

    public static void main(String[] args) throws Exception {
        var threadPool = new QueuedThreadPool();
        threadPool.setName("server");
        var server = new Server(threadPool);
        var connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);
        server.setHandler(new ServerHandler());

        server.start();
    }
}
