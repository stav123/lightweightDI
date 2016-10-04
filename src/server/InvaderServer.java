package server;

import com.sun.net.httpserver.HttpServer;
import server.handlers.RequestHandler;
import server.utils.PropertyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Stefan on 10/4/16.
 */
public class InvaderServer {

    public static void main(String[] args) throws IOException {

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(Integer.parseInt(PropertyUtils.getProp("server.port"))), 0);

        httpServer.createContext("/", httpExchange -> new RequestHandler().handle(httpExchange));

        httpServer.setExecutor(null);
        httpServer.start();
    }
}
