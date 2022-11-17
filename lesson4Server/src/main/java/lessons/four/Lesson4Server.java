package lessons.four;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Lesson4Server {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket socket = new DatagramSocket(50_000)) {
            byte[] buf = new byte[256];
            DatagramPacket rqPacket = new DatagramPacket(buf, buf.length);

            while (true) {
                socket.receive(rqPacket);
                String rqData = new String(rqPacket.getData(), 0, rqPacket.getLength());
                if ("Stop".equals(rqData)) return;

                InetAddress address = rqPacket.getAddress();
                int port = rqPacket.getPort();
                System.out.printf("Received from Client with address: %s, port: %s, data: %s%n", address, port, rqData);
                DatagramPacket rsPacket = new DatagramPacket(buf, buf.length, address, port);
                socket.send(rsPacket);
            }
        }
    }
}
