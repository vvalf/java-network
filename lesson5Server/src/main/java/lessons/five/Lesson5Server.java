package lessons.five;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static java.nio.channels.SelectionKey.OP_ACCEPT;
import static java.nio.channels.SelectionKey.OP_READ;

public class Lesson5Server {
    public static void main(String[] args) throws IOException {
        try (var selector = Selector.open();
             var serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress("localhost", 9999));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, OP_ACCEPT);
            var buffer = ByteBuffer.allocate(256);

            while (true) {
                selector.select();
                var selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    var key = iter.next();
                    if (key.isAcceptable()) {
                        register(selector, serverSocket);
                        iter.remove();
                        continue;
                    }
                    if (key.isReadable()) {
                        final SocketChannel client = (SocketChannel) key.channel();
                        if ("Stop".equals(readRq(buffer, client))) {
                            return;
                        } else {
                            answer(buffer, client);
                        }
                    }
                    iter.remove();
                }
            }
        }
    }

    private static String readRq(ByteBuffer buffer, SocketChannel client) throws IOException {
        client.read(buffer);
        var response = new String(buffer.array()).substring(0, buffer.position());
        System.out.println(response);
        return response;
    }

    private static void answer(final ByteBuffer buffer, SocketChannel client) throws IOException {
        buffer.flip();
        client.write(buffer);
        buffer.clear();
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        var client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, OP_READ);
    }
}