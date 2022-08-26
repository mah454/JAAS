package ir.moke.jaas;

import com.sun.net.httpserver.HttpServer;
import ir.moke.jaas.filter.JwtFilter;
import ir.moke.jaas.handler.HelloHandler;
import ir.moke.jaas.handler.LoginHandler;
import ir.moke.jaas.security.HttpAuthenticator;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    private static final ExecutorService es = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("0.0.0.0", 8080), 0);
        httpServer.setExecutor(es);

        httpServer.createContext("/login", new LoginHandler()).setAuthenticator(new HttpAuthenticator("app"));
        httpServer.createContext("/hello", new HelloHandler()).getFilters().add(new JwtFilter());


        httpServer.start();
    }
}
