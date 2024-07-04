package org.example.hexlet;

public class NamedRoutes {

    public static String usersPath() {
        return "/users";
    }

    public static String buildUserPath() {
        return "/users/build";
    }

    public static String coursesPath() {
        return "/courses";
    }

    public static String basePath() {
        return "/";
    }

    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursePath(String id) {
        return "/courses/" + id;
    }

    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }

    public static String userPath(String id) {
        return "/users/" + id;
    }

    public static String buildSessionPath() {
        return "/sessions/build";
    }

    public static String sessionsPath() {
        return "/sessions";
    }
}
