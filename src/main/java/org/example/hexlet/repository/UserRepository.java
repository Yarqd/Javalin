package org.example.hexlet.repository;

import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final List<User> entities = new ArrayList<>();
    private static long nextId = 1;

    public static void save(User user) {
        if (user.getId() == 0) { // Проверка, если id равно 0
            user.setId(nextId++);
        }
        entities.add(user);
    }

    public static List<User> getEntities() {
        return new ArrayList<>(entities);
    }

    public static Optional<User> find(Long id) {
        return entities.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public static void delete(Long id) {
        entities.removeIf(user -> user.getId() == id);
    }

    public static void clear() {
        entities.clear();
        nextId = 1;
    }

    public static void createUsers() {
        entities.add(new User(nextId++, "John", "Doe", "john.doe@example.com"));
        entities.add(new User(nextId++, "Jane", "Smith", "jane.smith@example.com"));
        entities.add(new User(nextId++, "Alice", "Johnson", "alice.johnson@example.com"));
        entities.add(new User(nextId++, "Bob", "Brown", "bob.brown@example.com"));
        entities.add(new User(nextId++, "Charlie", "Davis", "charlie.davis@example.com"));
    }
}
