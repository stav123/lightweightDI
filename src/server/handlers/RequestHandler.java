package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dinjection.invader.configuration.Configuration;
import dinjection.invader.diparser.InvaderParser;
import server.utils.PropertyUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

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


        Class clazz = null;

        try {
            clazz = Class.forName(PropertyUtils.getProp("configuration.class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Configuration config = null;

        try {
            config = (Configuration) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        String res = "EMPTY";
        try {
            res = new InvaderParser(config).resolve(httpExchange.getRequestURI().getRawPath());
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }


        OutputStream responseBody = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(200, res.length());
        responseBody.write(res.getBytes());
        responseBody.close();

    }

    public void postHandle(HttpExchange httpExchange) throws IOException {

        //repeat
    }


}
