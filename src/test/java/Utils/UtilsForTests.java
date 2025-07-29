package Utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UtilsForTests {
    public static Properties loadProperties() throws IOException {
        File file = new File("C:\\Users\\LotBag\\Desktop\\Hexlet\\restAssured\\src\\test\\resources" +
                "\\data.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties;
    }
}
