package ir.moke.jaas.filter;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import ir.moke.jaas.security.TokenProvider;

import java.io.IOException;
import java.util.List;

public class JwtFilter extends Filter {

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        Headers requestHeaders = exchange.getRequestHeaders();
        List<String> list = requestHeaders.get("TOKEN");
        if (list == null || list.isEmpty()) {
            exchange.sendResponseHeaders(401, -1);
        } else {
            try {
                String token = list.get(0);
                TokenProvider.verify(token);
            } catch (Exception e) {
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
