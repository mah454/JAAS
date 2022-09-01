package ir.moke.jaas.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ir.moke.jaas.security.TokenProvider;

import java.io.IOException;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String token = TokenProvider.create(exchange.getPrincipal().getUsername());
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("TOKEN", token);
        exchange.sendResponseHeaders(200, responseHeaders.size());
        HandlerHelper.sendResponse(exchange,"".getBytes(),200);
    }
}
