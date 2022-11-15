package lessons.two;

import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class URLInfoReaderTest {

    @Test
    public void getURLInfoToConsole() throws IOException {
        URLInfoReader urlInfoReader = new URLInfoReader();
        urlInfoReader.getURLInfoToConsole("https://ya.ru/");
        urlInfoReader.getURLInfoToConsole("https://wrongURL.wrongURL/");

        assertThrows(IOException.class, () -> urlInfoReader.getURLInfoToConsole("wrongURL.wrongURL"));

    }
}