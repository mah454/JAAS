package ir.moke.jaas.security;

import java.security.Principal;
import java.util.Objects;
import java.util.Set;

public record SecurityPrincipal(String username, Set<String> roles) implements Principal {

    public boolean hasRole(String role) {
        return roles.stream().anyMatch(item -> item.equals(role));
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityPrincipal that = (SecurityPrincipal) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
