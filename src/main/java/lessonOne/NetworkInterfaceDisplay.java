package lessonOne;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class NetworkInterfaceDisplay {
    public List<String> getInfo() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

        final List<String> collect = new ArrayList<>();
        for (NetworkInterface nif: Collections.list(nets)) {
            final String displayName = nif.getDisplayName();
            final boolean virtual = nif.isVirtual();
            final boolean loopback = nif.isLoopback();
            final boolean up = nif.isUp();
            collect.add(displayName + ", is virtual: " + virtual + ", is Up: " + up + ", is loopback: " + loopback);
        }
        return collect;
    }
}