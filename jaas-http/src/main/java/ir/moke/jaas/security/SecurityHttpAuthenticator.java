package ir.moke.jaas.security;

import com.sun.net.httpserver.BasicAuthenticator;
import ir.moke.jaas.db.InMemoryDatabase;
import ir.moke.jaas.db.User;

public class SecurityHttpAuthenticator extends BasicAuthenticator {
    public SecurityHttpAuthenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = InMemoryDatabase.instance.select(username);
        return user != null && user.password().equals(password);
    }
}
