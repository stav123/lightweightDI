package server.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Stefan on 10/4/16.
 */
public class PropertyUtils {

    private static final Properties prop = new Properties();


    static {
        String propertiesFile = "config.properties";
        try {
            prop.load(PropertyUtils.class.getClassLoader().getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getProp(String property) throws IOException {
        return prop.getProperty(property);
    }

}
