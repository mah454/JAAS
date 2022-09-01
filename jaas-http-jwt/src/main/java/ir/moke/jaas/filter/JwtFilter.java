package ir.moke.jaas.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import ir.moke.jaas.security.HttpAuthenticator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.List;

public class JwtFilter extends Filter {
    private final HttpAuthenticator httpAuthenticator;

    public JwtFilter(HttpAuthenticator httpAuthenticator) {
        this.httpAuthenticator = httpAuthenticator;
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        Headers requestHeaders = exchange.getRequestHeaders();
        List<String> list = requestHeaders.get("TOKEN");
        if (list == null || list.isEmpty()) {
            exchange.sendResponseHeaders(401, -1);
        } else {
            DecodedJWT decodedJWT = httpAuthenticator.checkToken(list.get(0));
            if (decodedJWT == null) {
                exchange.sendResponseHeaders(401, -1);
            }

        }

        chain.doFilter(exchange);
    }

    @Override
    public String description() {
        return "JWT Authentication Filter";
    }
}
