import java.net.SocketException;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Lesson1Test {

    @Test
    public void networkInterfaceDisplayTest() throws SocketException {
        List<String> networkInterfaceDisplay = new NetworkInterfaceDisplay().getInfo();
        assertTrue(networkInterfaceDisplay.size() > 0);
    }


}