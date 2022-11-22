package lesson.five;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Lesson5Client {
    public static void main(String[] args) throws IOException {
        try (var client = SocketChannel.open(new InetSocketAddress("localhost", 9999))) {
            var bufferHello = ByteBuffer.wrap("Hello".getBytes());
            writeRq(bufferHello, client);
            readRs(bufferHello, client);

            var bufferStop = ByteBuffer.wrap("Stop".getBytes());
            writeRq(bufferStop, client);
            readRs(bufferStop, client);
        }
    }

    private static void writeRq(ByteBuffer bufferStop, SocketChannel client) throws IOException {
        System.out.println("Запрос к серверу: " + new String(bufferStop.array()).trim());
        client.write(bufferStop);

        bufferStop.clear();
    }

    private static void readRs(ByteBuffer bufferStop, SocketChannel client) throws IOException {
        client.read(bufferStop);
        System.out.println("Response: " + new String(bufferStop.array()).trim());

        bufferStop.clear();
    }
}
