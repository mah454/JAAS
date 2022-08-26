package ir.moke.jaas.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HandlerHelper.sendResponse(exchange, "Login success".getBytes(), 200);
    }
}
