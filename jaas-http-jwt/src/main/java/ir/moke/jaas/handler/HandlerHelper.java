package ir.moke.jaas.handler;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HandlerHelper {
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> map = new HashMap<>();
        if (query == null || query.isEmpty()) return map;
        String[] kvList = query.split("&");
        for (String kv : kvList) {
            String key = kv.split("=")[0];
            String val = kv.split("=")[1];
            map.put(key, val);
        }
        return map;
    }

    public static void sendResponse(HttpExchange exchange, byte[] content, int statusCode) {
        try {
            exchange.sendResponseHeaders(statusCode, content.length);
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(content);
            responseBody.flush();
            responseBody.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
