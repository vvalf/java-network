package lessons.one;

import java.net.SocketException;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class NetworkInterfaceDisplayAnotherTest {

    @Test
    public void networkInterfaceDisplayTest() throws SocketException {
        List<String> networkInterfaceDisplay = new NetworkInterfaceDisplay().getInfo();

        networkInterfaceDisplay.forEach(System.out::println);

        assertTrue(networkInterfaceDisplay.size() > 0);
    }

}