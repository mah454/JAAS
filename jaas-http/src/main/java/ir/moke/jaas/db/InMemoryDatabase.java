package ir.moke.jaas.db;

import java.util.HashSet;
import java.util.Set;

public class InMemoryDatabase {
    public static final InMemoryDatabase instance = new InMemoryDatabase();
    private static Set<User> users;

    private InMemoryDatabase() {
        users = new HashSet<>();
        Set<String> roles = Set.of("member", "user");
        users.add(new User("admin", "adminpass", roles));
    }

    public void insert(User user) {
        users.add(user);
    }

    public void delete(User user) {
        users.remove(user);
    }

    public User select(String username) {
        return users.stream().filter(item -> item.username().equals(username)).findFirst().orElse(null);
    }
}
