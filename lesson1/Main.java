package src.main.java.lesson1;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

        for (NetworkInterface nif: Collections.list(nets)) {
            final String displayName = nif.getDisplayName();
            final boolean virtual = nif.isVirtual();
            final boolean loopback = nif.isLoopback();
            final boolean up = nif.isUp();
            System.out.printf("%s, is virtual: %s, is: Up %s, is loopback: %s%n", displayName, virtual, up, loopback);
        }
    }
}