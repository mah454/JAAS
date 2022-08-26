package ir.moke.jaas.db;

import javax.security.auth.login.LoginContext;
import java.util.Objects;
import java.util.Set;

public record User(String username, String password, Set<String> roles) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
