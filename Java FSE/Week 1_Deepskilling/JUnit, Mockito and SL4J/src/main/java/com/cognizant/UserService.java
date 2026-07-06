package com.cognizant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserService — used in JUnit Exercise 4 (AAA Pattern).
 */
public class UserService {

    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null.");
        users.add(user);
    }

    public Optional<User> findById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public boolean deleteUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }

    public int getUserCount() {
        return users.size();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
