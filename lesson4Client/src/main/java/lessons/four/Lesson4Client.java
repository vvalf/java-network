package lessons.four;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Lesson4Client {
    public static void main(String[] args) throws IOException {
        final InetAddress address = InetAddress.getByName(args[0]);

        try (DatagramSocket socket = new DatagramSocket()) {
            DatagramPacket rsPacket = sendMessage("Hello", socket, address);
            socket.receive(rsPacket);

            String rsData = new String(rsPacket.getData(), 0, rsPacket.getLength());
            System.out.printf("Received from Server with address: %s, port: %s, data: %s%n", rsPacket.getAddress(), rsPacket.getPort(), rsData);

            sendMessage("Stop", socket, address);
        }
    }

    private static DatagramPacket sendMessage(final String message, final DatagramSocket socket, final InetAddress address) throws IOException {
        DatagramPacket rqPacket = new DatagramPacket(message.getBytes(), message.length(), address, 50_000);
        socket.send(rqPacket);
        return rqPacket;
    }
}
