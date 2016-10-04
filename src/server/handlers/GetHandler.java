package server.handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by Stefan on 10/4/16.
 */
public class GetHandler extends RequestHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String propertiesFile = "config.properties";
        Properties prop = new Properties();

        prop.load(getClass().getClassLoader().getResourceAsStream(propertiesFile));

        String className = prop.getProperty("configuration.class");






    }
}
