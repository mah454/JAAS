package ir.moke.jaas.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Hello dear";
        System.out.println(response);
        HandlerHelper.sendResponse(exchange,response.getBytes(),200);
    }
}
