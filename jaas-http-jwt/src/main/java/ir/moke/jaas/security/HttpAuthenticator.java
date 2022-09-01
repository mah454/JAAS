package ir.moke.jaas.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.net.httpserver.BasicAuthenticator;

public class HttpAuthenticator extends BasicAuthenticator {
    public HttpAuthenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals("admin") && password.equals("adminpass");
    }

    public DecodedJWT checkToken(String token) {
        try {
            return TokenProvider.verify(token);
        } catch (Exception e) {
            return null;
        }
    }
}
