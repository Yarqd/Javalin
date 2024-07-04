package org.example.hexlet.repository;

import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final List<User> entities = new ArrayList<>();
    private static long nextId = 1;

    public static void save(User user) {
        // Если пользователь уже существует, обновляем его данные
        Optional<User> existingUser = find(user.getId());
        if (existingUser.isPresent()) {
            User existing = existingUser.get();
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            existing.setPassword(user.getPassword());
        } else {
            // Если пользователь новый, присваиваем ему новый ID и добавляем в список
            if (user.getId() == 0) {
                user.setId(nextId++);
            }
            entities.add(user);
        }
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
        entities.add(new User(nextId++, "John", "password1", "john.doe@example.com"));
        entities.add(new User(nextId++, "Jane", "password2", "jane.smith@example.com"));
        entities.add(new User(nextId++, "Alice", "password3", "alice.johnson@example.com"));
        entities.add(new User(nextId++, "Bob", "password4", "bob.brown@example.com"));
        entities.add(new User(nextId++, "Charlie", "password5", "charlie.davis@example.com"));
    }

    public static Optional<User> findByEmail(String email) {
        return entities.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}
