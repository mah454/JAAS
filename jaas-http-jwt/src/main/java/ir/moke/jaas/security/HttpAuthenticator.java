package ir.moke.jaas.security;

import com.sun.net.httpserver.BasicAuthenticator;

public class HttpAuthenticator extends BasicAuthenticator {
    public HttpAuthenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals("admin") && password.equals("adminpass");
    }
}
