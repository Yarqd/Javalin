package org.example.hexlet.dto.users;

import org.example.hexlet.model.User;

public class UserPage {
    private User user;

    public UserPage(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
