package ir.moke.jaas.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        String response = "Hello dear";
        HandlerHelper.sendResponse(exchange, response.getBytes(), 200);
    }
}
