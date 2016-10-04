package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by Stefan on 10/4/16.
 */
public class RequestHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        new Thread(() -> {
            if(httpExchange.getRequestMethod().equals("GET")) {
                try {
                    new GetHandler().handle(httpExchange);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    new PostHandler().handle(httpExchange);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }
}
