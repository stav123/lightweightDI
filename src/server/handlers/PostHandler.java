package server.handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Stefan on 10/4/16.
 */
public class PostHandler extends RequestHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String response = "<h1> Hello from POST handler";
        httpExchange.sendResponseHeaders(200, response.length());

        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(response.getBytes());
    }
}
