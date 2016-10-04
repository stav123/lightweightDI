package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.utils.PropertyUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Stefan on 10/4/16.
 */
public class RequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        new Thread(() -> {
            if(httpExchange.getRequestMethod().equals("GET")) {

                try {
                    this.getHandle(httpExchange);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    this.postHandle(httpExchange);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }

    public void getHandle(HttpExchange httpExchange) throws IOException {

        String response = "GET REQUEST WITH PATH: " + httpExchange.getRequestURI().getRawPath();
        httpExchange.sendResponseHeaders(200, response.length());

        //load the class from props file -> look for controller -> return json to response

        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(response.getBytes());
        responseBody.close();

    }

    public void postHandle(HttpExchange httpExchange) throws IOException {

        //repeat
    }
}
